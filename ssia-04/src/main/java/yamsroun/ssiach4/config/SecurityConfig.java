package yamsroun.ssiach4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();

        //return new StandardPasswordEncoder("secret");

        //return new Pbkdf2PasswordEncoder();
        //return new Pbkdf2PasswordEncoder("secret");
        //return new Pbkdf2PasswordEncoder("secret", 185000, 256);

        //return new BCryptPasswordEncoder();
        //return new BCryptPasswordEncoder(4);
        //SecureRandom s = SecureRandom.getInstanceStrong();
        //return new BCryptPasswordEncoder(4, s);

        //return new SCryptPasswordEncoder();
        //return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);

        //HashMap<String, PasswordEncoder> encoders = new HashMap<>();
        //encoders.put("noop", NoOpPasswordEncoder.getInstance());
        //encoders.put("bcrypt", new BCryptPasswordEncoder());
        //encoders.put("scrypt", new SCryptPasswordEncoder());
        //return new DelegatingPasswordEncoder("bcrypt", encoders);

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
