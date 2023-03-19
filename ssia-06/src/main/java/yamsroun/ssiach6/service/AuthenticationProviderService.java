package yamsroun.ssiach6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yamsroun.ssiach6.config.CustomUserDetails;
import yamsroun.ssiach6.domain.User;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final CustomUserDetailService userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        User user = userDetails.getUser();
        return switch (user.getAlgorithm()) {
            case BCRYPT -> checkPassword(userDetails, password, bCryptPasswordEncoder);
            case SCRYPT -> checkPassword(userDetails, password, sCryptPasswordEncoder);
            default -> throw new BadCredentialsException("Bad credentials");
        };
    }

    private Authentication checkPassword(
        CustomUserDetails userDetails,
        String rawPassword,
        PasswordEncoder encoder
    ) {
        if (encoder.matches(rawPassword, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authClass);
    }
}
