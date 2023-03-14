package yamsroun.ssia11logic.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import yamsroun.ssia11logic.security.provider.OtpAuthenticationProvider;
import yamsroun.ssia11logic.security.provider.UsernamePasswordAuthenticationProvider;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final OtpAuthenticationProvider otpAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        var providers = List.of(usernamePasswordAuthenticationProvider, otpAuthenticationProvider);
        return new ProviderManager(providers);
    }
}
