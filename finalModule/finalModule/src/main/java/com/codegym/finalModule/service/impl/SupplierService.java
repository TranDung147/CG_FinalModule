package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.repository.ISupplierRepository;
import com.codegym.finalModule.service.interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public Page<Supplier> getSuppliers(int page, int size) {
        return supplierRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setName(supplierDetails.getName());
                    supplier.setAddress(supplierDetails.getAddress());
                    supplier.setPhone(supplierDetails.getPhone());
                    supplier.setEmail(supplierDetails.getEmail());
                    supplier.setSupplierCode(supplierDetails.getSupplierCode()); // Added this line
                    return supplierRepository.save(supplier);
                }).orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    @Transactional
    public void deleteSuppliers(List<Long> ids) {
        supplierRepository.deleteByIdIn(ids);
    }


    // Cài đặt phương thức tìm kiếm
    @Override
    public List<Supplier> searchSuppliers(String name, String supplierCode) {
        return supplierRepository.findByNameContainingOrSupplierCodeContaining(name, supplierCode);
    }
}