package com.openscm.authservice.controller;

import com.openscm.authservice.dto.SignUpRequest;
import com.openscm.authservice.dto.SignUpResponse;
import com.openscm.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Received signup request for username: {}", signUpRequest.getUsername());
        
        SignUpResponse response = authService.registerUser(signUpRequest);
        
        log.info("User signup successful for username: {}", signUpRequest.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        return ResponseEntity.ok(Map.of(
                "message", "Auth service is up",
                "note", "Request went through API Gateway",
                "service", "AuthService",
                "endpoint", "/auth/test"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        log.info("Login attempt for username: {}", username);
        
        return ResponseEntity.ok(Map.of(
                "message", "Login endpoint hit",
                "token", "dummy-jwt-token",
                "username", username != null ? username : "unknown"
        ));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "AuthService",
                "endpoints", "GET /auth/test, POST /auth/login, POST /auth/signup, GET /auth/health, GET /auth/info"
        ));
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> info() {
        return ResponseEntity.ok(Map.of(
                "service", "AuthService",
                "description", "Authentication service with user registration and login",
                "version", "1.0.0",
                "features", "User signup, authentication, password hashing, validation"
        ));
    }
}
