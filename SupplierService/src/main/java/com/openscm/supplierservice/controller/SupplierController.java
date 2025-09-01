package com.openscm.supplierservice.controller;

import com.openscm.authservice.dto.SignUpResponse;
import com.openscm.supplierservice.dto.ApiResponse;
import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.service.SupplierService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Create a new supplier
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> addSupplier(@RequestBody @Valid SupplierDTO supplierDTO) {
        SupplierResponseDTO createdSupplier = supplierService.addSupplier(supplierDTO);

        ApiResponse<SupplierResponseDTO> apiResponse = ApiResponse.success(
                "Supplier created successfully",
                createdSupplier
        );

        log.info("Supplier added successfully: {}", createdSupplier.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // Optional: Get all suppliers
    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // Optional: Get supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }
}
