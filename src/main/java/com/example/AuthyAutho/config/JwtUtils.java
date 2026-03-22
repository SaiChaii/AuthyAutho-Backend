package com.example.AuthyAutho.config;

import com.example.AuthyAutho.logging.AppLogger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final AppLogger _logger = new AppLogger(JwtUtils.class);

    // In a real SDE 1 role, you would put this in application.properties
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final int jwtExpirationMs = 86400000; // 24 hours

    public String generateToken(String username) {
        _logger.logInformation("Generating JWT token for user: {}", username);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
        _logger.logInformation("JWT token generated successfully for user: {}", username);
        return token;
    }

    public String getUsernameFromToken(String token) {
        _logger.logInformation("Extracting username from JWT token");
        String username = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
        _logger.logInformation("Username extracted from token: {}", username);
        return username;
    }

    public boolean validateToken(String token) {
        _logger.logInformation("Validating JWT token");
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            _logger.logInformation("JWT token is valid");
            return true;
        } catch (Exception e) {
            _logger.logWarning("JWT token validation failed: {}", e.getMessage());
            return false;
        }
    }
}