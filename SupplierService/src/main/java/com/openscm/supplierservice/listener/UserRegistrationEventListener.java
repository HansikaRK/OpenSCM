package com.openscm.supplierservice.listener;

import com.openscm.supplierservice.event.UserRegisteredEvent;
import com.openscm.supplierservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationEventListener {
    
    private final SupplierService supplierService;

    @RabbitListener(queues = "supplier.registration.queue")
    public void handleSupplierRegistration(UserRegisteredEvent event) {
        try {
            log.info("Received supplier registration event for user: {}", event.getUsername());
            
            // Only process if it's a supplier registration
            if ("SUPPLIER".equals(event.getRole())) {
                supplierService.createSupplierFromUserRegistration(event);
                log.info("Successfully processed supplier registration for user: {}", event.getUsername());
            } else {
                log.warn("Received non-supplier event in supplier queue. Role: {}", event.getRole());
            }
            
        } catch (Exception e) {
            log.error("Failed to process supplier registration event for user: {}", 
                     event.getUsername(), e);
            // Consider implementing retry mechanism or dead letter queue
            throw e; // Rethrow to trigger retry mechanism if configured
        }
    }
}
