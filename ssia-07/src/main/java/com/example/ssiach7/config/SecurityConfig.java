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

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
            .password("1234")
            //.authorities("READ", "WRITE")
            //.authorities("ROLE_ADMIN")
            .roles("ADMIN")
            .build();
        var user2 = User.withUsername("jane")
            .password("1234")
            //.authorities("WRITE", "DELETE")
            //.authorities("ROLE_MANAGER")
            .roles("MANAGER")
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
            //.access(new WebExpressionAuthorizationManager(
            //    "hasAuthority('WRITE') and !hasAuthority('DELETE')"));
            //.hasRole("ADMIN");
            .denyAll();
        return http.build();
    }
}
