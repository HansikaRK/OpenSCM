package com.openscm.authservice.listener;

import com.openscm.authservice.event.SupplierCreatedEvent;
import com.openscm.authservice.service.AuthService;
import com.openscm.authservice.service.EmailService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SupplierEventListener {

    private final AuthService authService;
    private final EmailService emailService;

    public SupplierEventListener(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @RabbitListener(queues = "supplierQueue")
    public void handleSupplierCreated(SupplierCreatedEvent event) {
        try {
            log.info("Received supplier created event for: {}", event.getEmail());
            
            // 1. Create user in Auth DB
            String password = authService.createSupplierUser(
                    event.getEmail(),
                    event.getName(),
                    "SUPPLIER"
            );

            // 2. Send email with credentials
            emailService.sendSupplierCredentials(event.getEmail(), event.getName(), password);
            
            log.info("Successfully processed supplier created event for: {}", event.getEmail());
        } catch (Exception e) {
            log.error("Failed to process supplier created event for: {}", event.getEmail(), e);
            // Consider implementing retry mechanism or dead letter queue
        }
    }
}
