package com.example.ssiach7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
            .password("1234")
            .authorities("READ", "WRITE")
            .build();
        var user2 = User.withUsername("jane")
            .password("1234")
            .authorities("WRITE", "DELETE")
            .build();
        manager.createUser(user1);
        manager.createUser(user2);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeHttpRequests()
            .anyRequest()
            //permitAll();
            //hasAuthority("WRITE");
            //hasAnyAuthority("WRITE", "READ");
            .access(new WebExpressionAuthorizationManager(
                "hasAuthority('WRITE') and !hasAuthority('DELETE')"));
        return http.build();
    }
}
