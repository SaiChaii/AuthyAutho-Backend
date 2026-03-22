package com.example.AuthyAutho.config;

import com.example.AuthyAutho.logging.AppLogger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AppLogger _logger = new AppLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        _logger.logInformation("Processing authentication filter for request: {} {}", request.getMethod(), request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            _logger.logInformation("Bearer token found in Authorization header, validating...");

            if (jwtUtils.validateToken(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                _logger.logInformation("Token valid. Authenticating user: {}", username);

                // Create authentication object and set it in the context
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                _logger.logInformation("Security context updated for user: {}", username);
            } else {
                _logger.logWarning("Invalid or expired JWT token received for request: {}", request.getRequestURI());
            }
        } else {
            _logger.logInformation("No Bearer token found in request to: {}", request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }
}