package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IWareHouseRepository extends JpaRepository<WareHouse, Integer> {
    @Query("SELECT w FROM WareHouse w WHERE " +
            "(LOWER(w.product.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(w.supplier.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")



    Page<WareHouse> searchByProductOrSupplierAndStatus(
            @Param("keyword") String keyword,
            @Param("statusStock") String statusStock,
            Pageable pageable);
}
