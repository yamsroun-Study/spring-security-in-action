package yamsroun.ssiach9.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StaticKeyAuthenticationFilter implements Filter {

    private final String authorizationKey;

    public StaticKeyAuthenticationFilter(
        @Value("${authorization.key}") String authorizationKey
    ) {
        this.authorizationKey = authorizationKey;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authentication = httpRequest.getHeader("Authorization");
        if (!authorizationKey.equals(authentication)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }
}
