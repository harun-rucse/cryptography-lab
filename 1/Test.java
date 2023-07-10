import java.io.*;

public class Test {
    public static String encrypt(String plaintext, int shift) {
        String encryptedText = "";
        for(int i=0; i<plaintext.length(); i++) {
            char c = plaintext.charAt(i);

            if(Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A': 'a';
                int offset = (c + shift - base) % 26;
                c = (char) (offset + base);
            }

            encryptedText += c;
        }

        return encryptedText;
    }

    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift);
    }


    public static void main(String[] args) {
        String plaintext = readFile("./plaintext.txt");
        int shift = 4;

        String ciphertext = encrypt(plaintext, shift);
        writeFile("./ciphertext.txt", ciphertext);

        String decryptedtext = decrypt(ciphertext, shift);
        writeFile("./decryptedtext.txt", decryptedtext);
    }


    public static String readFile(String filename) {
        String content = "";

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine()) != null) {
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
