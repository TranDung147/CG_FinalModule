package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierCode(String supplierCode);
}

