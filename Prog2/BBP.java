/*
 * PiBBP.java
 *
 * DIGIT 2005, Javat tan�tok
 * B�tfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A BBP (Bailey-Borwein-Plouffe) algoritmust a Pi hexa
 * jegyeinek sz�mol�s�t v�gz� oszt�ly. A k�nnyebb olvahat�s�g
 * kedv��rt a v�ltoz� �s met�dus neveket megpr�b�ltuk az algoritmust
 * bemutat� [BBP ALGORITMUS] David H. Bailey: The BBP Algorithm for Pi.
 * cikk jel�l�seihez.
 *
 * @author B�tfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 */
public class PiBBP {
    /** A Pi hexa kifejt�s�ben a d+1. hexa jegyt�l n�h�ny hexa jegy.*/
    String d16PiHexaJegyek;
    /**
     * L�trehoz egy <code>PiBBP</code>, a BBP algoritmust a Pi-hez
     * alkalmaz� objektumot. A [BBP ALGORITMUS] David H. Bailey: The
     * BBP Algorithm for Pi. alapj�n a
     * {16^d Pi} = {4*{16^d S1} - 2*{16^d S4} - {16^d S5} - {16^d S6}}
     * kisz�m�t�sa, a {} a t�rtr�szt jel�li.
     *
     * @param   d   a Pi hexa kifejt�s�ben a d+1. hexa jegyt�l
     * sz�moljuk a hexa jegyeket
     */
    public PiBBP(int d) {
        
        double d16Pi = 0.0d;
        
        double d16S1t = d16Sj(d, 1);
        double d16S4t = d16Sj(d, 4);
        double d16S5t = d16Sj(d, 5);
        double d16S6t = d16Sj(d, 6);
        
        d16Pi = 4.0d*d16S1t - 2.0d*d16S4t - d16S5t - d16S6t;
        
        d16Pi = d16Pi - StrictMath.floor(d16Pi);
        
        StringBuffer sb = new StringBuffer();
        
        Character hexaJegyek[] = {'A', 'B', 'C', 'D', 'E', 'F'};
        
        while(d16Pi != 0.0d) {
            
            int jegy = (int)StrictMath.floor(16.0d*d16Pi);
            
            if(jegy<10)
                sb.append(jegy);
            else
                sb.append(hexaJegyek[jegy-10]);
            
            d16Pi = (16.0d*d16Pi) - StrictMath.floor(16.0d*d16Pi);
        }
        
        d16PiHexaJegyek = sb.toString();
    }
    /**
     * BBP algoritmus a Pi-hez, a [BBP ALGORITMUS] David H. Bailey: The
     * BBP Algorithm for Pi. alapj�n a {16^d Sj} r�szlet kisz�m�t�sa.
     *
     * @param   d   a d+1. hexa jegyt�l sz�moljuk a hexa jegyeket
     * @param   j   Sj indexe
     */
    public double d16Sj(int d, int j) {
        
        double d16Sj = 0.0d;
        
        for(int k=0; k<=d; ++k)
            d16Sj += (double)n16modk(d-k, 8*k + j) / (double)(8*k + j);
        
        /* (bekapcsolva a sorozat elejen az els� ut�ni jegyekben n�veli pl.
            a pontoss�got.)
        for(int k=d+1; k<=2*d; ++k)
            d16Sj += StrictMath.pow(16.0d, d-k) / (double)(8*k + j);
         */
        
        return d16Sj - StrictMath.floor(d16Sj);
    }
    /**
     * Bin�ris hatv�nyoz�s mod k, a 16^n mod k kisz�m�t�sa.
     *
     * @param   n   kitev�
     * @param   k   modulus
     */
    public long n16modk(int n, int k) {
        
        int t = 1;
        while(t <= n)
            t *= 2;
        
        long r = 1;
        
        while(true) {
            
            if(n >= t) {
                r = (16*r) % k;
                n = n - t;
            }
            
            t = t/2;
            
            if(t < 1)
                break;
            
            r = (r*r) % k;
            
        }
        
        return r;
    }
    /**
     * A kisz�molt n�h�ny hexa jegy visszaad�sa. A haszn�lt lebeg�pontos
     * aritmentia f�ggv�ny�ben mondjuk az els� 6 pontos peld�ul
     * d=1000000 eset�n.
     *
     * @return String a kisz�molt n�h�ny hexa jegy
     */
    public String toString() {
        
        return d16PiHexaJegyek;
    }
    /** P�ld�nyos�t egy BBP algoritmust implement�l� obektumot.*/
    public static void main(String args[]) {        
        System.out.println(new PiBBP(1000000));
    }
}