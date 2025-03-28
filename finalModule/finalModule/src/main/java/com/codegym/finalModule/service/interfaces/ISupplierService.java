package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    Page<Supplier> getSuppliers(int page, int size);

    List<Supplier> getAllSuppliers();

    Optional<Supplier> getSupplierById(Integer id);

    Supplier addSupplier(Supplier supplier);

    Supplier updateSupplier(Integer id, Supplier supplierDetails);

    void deleteSuppliers(List<Integer> ids);

    List<Supplier> searchSuppliers(String name, String supplierCode);

    // Thêm method mới
    List<Supplier> searchSuppliersByAllFields(String supplierCode, String name, String address, String phone, String email);
}