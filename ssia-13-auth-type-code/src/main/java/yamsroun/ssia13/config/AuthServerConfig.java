package yamsroun.ssia13.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("client")
            .secret("secret")
            .authorizedGrantTypes("authorization_code")
            .scopes("read")
            .redirectUris("http://localhost:9090/home")

            //다른 그랜트 유형의 클라이언트 구성
            .and()
            .withClient("client1")
            .secret("secret1")
            .authorizedGrantTypes("authorization_code")
            .scopes("read")
            .redirectUris("http://localhost:9090/home")

            .and()
            .withClient("client2")
            .secret("secret2")
            .authorizedGrantTypes("authorization_code", "password", "refresh_token")
            .scopes("read")
            .redirectUris("http://localhost:9090/home");
    }
}
