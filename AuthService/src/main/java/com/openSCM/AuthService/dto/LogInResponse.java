package com.openscm.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponse {
    
    private String token;
    private String userId;
    private String userName;
    private LocalDateTime loginTime;
}