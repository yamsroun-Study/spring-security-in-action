package yamsroun.ssiach9.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import yamsroun.ssiach9.web.filter.*;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(
            new RequestValidationFilter(),
            BasicAuthenticationFilter.class
        ).addFilterAfter(
            new AuthenticationLoggingFilter(),
            BasicAuthenticationFilter.class
        ).addFilterAt(
            staticKeyAuthenticationFilter,
            BasicAuthenticationFilter.class
        );

        http.authorizeHttpRequests()
            .anyRequest().permitAll();
        return http.build();
    }
}
