package com.openscm.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    // This will match any request that doesn't match existing endpoints
    @RequestMapping("/**")
    public ResponseEntity<Map<String, Object>> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", 404,
                "error", "Not Found",
                "message", "The requested endpoint does not exist",
                "timestamp", System.currentTimeMillis()
        ));
    }
}
