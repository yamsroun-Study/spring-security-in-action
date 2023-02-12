package com.example.ssiach8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityChainConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();

        //Spring Boot 3.x~
        http.authorizeHttpRequests(authz -> authz
            .requestMatchers("/hello").authenticated()
            .anyRequest().denyAll()
        );

        //Spring Boot ~2.7.x 방식 1
        //http.authorizeHttpRequests(authz -> authz
        //    .mvcMatchers("/hello").authenticated()
        //    .antMatchers("/hello").authenticated()
        //    .anyRequest().denyAll()
        //);

        //Spring Boot ~2.7.x 방식 2
        //http.authorizeRequests()
        //    .mvcMatchers("/hello").authenticated()
        //    .antMatchers("/hello").authenticated()
        //    .anyRequest().denyAll();

        return http.build();
    }
}
