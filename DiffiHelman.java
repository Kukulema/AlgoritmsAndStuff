import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class DiffiHelman {
    public static void main(String[] args) throws Exception {
        int p = 13;
        int q = 83;

        // Вычисление закрытого сессионного ключа
        int sessionKey = p * q;

        // Сообщение для шифрования
        String message = "lalalalalalalaal";

        // Преобразование ключа в ключ для AES
        String aesKey = generateAESKey(String.valueOf(sessionKey));

        // Шифрование сообщения на стороне Отправителя
        String encryptedMessage = encryptMessage(message, aesKey);

        System.out.println("Зашифрованное сообщение: " + encryptedMessage);

        // Расшифрование сообщения на стороне Получателя
        String decryptedMessage = decryptMessage(encryptedMessage, aesKey);

        System.out.println("Расшифрованное сообщение: " + decryptedMessage);
    }
   // Генерация ключа AES на основе заданного ключа.
    public static String generateAESKey(String key) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(key.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    public static String encryptMessage(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptMessage(String encryptedMessage, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
