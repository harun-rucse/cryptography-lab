import java.io.*;

public class CaeserCipper{
    public String encrypt(String text, int shift){
        String encryptedText = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (c + shift - base) % 26;
                c = (char) (offset + base);
            }

            encryptedText += c;
        }

        return encryptedText;
    }

    public String decrypt(String text, int shift){
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        String text, result;
        text = readFile("./plaintext.txt");
        int shift = 4;

        CaeserCipper caeserCipper = new CaeserCipper();

        result = caeserCipper.encrypt(text, shift);
        writeFile("./ciphertext.txt",result);

        result = caeserCipper.decrypt(result, shift);
        writeFile("./decryptedtext.txt",result);
    }

    public static String readFile(String fileName){
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null){
                content += line;
            }
        }catch(IOException e) {
            System.out.println("File not found");
        }
 
        return content.toString();
    }

    public static void writeFile(String fileName, String content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch(IOException e) {
            System.out.println("File not found");
        }
    }
}