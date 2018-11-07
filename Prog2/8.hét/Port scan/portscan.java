import java.net.InetAddress;
import java.net.Socket;

public class MainClass {

  public static void main(String[] args) {

    String host = "localhost";

    try {
      InetAddress theAddress = InetAddress.getByName(host);
      for (int i = 1; i < 65536; i++) {
        Socket connection = null;
        connection = new Socket(host, i);
        System.out.println("There is a server on port " + i + " of " + host);
        if (connection != null)
          connection.close();
      } // end for
    } catch (Exception ex) {
      System.err.println(ex);
    }

  }

}

