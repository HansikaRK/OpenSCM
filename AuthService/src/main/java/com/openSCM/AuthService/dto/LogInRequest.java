package com.openscm.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LogInRequest {
    @NotBlank(message = "Enter username or email")
    private String loginInput;

    @NotBlank(message = "Enter the password")
    private String password;


}
