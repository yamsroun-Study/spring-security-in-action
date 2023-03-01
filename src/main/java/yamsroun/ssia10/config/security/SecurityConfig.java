package yamsroun.ssia10.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import yamsroun.ssia10.repository.CustomCsrfTokenRepository;
import yamsroun.ssia10.repository.CustomTokenJpaRepository;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomTokenJpaRepository customTokenJpaRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ////1. Ant식
        //http.csrf(c -> {
        //    c.csrfTokenRepository(customCsrfTokenRepository());
        //    c.ignoringAntMatchers("/ciao");
        //});
        ////2. MVC식
        //http.csrf(c -> {
        //    HandlerMappingIntrospector i = new HandlerMappingIntrospector();
        //    MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
        //    c.ignoringRequestMatchers(r);
        //});
        ////3. 정규식
        //http.csrf(c -> {
        //    String pattern = ".*[\\d].*";
        //    String httpMethod = HttpMethod.POST.name();
        //    RegexRequestMatcher r = new RegexRequestMatcher(pattern, httpMethod);
        //    c.ignoringRequestMatchers(r);
        //});
        //Spring Boot 3~
        //http.csrf(c -> {
        //    c.csrfTokenRepository(customCsrfTokenRepository());
        //    c.ignoringRequestMatchers("/ciao");
        //});
        http.csrf().disable();
        http.authorizeHttpRequests()
            .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public CsrfTokenRepository customCsrfTokenRepository() {
        return new CustomCsrfTokenRepository(customTokenJpaRepository);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var service = new InMemoryUserDetailsManager();
        var user = User.withUsername("mary")
            .password("1234")
            .authorities("READ")
            .build();
        service.createUser(user);
        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
