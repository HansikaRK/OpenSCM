package com.openscm.authservice.controller;

import com.openscm.authservice.dto.*;
import com.openscm.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponse>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Received signup request for username: {}", signUpRequest.getUsername());
        
        SignUpResponse response = authService.registerUser(signUpRequest);
        ApiResponse<SignUpResponse> apiResponse = ApiResponse.success("User registered successfully", response);
        
        log.info("User signup successful for username: {}", signUpRequest.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LogInResponse>> login(@Valid @RequestBody LogInRequest loginRequest) {
        String loginInput = loginRequest.getLoginInput();
        log.info("Login attempt for user: {}", loginInput);

        LogInResponse response = authService.login(loginRequest);
        ApiResponse<LogInResponse> apiResponse = ApiResponse.success("Login successful", response);
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<Map<String, String>>> test() {
        log.info("Test endpoint accessed");
        
        Map<String, String> testData = Map.of(
                "message", "Auth service is up",
                "note", "Request went through API Gateway",
                "service", "AuthService",
                "endpoint", "/auth/test"
        );
        
        ApiResponse<Map<String, String>> apiResponse = ApiResponse.success("Test endpoint working", testData);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/test-protected")
    public ResponseEntity<ApiResponse<Map<String, String>>> testProtected() {
        log.info("Protected test endpoint accessed successfully");

        Map<String, String> testData = Map.of(
                "message", "The protected request reached the endpoint",
                "note", "Request for the protected endpoint successful",
                "service", "AuthService",
                "endpoint", "/auth/test-protected"
        );

        ApiResponse<Map<String, String>> apiResponse = ApiResponse.success("Protected test endpoint working", testData);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        log.info("Health check endpoint accessed");
        
        Map<String, String> healthData = Map.of(
                "status", "UP",
                "service", "AuthService",
                "endpoints", "GET /auth/test, POST /auth/login, POST /auth/signup, GET /auth/health, GET /auth/info"
        );
        
        ApiResponse<Map<String, String>> apiResponse = ApiResponse.success("Service is healthy", healthData);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<Map<String, String>>> info() {
        log.info("Info endpoint accessed");
        
        Map<String, String> infoData = Map.of(
                "service", "AuthService",
                "description", "Authentication service with user registration and login",
                "version", "1.0.0",
                "features", "User signup, authentication, password hashing, validation"
        );
        
        ApiResponse<Map<String, String>> apiResponse = ApiResponse.success("Service information retrieved", infoData);
        return ResponseEntity.ok(apiResponse);
    }
}
