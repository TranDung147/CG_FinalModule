package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findBySupplierCode(String supplierCode);
    List<Supplier> findByNameContainingOrSupplierCodeContaining(String name, String supplierCode);
    void deleteByIdIn(List<Integer> ids);
}