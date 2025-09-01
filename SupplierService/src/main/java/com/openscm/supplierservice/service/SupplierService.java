package com.openscm.supplierservice.service;

import com.openscm.supplierservice.dto.SupplierDTO;
import com.openscm.supplierservice.entity.Supplier;
import com.openscm.supplierservice.dto.SupplierResponseDTO;
import com.openscm.supplierservice.factory.SupplierFactory;
import com.openscm.supplierservice.repository.SupplierRepository;
import com.openscm.supplierservice.util.IdGenerator;
import com.openscm.supplierservice.event.SupplierCreatedEvent;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final RabbitTemplate rabbitTemplate;

    public SupplierService(SupplierRepository supplierRepository, RabbitTemplate rabbitTemplate) {
        this.supplierRepository = supplierRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public SupplierResponseDTO addSupplier(SupplierDTO supplierDTO) {

        Supplier supplier = SupplierFactory.createSupplierEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        savedSupplier.setSupplierCode(IdGenerator.generateSupplierCodeFromId(savedSupplier.getId()));
        Supplier updatedSupplier = supplierRepository.save(savedSupplier);

        SupplierCreatedEvent event = SupplierFactory.createSupplierEvent(updatedSupplier);
        rabbitTemplate.convertAndSend("supplierExchange", "supplier.created", event);

        return SupplierFactory.createSupplierResponse(updatedSupplier);
    }

}
