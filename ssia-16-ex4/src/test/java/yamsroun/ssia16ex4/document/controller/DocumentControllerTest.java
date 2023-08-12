package yamsroun.ssia16ex4.document.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @ParameterizedTest
    @ValueSource(strings = {"abc123", "asd123"})
    @DisplayName("어드민인 경우, 소유자와 무관하게 모든 문서 접근 허용")
    void allowAccessDocumentOfOwnerForAdmin(String documentCode) {
        String username = "natalie";
        String password = "1234";
        ResponseEntity<String> response = callUsingWithBasicAuth(username, password, documentCode);
        HttpStatusCode statusCode = response.getStatusCode();
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.OK);
        System.out.println(">>> body=" + response.getBody());
    }

    @Test
    @DisplayName("일반 사용자인 경우, 본인 소유 문서에 접근 허용")
    void allowAccessDocumentOfOwnerForUser() {
        String username = "emma";
        String password = "1234";
        String documentCode = "asd123";
        ResponseEntity<String> response = callUsingInterceptor(username, password, documentCode);
        HttpStatusCode statusCode = response.getStatusCode();
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.OK);
        System.out.println(">>> body=" + response.getBody());
    }

    @Test
    @DisplayName("일반 사용자인 경우, 본인 소유가 아닌 문서에 접근 거부")
    void denyAccessDocumentOfNotOwnerForUser() {
        String username = "emma";
        String password = "1234";
        String documentCode = "abc123";
        ResponseEntity<String> response = callUsingInterceptor(username, password, documentCode);
        HttpStatusCode statusCode = response.getStatusCode();
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.FORBIDDEN);
    }


    String getUrlWith(String documentCode) {
        return "http://localhost:" + port + "/documents/" + documentCode;
    }

    ResponseEntity<String> callUsingWithBasicAuth(String username, String password, String documentCode) {
        String url = getUrlWith(documentCode);
        return restTemplate.withBasicAuth(username, password)
            .getForEntity(url, String.class);
    }

    ResponseEntity<String> callUsingInterceptor(String username, String password, String documentCode) {
        restTemplate.getRestTemplate()
            .getInterceptors()
            .add(new BasicAuthenticationInterceptor(username, password));

        String url = getUrlWith(documentCode);
        return restTemplate.getForEntity(url, String.class);
    }


    static class BasicAuthenticationInterceptor implements ClientHttpRequestInterceptor {

        private final String username;
        private final String password;

        public BasicAuthenticationInterceptor(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            byte[] tokenBytes = Base64.getEncoder().encode((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            String authorization = "Basic " + new String(tokenBytes);
            request.getHeaders().set(HttpHeaders.AUTHORIZATION, authorization);
            return execution.execute(request, body);
        }
    }
}