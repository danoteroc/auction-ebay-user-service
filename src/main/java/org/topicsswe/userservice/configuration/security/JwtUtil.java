package org.topicsswe.userservice.configuration.security;

import io.jsonwebtoken.*;

import java.util.Date;

// https://medium.com/@thecodebean/implementing-jwt-authentication-in-a-spring-boot-application-5a7a94d785d1
public class JwtUtil {
    private static final String SECRET = "your-secret-key"; //TODO move to .yaml
    private static final long EXPIRATION_TIME = 1_800_000; // 30 mins
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}