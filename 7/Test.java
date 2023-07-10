import java.io.*;
import java.util.*;
import java.math.*;

public class Test {
    public static boolean isPrime(BigInteger n, int iteration) {
        if(n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return false;
        }

        if(n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }

        // n-1 = 2^k * m
        BigInteger m = n.subtract(BigInteger.ONE);
        int k = 0;
        while(m.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            k++;
            m = m.divide(BigInteger.TWO);
        }

        // choose a
        Random random = new Random();
        
        for(int i=0; i<iteration; i++) {
            // Choose a random number between 2 and n-1
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), random);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.ONE)) > 0);

            // Compute b0 = a^m mod n
            BigInteger b = a.modPow(m, n);
            
            if(!b.equals(BigInteger.ONE) && !b.equals(n.subtract(BigInteger.ONE))) {
                int j = 1;

                while(j < k && !b.equals(n.subtract(BigInteger.ONE))) {
                    // Compute b1 = b0^2 mod n
                    b = b.modPow(BigInteger.TWO, n);

                    if(b.equals(BigInteger.ONE)) {
                        return false; // composite
                    }
                    j++;
                }

                if (!b.equals(n.subtract(BigInteger.ONE))) {
                    return false; // n is composite
                }
                
            }
        }

        return true;
    }
    public static void main(String[] args) {
        String bigNumber = readFile("./input.txt");
        BigInteger number = new BigInteger(bigNumber);
        int iteration = 10;

        if (isPrime(number, iteration)) {
            System.out.println(number + " is likely prime.");
        } else {
            System.out.println(number + " is composite.");
        }
    }

    public static String readFile(String filename) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return content.toString();
    }

    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
