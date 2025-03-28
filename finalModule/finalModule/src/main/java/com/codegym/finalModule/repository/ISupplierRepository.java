package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findBySupplierCode(String supplierCode);
    List<Supplier> findByNameContainingOrSupplierCodeContaining(String name, String supplierCode);

    // Thêm method tìm kiếm theo tất cả các trường
    @Query("SELECT s FROM Supplier s WHERE " +
            "(:supplierCode IS NULL OR s.supplierCode LIKE %:supplierCode%) AND " +
            "(:name IS NULL OR s.name LIKE %:name%) AND " +
            "(:address IS NULL OR s.address LIKE %:address%) AND " +
            "(:phone IS NULL OR s.phone LIKE %:phone%) AND " +
            "(:email IS NULL OR s.email LIKE %:email%)")
    List<Supplier> searchSuppliers(@Param("supplierCode") String supplierCode,
                                   @Param("name") String name,
                                   @Param("address") String address,
                                   @Param("phone") String phone,
                                   @Param("email") String email);

    void deleteByIdIn(List<Integer> ids);
}