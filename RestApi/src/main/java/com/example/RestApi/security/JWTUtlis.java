package com.example.RestApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Component
public class JWTUtlis {

    private static String securityKey;

    JWTUtlis() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; //256bits
        secureRandom.nextBytes(key);
        securityKey = Base64.getEncoder().encodeToString(key);
    }

    private Key getSignedKey() {
        byte[] keyBite = Decoders.BASE64.decode(securityKey);
        return Keys.hmacShaKeyFor(keyBite);
    }


    public String generateToken(String username, List<String> roles) {
        return builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isValidToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {

        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimResolver.apply(claims);
    }


}
