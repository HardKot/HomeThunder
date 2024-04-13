package com.homethunder.homethunder.infrastructure.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSigningKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;


    public String generateToken(UserDetailsImpl userDetails) {
        Date currentDate = new Date();
        return Jwts.builder()
                .subject(userDetails.getEmail())
                .claim("rules", userDetails.getAuthorities())
                .claim("uid", userDetails.getId())
                .issuedAt(currentDate)
                .expiration(new Date(currentDate.getTime() + jwtLifeTime.toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetailsImpl userDetails) {
        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(userDetails.getEmail()) && !isTokenExpired(token);
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public UUID extractUID(String token) {
        return extractAllClaims(token).get("uid", UUID.class);
    }

    public ArrayList<String> extractRule(String token) {
        return extractAllClaims(token).get("rules", ArrayList.class);
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
