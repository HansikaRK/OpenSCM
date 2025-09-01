package com.openscm.supplierservice.service;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private SupplierService supplierService;

    private SupplierDTO supplierDTO;

    @BeforeEach
    void setUp() {
        supplierDTO = new SupplierDTO();
        supplierDTO.setName("Test Supplier");
        supplierDTO.setEmail("test@supplier.com");
        supplierDTO.setPhone("1234567890");
        supplierDTO.setSupplierType("MANUFACTURER");
        supplierDTO.setAddress("123 Test St");
        supplierDTO.setCity("Test City");
    }

    @Test
    void addSupplier_Success() {
        // Given
        when(supplierRepository.existsByEmail(supplierDTO.getEmail())).thenReturn(false);
        
        Supplier savedSupplier = new Supplier();
        savedSupplier.setId(1L);
        savedSupplier.setName(supplierDTO.getName());
        savedSupplier.setEmail(supplierDTO.getEmail());
        savedSupplier.setPhone(supplierDTO.getPhone());
        savedSupplier.setSupplierType(supplierDTO.getSupplierType());
        savedSupplier.setAddress(supplierDTO.getAddress());
        savedSupplier.setCity(supplierDTO.getCity());
        
        when(supplierRepository.save(any(Supplier.class))).thenReturn(savedSupplier);

        // When
        SupplierResponseDTO result = supplierService.addSupplier(supplierDTO);

        // Then
        assertNotNull(result);
        assertEquals("SUP-000001", result.getSupplierCode());
        assertEquals(supplierDTO.getName(), result.getName());
        assertEquals(supplierDTO.getEmail(), result.getEmail());
        
        verify(supplierRepository, times(2)).save(any(Supplier.class));
        verify(rabbitTemplate).convertAndSend(eq("supplierExchange"), eq("supplier.created"), any(Object.class));
    }

    @Test
    void addSupplier_EmailAlreadyExists_ThrowsException() {
        // Given
        when(supplierRepository.existsByEmail(supplierDTO.getEmail())).thenReturn(true);

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> supplierService.addSupplier(supplierDTO)
        );
        
        assertEquals("Supplier with this email already exists", exception.getMessage());
        verify(supplierRepository, never()).save(any(Supplier.class));
        verify(rabbitTemplate, never()).convertAndSend(anyString(), anyString(), any(Object.class));
    }
}
