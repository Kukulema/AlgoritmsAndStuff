import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class DiffiHelman {
    public static void main(String[] args) throws Exception {
        int p = 13;
        int g = 83;
        int a = 7; // Закрытый ключ Алисы
        int b = 5; // Закрытый ключ Боба

        // Вычисление секретного ключа 
        int sessionKeyAlice = (int) Math.pow(g, a) % p;
        int sessionKeyBob = (int) Math.pow(g, b) % p;

        // Преобразование секретного ключа в ключ для AES
        String aesKeyAlice = generateAESKey(String.valueOf(sessionKeyAlice));
        String aesKeyBob = generateAESKey(String.valueOf(sessionKeyBob));

        // Сообщение для шифрования
        String message = "llalalalalaaaaaa";

        // Шифрование сообщения на стороне Алисы
        String encryptedMessage = encryptMessage(message, aesKeyAlice);
        System.out.println("Зашифрованное сообщение Алисы: " + encryptedMessage);

        // Расшифрование сообщения на стороне Боба
        String decryptedMessage = decryptMessage(encryptedMessage, aesKeyBob);
        System.out.println("Расшифрованное сообщение Боба: " + decryptedMessage);
    }

    // Метод для генерации ключа AES на основе заданного ключа
    public static String generateAESKey(String key) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(key.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    // Метод для шифрования сообщения
    public static String encryptMessage(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Метод для расшифрования сообщения
    public static String decryptMessage(String encryptedMessage, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
