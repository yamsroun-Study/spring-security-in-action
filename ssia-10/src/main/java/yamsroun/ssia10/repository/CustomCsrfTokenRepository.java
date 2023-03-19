package yamsroun.ssia10.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.*;
import yamsroun.ssia10.domain.CustomToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final CustomTokenJpaRepository customTokenJpaRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        customTokenJpaRepository.findTokenByIdentifier(identifier)
            .ifPresentOrElse(
                t -> t.setToken(token.getToken()),
                () -> customTokenJpaRepository.save(
                    new CustomToken(identifier, token.getToken()))
            );
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        return customTokenJpaRepository.findTokenByIdentifier(identifier)
            .map(t -> new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", t.getToken()))
            .orElse(null);
    }
}
