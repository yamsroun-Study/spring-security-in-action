package yamsroun.ssiach9.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import yamsroun.ssiach9.web.data.HttpHeader;

import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String requestId = request.getHeader(HttpHeader.REQUEST_ID.code());
        log.info("Successfully authenticated request with id {}", requestId);

        filterChain.doFilter(request, response);
    }
}
