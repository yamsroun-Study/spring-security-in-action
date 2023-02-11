package com.example.ssiach8.config;

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
            .roles("ADMIN")
            .build();
        var user2 = User.withUsername("jane")
            .password("1234")
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
        //Spring Boot 3.x~
        http.authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/hello").hasRole("ADMIN")
            .requestMatchers("/ciao").hasRole("MANAGER")
            //.anyRequest().denyAll() //미설정 시 기본동작
            .anyRequest().authenticated()
        );
        //Spring Boot ~2.7.x
        //http.authorizeHttpRequests((authorize) -> authorize
        //    .mvcMatchers("/hello").hasRole("ADMIN")
        //    .mvcMatchers("/ciao").hasRole("MANAGER")
        //    .anyRequest().permitAll() //미설정 시 기본동작
        //);
        //http.authorizeRequests()
        //    .mvcMatchers("/hello").hasRole("ADMIN")
        //    .mvcMatchers("/ciao").hasRole("MANAGER")
        //    .anyRequest().permitAll(); //미설정 시 기본동작
        return http.build();
    }
}
