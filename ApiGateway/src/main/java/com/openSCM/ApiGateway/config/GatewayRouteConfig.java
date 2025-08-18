package com.openscm.apigateway.config;

import com.openscm.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public GatewayRouteConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                // Public Auth routes (NO authentication filters are used here)
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f
                                .rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                        .uri("lb://AUTH-SERVICE"))

                // Protected Order routes
                .route("order-service", r -> r.path("/orders/**")
                        .filters(f -> f
                                .rewritePath("/orders/(?<segment>.*)", "/${segment}")
                                .filter(jwtAuthenticationFilter))
                        .uri("lb://ORDER-SERVICE"))

                // Protected Inventory routes
                .route("inventory-service", r -> r.path("/inventory/**")
                        .filters(f -> f
                                .rewritePath("/inventory/(?<segment>.*)", "/${segment}")
                                .filter(jwtAuthenticationFilter))
                        .uri("lb://INVENTORY-SERVICE"))

                // Protected Product routes
                .route("product-service", r -> r.path("/products/**")
                        .filters(f -> f
                                .rewritePath("/products/(?<segment>.*)", "/${segment}")
                                .filter(jwtAuthenticationFilter))
                        .uri("lb://PRODUCT-SERVICE"))

                .build();
    }

}
