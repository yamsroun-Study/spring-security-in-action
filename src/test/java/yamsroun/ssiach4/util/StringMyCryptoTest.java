package yamsroun.ssiach4.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringMyCryptoTest {

    private static final String PASSWORD = "PaSsWoRd";

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "a1b2", "abcd1234"})
    void basic(String plainText) {
        StringMyCrypto myCrypto = new StringMyCrypto(PASSWORD);

        String encryptedText1 = myCrypto.encrypt(plainText);
        String encryptedText2 = myCrypto.encrypt(plainText);
        String decryptedText1 = myCrypto.decrypt(encryptedText1);
        String decryptedText2 = myCrypto.decrypt(encryptedText2);

        System.out.printf("plainText=[%s]%s", plainText, System.lineSeparator());
        System.out.printf("encryptedText1=[%s]%s", encryptedText1, System.lineSeparator());
        System.out.printf("encryptedText2=[%s]%s", encryptedText2, System.lineSeparator());

        assertThat(decryptedText1).isEqualTo(plainText);
    }
}