package com.openscm.supplierservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @GetMapping("/health")
    public String health() {
        return "Supplier Service is running!";
    }

    @GetMapping("/products")
    public String getProducts() {
        return "Products endpoint - Implementation coming soon";
    }
}