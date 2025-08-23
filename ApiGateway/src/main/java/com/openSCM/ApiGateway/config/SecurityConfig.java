package com.openscm.apigateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SecurityConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(@LoadBalanced WebClient.Builder webClientBuilder) {
        // Use load-balanced WebClient to resolve AUTH-SERVICE through Eureka
        return NimbusReactiveJwtDecoder
                .withJwkSetUri("http://AUTH-SERVICE/.well-known/jwks.json")
                .webClient(webClientBuilder.build())
                .jwsAlgorithm(SignatureAlgorithm.RS256)
                .build();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ReactiveJwtDecoder jwtDecoder) {
        http.csrf(csrf -> csrf.disable()) // disable CSRF for stateless APIs
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login", "/auth/signup", "/auth/info").permitAll() // public endpoints (login, register, etc.
                        .pathMatchers("/actuator/**").permitAll() // actuator endpoints
                        .pathMatchers("/auth/test-protected").authenticated() // protected testing endpoint
                        .pathMatchers("/orders/**", "/inventory/**", "/products/**").authenticated() // protected
                        .anyExchange().authenticated() // everything else also requires JWT
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // disable HTTP Basic
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder)) // Use custom JWT decoder with load balancer
                );

        return http.build();
    }
}
