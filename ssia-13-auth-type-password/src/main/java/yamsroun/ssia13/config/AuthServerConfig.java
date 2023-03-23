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
        //var service = new InMemoryClientDetailsService();
        //var cd = new BaseClientDetails();
        //cd.setClientId("client");
        //cd.setClientSecret("secret");
        //cd.setScope(List.of("read"));
        //cd.setAuthorizedGrantTypes(List.of("password"));

        //service.setClientDetailsStore(Map.of("client", cd));
        //clients.withClientDetails(service);

        //간단한 버전
        clients.inMemory()
            .withClient("client")
            .secret("secret")
            .authorizedGrantTypes("password")
            .scopes("read");
    }
}
