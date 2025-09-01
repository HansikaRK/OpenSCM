package com.openscm.supplierservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierCreatedEvent {
    private String supplierCode;
    private String name;
    private String email;
    private String supplierType;
}