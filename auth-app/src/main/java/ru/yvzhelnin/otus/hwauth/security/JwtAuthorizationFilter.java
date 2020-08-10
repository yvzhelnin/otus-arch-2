package ru.yvzhelnin.otus.hwauth.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.yvzhelnin.otus.hwauth.exception.CrudAppAuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final JwtTokenHandler jwtTokenHandler;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  AuthenticationEntryPoint authenticationEntryPoint,
                                  JwtTokenHandler jwtTokenHandler) {
        super(authenticationManager);
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(request);
        } catch (SignatureException ex) {
            LOGGER.warn(ex.getMessage());
            authenticationEntryPoint.commence(request, response, new CrudAppAuthenticationException("Невалидная подпись токена"));
            return;
        } catch (MalformedJwtException ex) {
            LOGGER.warn(ex.getMessage());
            authenticationEntryPoint.commence(request, response, new CrudAppAuthenticationException("Невалидный токен"));
            return;
        } catch (ExpiredJwtException ex) {
            LOGGER.warn(ex.getMessage());
            authenticationEntryPoint.commence(request, response, new CrudAppAuthenticationException("Истек срок жизни токена"));
            return;
        } catch (UnsupportedJwtException ex) {
            LOGGER.warn(ex.getMessage());
            authenticationEntryPoint.commence(request, response, new CrudAppAuthenticationException("Неподдерживаемый токен"));
            return;
        }

        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)) {
            jwtTokenHandler.validateToken(token);
            String username = jwtTokenHandler.getSubject(token);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (StringUtils.isNotEmpty(username)) {
                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
        }
        return null;
    }
}
