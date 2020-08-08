package ru.yvzhelnin.otus.hwauth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ru.yvzhelnin.otus.hwauth.dto.JwtResponse;
import ru.yvzhelnin.otus.hwauth.enums.TokenType;
import ru.yvzhelnin.otus.hwauth.model.Client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

    private static final String TOKEN_TYPE_PARAMETER_NAME = "token_type";

    private final byte[] jwtSecret;
    private final long jwtExpirationInMsForAccessToken;

    public JwtTokenProvider(byte[] jwtSecret, long jwtExpirationInMsForAccessToken) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInMsForAccessToken = jwtExpirationInMsForAccessToken;
    }

    public JwtResponse generateToken(Client client) {
        Date now = new Date();
        Map<String, Object> tokenClaims = new HashMap<>();    // for writing token type to token

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMsForAccessToken);
        tokenClaims.put(TOKEN_TYPE_PARAMETER_NAME, TokenType.ACCESS_TOKEN);

        String token = Jwts.builder()
                .addClaims(tokenClaims)
                .setSubject(client.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        return new JwtResponse("Bearer " + token);
    }
}
