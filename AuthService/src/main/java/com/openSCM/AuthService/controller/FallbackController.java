package com.openscm.authservice.controller;

import com.openscm.authservice.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallbackController {

    // This will match any request that doesn't match existing endpoints
    @RequestMapping("/**")
    public ResponseEntity<ApiResponse<Object>> handleNotFound() {
        log.warn("Endpoint not found - returning 404");
        ApiResponse<Object> response = ApiResponse.error("The requested endpoint does not exist", "ENDPOINT_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
