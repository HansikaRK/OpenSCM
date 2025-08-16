package com.openscm.apigateway.config;

import com.openscm.apigateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtFilter;

    // Expose the JWT filter as a bean so it can be referenced in application.yml
    @Bean
    public WebFilter jwtGatewayFilter() {
        return jwtFilter;
    }
}