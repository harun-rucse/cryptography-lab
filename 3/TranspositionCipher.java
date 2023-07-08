import java.util.Scanner;
import java.io.*;

public class TranspositionCipher {
    public static String encrypt(String plaintext, int width) {
        int height = (int) Math.ceil((double) plaintext.length() / width);
        char[][] grid = new char[height][width];
        int index = 0;

        // Fill the grid with the plaintext characters
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (index < plaintext.length()) {
                    grid[row][col] = plaintext.charAt(index);
                    index += 1;
                } else {
                    grid[row][col] = ' ';
                }
            }
        }

        // Read the grid column-wise to get the ciphertext
        StringBuilder ciphertext = new StringBuilder();
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                ciphertext.append(grid[row][col]);
            }
        }

        return ciphertext.toString().trim();
    }

    public static String decrypt(String ciphertext, int width) {
        int height = (int) Math.ceil((double) ciphertext.length() / width);
        char[][] grid = new char[height][width];
        int index = 0;

        // Fill the grid with the ciphertext characters
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if (index < ciphertext.length()) {
                    grid[row][col] = ciphertext.charAt(index);
                    index += 1;
                } else {
                    grid[row][col] = ' ';
                }
            }
        }

        // Read the grid row-wise to get the decrypted plaintext
        StringBuilder plaintext = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                plaintext.append(grid[row][col]);
            }
        }

        return plaintext.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String plaintext = readFile("./input.txt");
        
        System.out.print("Enter the width: ");
        int width = scanner.nextInt();

        // Encryption
        String ciphertext = encrypt(plaintext, width);
        writeFile("./ciphertext.txt", ciphertext);

        // Decryption
        String decryptedPlaintext = decrypt(ciphertext, width);
        writeFile("./decryptedtext.txt", decryptedPlaintext);

        // String ciphertext = encrypt(encrypt(plaintext, width), width);
        // System.out.println("Double Ciphertext: " + ciphertext);

        // String decryptedtext = decrypt(decrypt(ciphertext, width), width);
        // System.out.println("Decryptedtext: " + decryptedtext);

        scanner.close();
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

