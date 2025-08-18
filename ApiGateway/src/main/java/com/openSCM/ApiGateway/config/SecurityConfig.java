package com.openscm.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable() // disable CSRF for simplicity
                .authorizeExchange()
                .pathMatchers("/auth/**").permitAll() // allow public access to auth endpoints
                .anyExchange().authenticated();       // all other routes require authentication

        return http.build();
    }
}