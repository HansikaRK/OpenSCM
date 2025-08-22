package com.openscm.authservice.controller;

import com.openscm.authservice.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    
    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<String>> testEndPoint() {
        log.info("Hello endpoint accessed");
        ApiResponse<String> response = ApiResponse.success("Hello endpoint working", "Auth service waves hello");
        return ResponseEntity.ok(response);
    }
}
