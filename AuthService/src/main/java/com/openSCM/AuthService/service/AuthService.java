package com.openscm.authservice.service;

import com.openscm.authservice.dto.LogInRequest;
import com.openscm.authservice.dto.LogInResponse;
import com.openscm.authservice.dto.SignUpRequest;
import com.openscm.authservice.dto.SignUpResponse;
import com.openscm.authservice.entity.Role;
import com.openscm.authservice.entity.User;
import com.openscm.authservice.exception.UserAlreadyExistsException;
import com.openscm.authservice.repository.RoleRepository;
import com.openscm.authservice.repository.UserRepository;
import com.openscm.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignUpResponse registerUser(SignUpRequest signUpRequest) {
        log.info("Starting customer registration for username: {}", signUpRequest.getUsername());

        // Check if username or email already exists (combined for security)
        if (userRepository.existsByUsername(signUpRequest.getUsername()) ||
                userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.warn("Registration failed: Username or email already exists");
            throw new UserAlreadyExistsException("Username or email already exists");
        }

        // Validating password confirmation
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Role customerRole = roleRepository.findByType("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));


        // Creating new user entity
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setPhoneNumber(signUpRequest.getContactNumber());
        user.setRole(customerRole);

        // Save user
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());

        // Return success response
        return SignUpResponse.builder()
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().getType())
                .registrationTime(savedUser.getCreatedAt())
                .build();
    }

    public LogInResponse login(LogInRequest logInRequest){
        String loginInput = logInRequest.getLoginInput();
        String password = logInRequest.getPassword();

        User user = userRepository.findByUsernameOrEmail(loginInput, loginInput)
                .orElseThrow(() -> {
                    log.warn("Login attempt failed: User does not exist");
                    return new com.openscm.authservice.exception.UsernameNotFoundException("User not found");
                });

        if(!passwordEncoder.matches(password, user.getPassword())){
            log.warn("Login attempt failed: Incorrect password for user {}", loginInput);
            throw new IllegalArgumentException("Incorrect password");
        }

        log.info("User {} logged in successfully", loginInput);

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername());

        return LogInResponse.builder()
                .token(token)
                .userId(user.getId())
                .userName(user.getUsername())
                .loginTime(LocalDateTime.now())
                .build();
    }
}