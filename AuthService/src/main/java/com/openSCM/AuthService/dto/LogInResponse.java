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
public class LogInResponse {

    private Boolean success;
    private String message;
    private String token;
    private UUID userId;
    private String userName;
    private LocalDateTime loginTime;
}
