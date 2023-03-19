package yamsroun.ssia11logic.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import yamsroun.ssia11logic.client.AuthenticationServerProxy;
import yamsroun.ssia11logic.security.auth.OtpAuthentication;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());
        boolean success = proxy.sendOtp(username, code);
        if (success) {
            return new OtpAuthentication(username, code);
        }
        throw new BadCredentialsException("Bad credentials.");
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return OtpAuthentication.class.isAssignableFrom(authClass);
    }
}
