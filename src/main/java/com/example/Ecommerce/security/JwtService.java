package com.example.Ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private static final String JWT_SECRET = "s2IbGRdadcgNHtTJGpv5l/4k2XLf9BLLfNuF0VU11VA=";


    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(60))))
            .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes()))
            .compact();
    }
    public String extractUsername(String token){
        return Jwts.parserBuilder()
            .setSigningKey(JWT_SECRET.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
