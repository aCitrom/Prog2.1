#include <iostream>
#include <string>
#include <boost/filesystem.hpp>
#include <boost/filesystem/fstream.hpp>

using namespace std;

int k = 0;

void getAmount(boost::filesystem::path path);

int main ()
{
  getAmount("Letöltések/jdk-11/lib");
  cout << k;
  return 0;
}

void getAmount(boost::filesystem::path path) {
  for(boost::filesystem::directory_entry & entry : boost::filesystem::directory_iterator(path))
  {
    string ext ( ".java" );
    if(is_regular_file(entry.path()))
    {
        if(!ext.compare(boost::filesystem::extension(entry.path())))
          k++;
    } else getAmount(entry.path());
  }
}

