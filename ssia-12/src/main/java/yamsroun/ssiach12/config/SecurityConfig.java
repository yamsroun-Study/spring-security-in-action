package yamsroun.ssiach12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //@Value("${spring.security.oauth2.client.registration.custom.client-id:}")
    private String clientId;

    //@Value("${spring.security.oauth2.client.registration.custom.client-secret:}")
    private String clientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Login();
        //http.oauth2Login(c -> c.clientRegistrationRepository(clientRegistrationRepository()));

        http.authorizeHttpRequests()
            .anyRequest().authenticated();
        return http.build();
    }

    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientRegistration2());
        //TODO: DB에 권한 부여 세부 정보를 저장하도록 수정
    }

    //첫 번째 방법
    private ClientRegistration clientRegistration1() {
        return ClientRegistration.withRegistrationId("github")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .scope("read:user")
            .authorizationUri("https://github.com/login/oauth/authorize")
            .tokenUri("https://github.com/login/oauth/access_token")
            .userInfoUri("https://api.github.com/user")
            .userNameAttributeName("id")
            .clientName("GibHub")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .build();
    }

    //두 번째 방법
    private ClientRegistration clientRegistration2() {
        return CommonOAuth2Provider.GITHUB
            .getBuilder("github")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();
    }
}
