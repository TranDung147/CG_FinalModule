package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    Page<Supplier> getSuppliers(int page, int size);

    List<Supplier> getAllSuppliers();

    Optional<Supplier> getSupplierById(Long id);

    Supplier addSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplierDetails);

    void deleteSuppliers(List<Long> ids);

    List<Supplier> searchSuppliers(String name, String supplierCode);
}