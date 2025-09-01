package com.openscm.authservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSupplierCredentials(String email, String name, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Welcome to OpenSCM - Your Supplier Account");
            message.setText(buildSupplierWelcomeEmail(name, email, password));
            
            mailSender.send(message);
            log.info("Welcome email sent successfully to supplier: {}", email);
        } catch (Exception e) {
            log.error("Failed to send welcome email to supplier: {}", email, e);
            // Don't rethrow exception to avoid breaking the user creation process
        }
    }

    private String buildSupplierWelcomeEmail(String name, String email, String password) {
        return String.format(
            "Dear %s,\n\n" +
            "Welcome to OpenSCM! Your supplier account has been created successfully.\n\n" +
            "Your login credentials:\n" +
            "Email: %s\n" +
            "Temporary Password: %s\n\n" +
            "Please log in and change your password immediately for security reasons.\n\n" +
            "Best regards,\n" +
            "OpenSCM Team",
            name, email, password
        );
    }
}
