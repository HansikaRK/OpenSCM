package com.openscm.apigateway.filter;

import com.openscm.apigateway.util.JwtUtil;
import com.openscm.apigateway.util.JwtUtil.JwtValidationException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            String msg = "Missing or invalid Authorization header";
            log.error(msg);
            return Mono.error(new JwtValidationException(msg));
        }

        String token = authHeader.substring(7); // Removing "Bearer " prefix

        try {
            // Validating the token and get the subject
            String username = JwtUtil.validateToken(token);
            log.info("JWT token valid for user: {}", username);

            // Extracting claims if needed (roles, permissions, etc.)
            Claims claims = JwtUtil.extractClaims(token);
            log.debug("Extracted claims: {}", claims);

        } catch (JwtValidationException e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return Mono.error(e);
        }

        // Proceeding to downstream service
        return chain.filter(exchange);
    }
}
