package com.openscm.authservice.config;

import com.openscm.authservice.entity.Role;
import com.openscm.authservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] roles = {"ADMIN", "CUSTOMER", "SUPPLIER"};

        for (String roleName : roles) {
            if (!roleRepository.existsByType(roleName)) {
                roleRepository.save(new Role(roleName));
            }
        }
    }
}

