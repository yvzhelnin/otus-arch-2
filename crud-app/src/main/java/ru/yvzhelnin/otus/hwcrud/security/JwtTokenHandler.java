package ru.yvzhelnin.otus.hwcrud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenHandler {

    private static final String TOKEN_TYPE_PARAMETER_NAME = "token_type";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final byte[] jwtSecret;

    public JwtTokenHandler(String jwtSecret) {
        this.jwtSecret = jwtSecret.getBytes();
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

    public String getTokenType(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(removePrefix(token))
                .getBody()
                .get(TOKEN_TYPE_PARAMETER_NAME)
                .toString();
    }

    private String removePrefix(String token) {
        return token.startsWith(TOKEN_PREFIX) ? token.substring(TOKEN_PREFIX.length()) : token;
    }
}