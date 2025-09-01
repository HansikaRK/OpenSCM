package com.openscm.supplierservice.service;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.factory.SupplierFactory;
import com.openscm.supplierservice.repository.SupplierRepository;
import com.openscm.supplierservice.util.IdGenerator;
import com.openscm.supplierservice.event.SupplierCreatedEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final RabbitTemplate rabbitTemplate;

    public SupplierService(SupplierRepository supplierRepository, RabbitTemplate rabbitTemplate) {
        this.supplierRepository = supplierRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public SupplierResponseDTO addSupplier(SupplierDTO supplierDTO) {
        log.info("Starting supplier creation process for email: {}", supplierDTO.getEmail());
        
        // Check if supplier with email already exists
        if (supplierRepository.existsByEmail(supplierDTO.getEmail())) {
            log.warn("Supplier creation failed: Email {} already exists", supplierDTO.getEmail());
            throw new IllegalArgumentException("Supplier with this email already exists");
        }

        try {
            // Create and save supplier
            Supplier supplier = SupplierFactory.createSupplierEntity(supplierDTO);
            Supplier savedSupplier = supplierRepository.save(supplier);
            
            // Generate and update supplier code
            savedSupplier.setSupplierCode(IdGenerator.generateSupplierCodeFromId(savedSupplier.getId()));
            Supplier updatedSupplier = supplierRepository.save(savedSupplier);
            log.info("Supplier saved successfully with ID: {} and code: {}", 
                     updatedSupplier.getId(), updatedSupplier.getSupplierCode());

            // Publish event for auth service
            SupplierCreatedEvent event = SupplierFactory.createSupplierEvent(updatedSupplier);
            rabbitTemplate.convertAndSend("supplierExchange", "supplier.created", event);
            log.info("Supplier created event published for: {}", updatedSupplier.getEmail());

            return SupplierFactory.createSupplierResponse(updatedSupplier);
        } catch (Exception e) {
            log.error("Failed to create supplier for email: {}", supplierDTO.getEmail(), e);
            throw new RuntimeException("Failed to create supplier", e);
        }
    }

}
