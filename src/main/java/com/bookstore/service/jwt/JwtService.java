package com.bookstore.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // Secret key (we'll move it to application.properties later)
    private static final String SECRET =
            "bookstoremanagementsystemjwtsecretkey2026secure";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate JWT token
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // Extract email from token
    public String extractEmail(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Check token validity
    public boolean isTokenValid(String token) {

        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}