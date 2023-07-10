import java.io.*;

public class Onetimepad {
    public static char generateRandomLetter() {
        int randomInt = (int) (Math.random() * 26);

        return (char) (randomInt + 'A');
    }

    public static void generateKey(int length, String filename) {
        String keys = "";
        for(int i=0; i<length; i++) {
            keys += generateRandomLetter();
        }

        writeFile(filename, keys);
    }


    public static void main(String[] args) {
        // Read the plain text
        String plainText = readFile("plaintext.txt");

        // Generate a random key file
        generateKey(plainText.length(), "key.txt");

        // Read the key file
        String key = readFile("key.txt");
        key = key.toUpperCase();

        String cipherText = encrypt(plainText, key);
        writeFile("ciphertext.txt", cipherText);

        String decryptedText = decrypt(cipherText, key);
        writeFile("decrypt.txt", decryptedText);

    }

    // Encrypt the plaintext using the key
    public static String encrypt(String plaintext, String key) {
        String cipherText = "";
        
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i);

            if (Character.isUpperCase(plainChar)) {
                int x = (plainChar - 'A' + keyChar - 'A') % 26;
                x = x + 'A' + 1;
                cipherText += (char) x;
            } else if (Character.isLowerCase(plainChar)) {
                int x = (plainChar - 'a' + keyChar - 'A') % 26;
                x = x + 'a' + 1;
                cipherText += (char) x;
            } else {
                cipherText += plainChar;
            }
        }
        return cipherText;
    }

    // Encrypt the plaintext using the key
    public static String decrypt(String ciphertext, String key) {
        String plainText = "";
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i);
            
            if (Character.isUpperCase(cipherChar)) {
                int x = cipherChar - 'A' - (keyChar - 'A');
                if(x < 0) {
                    x += 26;
                }
                x = x + 'A' - 1;
                plainText += (char) x;
            } else if (Character.isLowerCase(cipherChar)) {
                int x = cipherChar - 'a' - (keyChar - 'A');
                if(x < 0) {
                    x += 26;
                }
                x = x + 'a' - 1;
                plainText += (char) x;
            } else {
                plainText += cipherChar;
            }
        }
        return plainText;
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