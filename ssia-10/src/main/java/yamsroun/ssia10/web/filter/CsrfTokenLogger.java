package yamsroun.ssia10.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CsrfTokenLogger implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;

        log.info(">>> CSRF token : {}", token.getToken());
        chain.doFilter(request, response);
    }
}
