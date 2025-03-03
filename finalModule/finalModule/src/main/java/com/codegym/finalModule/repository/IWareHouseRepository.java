package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IWareHouseRepository extends JpaRepository<WareHouse, Integer> {
    WareHouse findByProductAndSupplier(Product product, Supplier supplier);
    @Query("SELECT w FROM WareHouse w LEFT JOIN FETCH w.supplier LEFT JOIN FETCH w.product WHERE " +
            "(:field = 'supplierName' AND w.supplier.name LIKE %:keyword%) OR " +
            "(:field = 'productName' AND w.product.name LIKE %:keyword%)")
    Page<WareHouse> searchWareHouses(@Param("field") String field,
                                     @Param("keyword") String keyword,
                                     Pageable pageable);

}
