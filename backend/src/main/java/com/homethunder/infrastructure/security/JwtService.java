package com.homethunder.infrastructure.security;

import com.homethunder.domain.security.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSigningKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;


    public String generateToken(Token token) {
        Date issuedAt = Date.from(token.getCreateAt().atZone(ZoneOffset.systemDefault()).toInstant());

        return Jwts.builder()
                .id(token.getId().toString())
                .subject(token.getUid().toString())
                .claim("rules", token.getRuleSet())
                .claim("uid", token.getUid().toString())
                .issuedAt(issuedAt)
                .expiration(new Date(issuedAt.getTime() + jwtLifeTime.toMillis()))
                .signWith(getSecretKey())
                .compact();
    }


    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public UUID extractUID(String token) {
        return extractAllClaims(token).get("uid", UUID.class);
    }

    public UUID extractTokenID(String token) {
        return UUID.fromString(extractAllClaims(token).getId());
    }

    public ArrayList<String> extractRule(String token) {
        return extractAllClaims(token).get("rules", ArrayList.class);
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }
}
