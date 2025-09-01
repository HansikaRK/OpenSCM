package com.openscm.supplierservice.repository;

import com.openscm.supplierservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
    boolean existsByEmail(String email);
    
    boolean existsBySupplierCode(String supplierCode);

}
