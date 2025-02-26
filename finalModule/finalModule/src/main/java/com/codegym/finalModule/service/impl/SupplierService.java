package com.codegym.finalModule.service.impl;


import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.repository.ISupplierRepository;
import com.codegym.finalModule.service.interfaces.ISupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService <Supplier> {
    private final ISupplierRepository supplierRepository;
    public SupplierService(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public Page<Supplier> getSuppliers(int page, int size) {
        return supplierRepository.findAll(PageRequest.of(page - 1, size));
    }
}
