package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.infrastructure.db.repository.JwtRepository;
import com.homethunder.homethunder.infrastructure.db.schema.JwtSchema;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSigningKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;

    @Autowired
    private JwtRepository jwtRepository;

    public String generateToken(UserDetailsImpl userDetails, String deviceName) {
        JwtSchema jwtSchema = new JwtSchema();
        jwtSchema.setId(UUID.randomUUID());
        jwtSchema.setCreateAt(LocalDateTime.now());
        jwtSchema.setUserId(userDetails.getId());
        jwtSchema.setDeviceName(deviceName);

        Date issuedAt = Date.from(jwtSchema.getCreateAt().atZone(ZoneOffset.systemDefault()).toInstant());

        jwtRepository.save(jwtSchema);

        return Jwts.builder()
                .id(jwtSchema.getId().toString())
                .subject(userDetails.getEmail())
                .claim("rules", userDetails.getAuthorities())
                .claim("uid", userDetails.getId())
                .issuedAt(issuedAt)
                .expiration(new Date(issuedAt.getTime() + jwtLifeTime.toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token){
        return jwtRepository.findById(extractTokenID(token)).isPresent();
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public String regenerateToken(UserDetailsImpl userDetails, String token, String deviceName) {
        jwtRepository.deleteById(extractTokenID(token));
        return generateToken(userDetails, deviceName);
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
