package com.openscm.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse {
    
    private Boolean success;
    private String message;
    private UUID userId;
    private String username;
    private String email;
    private String role;
    private LocalDateTime registrationTime;
}
