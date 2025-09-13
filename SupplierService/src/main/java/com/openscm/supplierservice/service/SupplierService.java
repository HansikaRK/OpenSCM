package com.openscm.supplierservice.service;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.event.UserRegisteredEvent;
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

    // ------------------ Event-driven Supplier Creation ------------------
    @Transactional
    public void createSupplierFromUserRegistration(UserRegisteredEvent event) {
        log.info("Creating supplier from user registration event for: {}", event.getEmail());
        
        // Check if supplier with email already exists
        if (supplierRepository.existsByEmail(event.getEmail())) {
            log.warn("Supplier creation skipped: Email {} already exists", event.getEmail());
            return; // Skip creation if already exists
        }

        try {
            // Create supplier entity from event data
            Supplier supplier = new Supplier();
            supplier.setName(event.getBusinessName() != null ? event.getBusinessName() : event.getUsername());
            supplier.setEmail(event.getEmail());
            supplier.setPhone(event.getPhoneNumber());
            supplier.setSupplierType("GENERAL"); // Default type, can be updated later
            supplier.setAddress(event.getCompanyAddress());
            supplier.setCity(null); // Will be extracted or set later
            
            // Save supplier
            Supplier savedSupplier = supplierRepository.save(supplier);
            
            // Generate and update supplier code
            savedSupplier.setSupplierCode(IdGenerator.generateSupplierCodeFromId(savedSupplier.getId()));
            Supplier updatedSupplier = supplierRepository.save(savedSupplier);
            
            log.info("Supplier created from user registration with ID: {} and code: {}", 
                     updatedSupplier.getId(), updatedSupplier.getSupplierCode());

            // Publish supplier created event (for other services that might need it)
            SupplierCreatedEvent supplierEvent = SupplierFactory.createSupplierEvent(updatedSupplier);
            rabbitTemplate.convertAndSend("supplierExchange", "supplier.created", supplierEvent);
            log.info("Supplier created event published for: {}", updatedSupplier.getEmail());

        } catch (Exception e) {
            log.error("Failed to create supplier from user registration for: {}", event.getEmail(), e);
            throw new RuntimeException("Failed to create supplier from user registration", e);
        }
    }

}
