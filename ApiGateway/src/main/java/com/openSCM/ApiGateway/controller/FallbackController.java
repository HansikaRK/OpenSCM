package com.openscm.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public ResponseEntity<Map<String, Object>> handleFallback() {
        return ResponseEntity.status(404).body(Map.of(
                "success", false,
                "message", "Route not found",
                "timestamp", System.currentTimeMillis()
        ));
    }
}
