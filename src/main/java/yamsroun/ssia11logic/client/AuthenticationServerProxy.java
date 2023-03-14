package yamsroun.ssia11logic.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {

    private final RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        AuthUser authUser = AuthUser.ofUsernameAndPassword(username, password);
        HttpEntity<AuthUser> request = new HttpEntity<>(authUser);
        restTemplate.postForEntity(url, request, Void.class);
    }

    public boolean sendOtp(String username, String code) {
        String url = baseUrl + "/otp/check";
        AuthUser authUser = AuthUser.ofUsernameAndCode(username, code);
        HttpEntity<AuthUser> request = new HttpEntity<>(authUser);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
