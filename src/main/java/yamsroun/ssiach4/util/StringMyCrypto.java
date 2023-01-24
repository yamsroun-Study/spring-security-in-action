package yamsroun.ssiach4.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.Random;

public class StringMyCrypto {

    private static final int SALT_FIELD_SIZE = 2;
    private static final Random random = new Random();

    private final String password;

    public StringMyCrypto(String password) {
        this.password = password;
    }

    public String encrypt(String plainText) {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        int randomNum = random.nextInt(16 / 2) * 2 + 2; // 2~16(even only)
        String salt = keyGenerator.generateKey().substring(0, randomNum);
        int saltSize = salt.length();

        TextEncryptor e = Encryptors.delux(password, salt);
        String digest = e.encrypt(salt + plainText);
        return String.format("%0" + SALT_FIELD_SIZE + "d%s%s", saltSize, salt, digest);
    }

    public String decrypt(String encryptedText) {
        int saltSize;
        try {
            saltSize = Integer.parseInt(encryptedText.substring(0, SALT_FIELD_SIZE));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid encrypted string");
        }

        String salt = encryptedText.substring(SALT_FIELD_SIZE, saltSize + SALT_FIELD_SIZE);
        String digest = encryptedText.substring(saltSize + SALT_FIELD_SIZE);

        TextEncryptor encryptor = Encryptors.delux(password, salt);
        return encryptor.decrypt(digest).substring(saltSize);
    }
}
