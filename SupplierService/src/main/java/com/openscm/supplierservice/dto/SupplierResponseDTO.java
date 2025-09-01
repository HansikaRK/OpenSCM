package com.openscm.supplierservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierResponseDTO {
    private String supplierCode;
    private String name;
    private String email;
}
