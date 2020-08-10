package ru.yvzhelnin.otus.hwauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.yvzhelnin.otus.hwauth.security.JwtAuthenticationEntryPoint;
import ru.yvzhelnin.otus.hwauth.security.JwtTokenHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@Getter
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST = {
            "/api/auth/login",
            "/api/auth/register",
            "/actuator/health",
            "/actuator/prometheus",
            "/info/health",
            "/info/status",
            "/info/version",
    };

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-in-ms-for-access-token}")
    private long jwtExpirationInMsForAccessToken;

    private final ObjectMapper objectMapper;

    public WebSecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public JwtTokenHandler jwtTokenProvider() {
        return new JwtTokenHandler(jwtSecret, jwtExpirationInMsForAccessToken);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }
}
