import java.io.*;
import java.util.Scanner;

public class Double {
    public static String encrypt(String plaintext, int width) {
        int height = (int) Math.ceil((double) plaintext.length() / width);
        char[][] grid = new char[height][width];
        
        int index = 0;
        for(int row=0; row<height; row++) {
            for(int col=0; col<width; col++) {
                if(index < plaintext.length()) {
                    grid[row][col] = plaintext.charAt(index);
                    index += 1;
                } else {
                    grid[row][col] = ' ';
                }
            }
        }

        StringBuilder ciphertext = new StringBuilder();

        for(int col=0; col<width; col++) {
            for(int row=0; row<height; row++) {
                ciphertext.append(grid[row][col]);
            }
        }


        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int width) {
        int height = (int) Math.ceil((double) ciphertext.length()/width);
        char[][] grid = new char[height][width];

        int index = 0;
        for(int col=0; col<width; col++) {
            for(int row=0; row<height; row++) {
                if(index < ciphertext.length()) {
                    grid[row][col] = ciphertext.charAt(index);
                    index += 1;
                } else {
                    grid[row][col] = ' ';
                }
            }
        }

        StringBuilder plaintext = new StringBuilder();

        for(int row=0; row<height; row++) {
            for(int col=0; col<width; col++) {
                plaintext.append(grid[row][col]);
            }
        }

        return plaintext.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String plaintext = readFile("./input.txt");

        System.out.print("Enter width: ");
        int width = scanner.nextInt();

        String ciphertext = encrypt(encrypt(plaintext, width), width);
        System.out.println("Double Ciphertext: " + ciphertext);

        String decryptedtext = decrypt(decrypt(ciphertext, width), width);
        System.out.println("Decryptedtext: " + decryptedtext);

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
