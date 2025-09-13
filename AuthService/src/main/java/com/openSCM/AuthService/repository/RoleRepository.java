package com.openscm.authservice.repository;

import com.openscm.authservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByType(String name);
    Optional<Role> findByType(String type);
}
