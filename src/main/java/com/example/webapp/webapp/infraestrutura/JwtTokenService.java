package com.example.webapp.webapp.infraestrutura;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = EnvConfig.get("JWT_SECRET");
    private static final String JWT_EXPIRATION = EnvConfig.get("JWT_EXPIRATION");

    private final Set<String> blacklist = new HashSet<>();

    private static final SecretKey KEY =
        Keys.hmacShaKeyFor(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );

    private static final long JWT_EXPIRATION_MILLIS = Long.parseLong(JWT_EXPIRATION);

    public String generateToken(String id, String user) {
        Instant expiration = Instant.now().plusMillis(JWT_EXPIRATION_MILLIS);

        return Jwts.builder()
            .setSubject(id)
            .claim("user", user)
            .setIssuedAt(new Date())
            .setExpiration(
                Date.from(expiration)
            )
            .signWith(KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    public void removeToken(String token) {
        blacklist.add(token);
    }

    public boolean isTokenValid(String token) {
        try {
            if (blacklist.contains(token)) {
                return false;
            }

            Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    public String getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.get("user", String.class);
    }    
}