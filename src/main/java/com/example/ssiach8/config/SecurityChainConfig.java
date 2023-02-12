package com.example.ssiach8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
public class SecurityChainConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();

        //Spring Boot 3.x~
        http.authorizeHttpRequests(authz -> authz
            .requestMatchers(regexMatcher(".*/(us|uk|ca)+/(en|fr).*")).authenticated()
            .anyRequest().denyAll()
        );

        //Spring Boot ~2.7.x 방식 1
        //http.authorizeHttpRequests(authz -> authz
        //    .regexMatchers(".*/(us|uk|ca)+/(en|fr).*").authenticated()
        //    .anyRequest().denyAll()
        //);

        //Spring Boot ~2.7.x 방식 2
        //http.authorizeRequests()
        //    .regexMatchers(".*/(us|uk|ca)+/(en|fr).*").authenticated()
        //    .anyRequest().hasAuthority("premium");

        return http.build();
    }
}
