package com.openscm.supplierservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierDTO {
    @NotBlank(message = "Supplier name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 50, message = "Phone number can be max 50 characters")
    private String phone;

    @NotBlank(message = "Supplier type is required")
    private String supplierType;

    private String address;

    private String city;

}
