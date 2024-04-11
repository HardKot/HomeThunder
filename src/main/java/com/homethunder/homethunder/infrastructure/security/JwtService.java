package com.homethunder.homethunder.infrastructure.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String generateToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .header()
                .and()
                .issuer("homeThunder")
                .subject(userDetails.getEmail())
                .claim("rules", userDetails.getAuthorities())
                .claim("uid", userDetails.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetailsImpl userDetails) {
        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(userDetails.getEmail()) && !isTokenExpired(token);
    }

    private String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload()   ;
    }
}
