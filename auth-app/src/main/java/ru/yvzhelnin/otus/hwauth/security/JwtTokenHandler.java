package ru.yvzhelnin.otus.hwauth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ru.yvzhelnin.otus.hwauth.dto.JwtResponse;
import ru.yvzhelnin.otus.hwauth.enums.TokenType;
import ru.yvzhelnin.otus.hwauth.model.Client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenHandler {

    private static final String TOKEN_TYPE_PARAMETER_NAME = "token_type";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final byte[] jwtSecret;
    private final long jwtExpirationInMsForAccessToken;

    public JwtTokenHandler(String jwtSecret, long jwtExpirationInMsForAccessToken) {
        this.jwtSecret = jwtSecret.getBytes();
        this.jwtExpirationInMsForAccessToken = jwtExpirationInMsForAccessToken;
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(removePrefix(token))
                .getBody();

        return claims.getSubject();
    }

    public void validateToken(String token) {
        Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(removePrefix(token));
    }

    private String removePrefix(String token) {
        return token.startsWith(TOKEN_PREFIX) ? token.substring(TOKEN_PREFIX.length()) : token;
    }

    public JwtResponse generateToken(Client client) {
        Date now = new Date();
        Map<String, Object> tokenClaims = new HashMap<>();

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
