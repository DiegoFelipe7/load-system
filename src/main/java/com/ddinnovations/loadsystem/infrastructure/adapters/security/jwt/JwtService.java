package com.ddinnovations.loadsystem.infrastructure.adapters.security.jwt;

import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(UserDetails userDetails, long duration) {
        var user = (UserEntity) userDetails;
        return Jwts.builder()
                .subject(user.getId())
                .claim("state", user.isState())
                .claim("roles", user.getRoles())
                .claim("permissions", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + duration * 60 * 60 * 1000))
                .signWith(getKey(secret))
                .compact();
    }

    public String extractUserName(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
