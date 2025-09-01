package com.openscm.supplierservice.factory;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.event.SupplierCreatedEvent;

public class SupplierFactory {

    public static Supplier createSupplierEntity(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setSupplierType(dto.getSupplierType());
        supplier.setAddress(dto.getAddress());
        supplier.setCity(dto.getCity());
        return supplier;
    }

    public static SupplierResponseDTO createSupplierResponse(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getSupplierCode(),
                supplier.getName(),
                supplier.getEmail()
        );
    }

    public static SupplierCreatedEvent createSupplierEvent(Supplier supplier) {
        return new SupplierCreatedEvent(
                supplier.getSupplierCode(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getSupplierType()
        );
    }
}
