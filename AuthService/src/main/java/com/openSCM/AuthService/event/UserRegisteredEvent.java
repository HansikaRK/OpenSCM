package com.openscm.authservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent {
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
    private LocalDateTime registrationTime;
    
    // Supplier-specific fields (null for customers)
    private String businessName;
    private String companyName;
    private String companyAddress;
    private Integer leadTime;
    private String contactPerson;
}
