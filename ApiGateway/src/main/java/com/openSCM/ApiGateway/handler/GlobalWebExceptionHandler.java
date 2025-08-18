package com.openscm.apigateway.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2) // Make sure it runs before default exception handlers
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (!exchange.getResponse().isCommitted()) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = Map.of(
                    "status", 404,
                    "message", "Endpoint not found",
                    "path", exchange.getRequest().getPath().value()
            );

            try {
                byte[] bytes = objectMapper.writeValueAsBytes(body);
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse().bufferFactory().wrap(bytes))
                );
            } catch (Exception e) {
                // fallback in case JSON serialization fails
                return Mono.error(e);
            }
        }
        return Mono.error(ex);
    }
}
