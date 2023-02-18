package yamsroun.ssiach9.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import yamsroun.ssiach9.web.data.HttpHeader;

import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestId = httpRequest.getHeader(HttpHeader.REQUEST_ID.code());
        log.info("Successfully authenticated request with id {}", requestId);

        chain.doFilter(request, response);
    }
}
