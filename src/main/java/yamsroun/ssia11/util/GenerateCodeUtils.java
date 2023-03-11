package yamsroun.ssia11.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import yamsroun.ssia11.exception.ApplicationException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateCodeUtils {

    public static String generateCode() {
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int code = random.nextInt(9000) + 1000;
            return String.valueOf(code);
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException(e);
        }
    }
}
