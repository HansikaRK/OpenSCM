package com.openscm.supplierservice.service;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.repository.SupplierRepository;
import com.openscm.supplierservice.util.IdGenerator;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public SupplierResponseDTO addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setSupplierType(supplierDTO.getSupplierType());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setCity(supplierDTO.getCity());

        Supplier savedSupplier = supplierRepository.save(supplier);

        savedSupplier.setSupplierCode(IdGenerator.generateSupplierCodeFromId(savedSupplier.getId()));
        Supplier updatedSupplier = supplierRepository.save(savedSupplier);

        return new SupplierResponseDTO(
                updatedSupplier.getSupplierCode(),
                updatedSupplier.getName(),
                updatedSupplier.getEmail()
        );
    }

}
