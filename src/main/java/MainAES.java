import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class MainAES {
    public static void main(String[] args) {
        doAES();
    }

    public static void doAES() {

        byte[] key =  "aaabbbcccddd1234".getBytes();
        byte[] encrypted = AES_Cipher.encrypt("Входной текст".getBytes(), key);
        byte[] decrypted = AES_Cipher.decrypt(encrypted, key);

        System.out.println("Зашифровано: " + Arrays.toString(encrypted));
        System.out.printf("Расшифровано: " + new String(decrypted, StandardCharsets.UTF_8));
    }
}
