package ru.yvzhelnin.otus.hwauth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.yvzhelnin.otus.hwauth.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@Getter
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-in-ms-for-access-token}")
    private long jwtExpirationInMsForAccessToken;

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(jwtSecret.getBytes(), jwtExpirationInMsForAccessToken);
    }
}
