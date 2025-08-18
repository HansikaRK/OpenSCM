package com.openscm.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {
        return ResponseEntity.ok(Map.of(
            "service", "AuthService",
            "message", "Auth service is working correctly",
            "status", "UP",
            "timestamp", System.currentTimeMillis(),
            "note", "This request went through API Gateway!"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> loginRequest) {
        String email = (String) loginRequest.get("email");
        String password = (String) loginRequest.get("password");
        
        // Simple mock authentication for testing
        if ("admin@openscm.com".equals(email) && "password123".equals(password)) {
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "token", "mock-jwt-token-" + System.currentTimeMillis(),
                "user", Map.of(
                    "id", 1,
                    "email", email,
                    "name", "Admin User"
                ),
                "gateway_route", "/auth/login -> AUTH-SERVICE"
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Invalid credentials",
                "gateway_route", "/auth/login -> AUTH-SERVICE"
            ));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
            "service", "AuthService",
            "status", "UP",
            "version", "1.0.0",
            "timestamp", System.currentTimeMillis(),
            "endpoints", Map.of(
                "login", "POST /auth/login",
                "test", "GET /auth/test",
                "health", "GET /auth/health"
            )
        ));
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
            "service", "AuthService",
            "description", "Authentication and Authorization Service",
            "version", "1.0.0",
            "gateway_integration", "Connected via API Gateway on /auth/** routes",
            "available_endpoints", Map.of(
                "health_check", "GET /auth/health",
                "service_test", "GET /auth/test", 
                "authentication", "POST /auth/login",
                "service_info", "GET /auth/info"
            )
        ));
    }
}
