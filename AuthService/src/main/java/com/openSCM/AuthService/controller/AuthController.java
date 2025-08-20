package com.openscm.authservice.controller;

import com.openscm.authservice.dto.*;
import com.openscm.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Received signup request for username: {}", signUpRequest.getUsername());
        
        SignUpResponse response = authService.registerUser(signUpRequest);
        
        log.info("User signup successful for username: {}", signUpRequest.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> login(@Valid @RequestBody LogInRequest loginRequest) {
        String loginInput = loginRequest.getLoginInput();
        log.info("Login attempt for user: {}", loginInput);

        LogInResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
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
