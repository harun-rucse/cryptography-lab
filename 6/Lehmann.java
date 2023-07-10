import java.io.*;
import java.math.*;
import java.util.*;

public class Lehmann {
    public static boolean isPrime(BigInteger p, int iteration) {
        if(p.equals(BigInteger.ZERO) || p.equals(BigInteger.ONE)) 
            return false;

        if(p.equals(BigInteger.TWO) || p.equals(BigInteger.valueOf(3))) 
            return true;

        Random random = new Random();

        for(int i=0; i<iteration; i++) {
            // Generate a random number between 2 and P-1
            BigInteger a;
            do {
                a = new BigInteger(p.bitLength(), random);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(p.subtract(BigInteger.ONE)) > 0);

            // Compute a^((P-1)/2) mod P
            BigInteger x = a.modPow(p.subtract(BigInteger.ONE).divide(BigInteger.TWO), p);

            // If the result is not 1 or p-1, then n is composite
            if(!x.equals(BigInteger.ONE) && !x.equals(p.subtract(BigInteger.ONE))) {
                return false;
            }
        }

        return true;
    }

   public static void main(String[] arg) {
        String bigNumber = readFile("./input.txt");

        BigInteger p = new BigInteger(bigNumber);
        int iteration = 4;

        if(isPrime(p, iteration)) {
            System.out.println(p + " is Likely prime.");
        } else {
            System.out.println(p + " is composite.");
        }
   }

    public static String readFile(String filename) {
        String content = "";

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line=reader.readLine()) != null) {
                content += line;
            }
        } catch(IOException e) {
            System.out.println("File not found");
        }

        return content.toString();
    }

    public static void writeFile(String filename, String content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }
}
