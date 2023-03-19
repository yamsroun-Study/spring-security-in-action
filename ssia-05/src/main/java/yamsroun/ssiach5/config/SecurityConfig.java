package yamsroun.ssiach5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import yamsroun.ssiach5.security.CustomAuthenticationFailureHandler;
import yamsroun.ssiach5.security.CustomAuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler authSuccessHandler;
    private final CustomAuthenticationFailureHandler authFailureHandler;

    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(
            SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler)
            .and()
            .httpBasic();
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated();
        return http.build();
    }
}
