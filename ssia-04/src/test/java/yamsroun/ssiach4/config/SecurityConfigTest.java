package yamsroun.ssiach4.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.*;
import org.springframework.security.crypto.keygen.*;

class SecurityConfigTest {

    @Test
    void stringKeyGenerator() {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        for (int i = 0; i < 10; i++) {
            String salt = keyGenerator.generateKey();
            System.out.println("salt=" + salt);
        }
    }

    @Test
    void bytesKeyGenerator() {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
        //BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);
        for (int i = 0; i < 10; i++) {
            byte[] key = keyGenerator.generateKey();
            int keyLength = keyGenerator.getKeyLength();
            System.out.println("key=" + key);
            System.out.println("keyLength=" + keyLength);
        }
    }

    @Test
    void sharedBytesKeyGenerator() {
        BytesKeyGenerator keyGenerator = KeyGenerators.shared(16);
        for (int i = 0; i < 10; i++) {
            byte[] key = keyGenerator.generateKey();
            System.out.println("key=" + key);
        }
    }

    @Test
    void bytesEncryptor() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String plainText = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, salt);
        byte[] encrypted = e.encrypt(plainText.getBytes());
        byte[] decrypted = e.decrypt(encrypted);

        System.out.println("plainText=" + plainText);
        System.out.println("encrypted=" + encrypted);
        System.out.println("decrypted=" + new String(decrypted));
    }

    @Test
    void textEncryptor() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String plainText = "HELLO";

        TextEncryptor e = Encryptors.text(password, salt);
        String encrypted = e.encrypt(plainText);
        String decrypted = e.decrypt(encrypted);

        System.out.println("plainText=" + plainText);
        System.out.println("encrypted=" + encrypted);
        System.out.println("decrypted=" + decrypted);
    }

    @Test
    void queryableTextEncryptor() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String plainText = "HELLO";

        TextEncryptor e = Encryptors.text(password, salt);
        //TextEncryptor e = Encryptors.queryableText(password, salt); // Spring Security 6.0.0에서 제거됨
        for (int i = 0; i < 10; i++) {
            String encrypted = e.encrypt(plainText);
            String decrypted = e.decrypt(encrypted);

            System.out.println("plainText=" + plainText);
            System.out.println("encrypted=" + encrypted);
            System.out.println("decrypted=" + decrypted);
        }
    }
}