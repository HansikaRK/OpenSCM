package com.openscm.authservice.service;

import com.openscm.authservice.dto.SignUpRequest;
import com.openscm.authservice.dto.SignUpResponse;
import com.openscm.authservice.entity.User;
import com.openscm.authservice.exception.UserAlreadyExistsException;
import com.openscm.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    @Transactional
    public SignUpResponse registerUser(SignUpRequest signUpRequest) {
        log.info("Starting user registration for username: {}", signUpRequest.getUsername());
        
        // Check if username or email already exists (combined for security)
        if (userRepository.existsByUsername(signUpRequest.getUsername()) || 
            userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.warn("Registration failed: Username or email already exists for registration attempt");
            throw new UserAlreadyExistsException("Username or email already exists");
        }
        
        // Validating password confirmation
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        // Creating new user entity
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRole(User.Role.ROLE_CUSTOMER); // Fixed role for buyers
        user.setEnabled(true);
        
        // Save user to database
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());
        
        // Return success response
        return SignUpResponse.builder()
                .success(true)
                .message("User registered successfully")
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .registrationTime(savedUser.getCreatedAt())
                .build();
    }
}
