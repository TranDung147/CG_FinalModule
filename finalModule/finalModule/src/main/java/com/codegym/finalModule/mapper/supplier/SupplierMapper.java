package com.codegym.finalModule.mapper.supplier;


import com.codegym.finalModule.DTO.supplier.SupplierDTO;
import com.codegym.finalModule.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier convertToSupplier(SupplierDTO supplierDTO) {
        return Supplier.builder()
                .name(supplierDTO.getName())
                .phone(supplierDTO.getPhone())
                .address(supplierDTO.getAddress())
                .email(supplierDTO.getEmail())
                .build();
    }
}
