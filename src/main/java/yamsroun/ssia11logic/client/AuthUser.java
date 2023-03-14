package yamsroun.ssia11logic.client;

import lombok.Getter;


@Getter
public class AuthUser {

    private final String username;
    private final String password;
    private final String code;

    private AuthUser(String username, String password, String code) {
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public static AuthUser ofUsernameAndPassword(String username, String password) {
        return new AuthUser(username, password, null);
    }

    public static AuthUser ofUsernameAndCode(String username, String code) {
        return new AuthUser(username, null, code);
    }
}
