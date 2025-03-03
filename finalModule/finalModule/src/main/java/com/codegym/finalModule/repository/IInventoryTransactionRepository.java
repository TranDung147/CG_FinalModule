package com.codegym.finalModule.repository;

import com.codegym.finalModule.enums.TransactionType;
import com.codegym.finalModule.model.InventoryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IInventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {

    @Query("SELECT t FROM InventoryTransaction t " +
            "JOIN t.product p " +
            "JOIN t.supplier s " +
            "WHERE (:field IS NULL OR " +
            "   (:field = 'productName' AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) OR " +
            "   (:field = 'supplierName' AND LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            ") " +
            "AND (:transactionType IS NULL OR t.transactionType = :transactionType) " +
            "AND (:fromDate IS NULL OR t.createdAt >= :fromDate) " +
            "AND (:toDate IS NULL OR t.createdAt <= :toDate)" +
            "ORDER BY t.createdAt DESC")
    Page<InventoryTransaction> filterTransactions(
            @Param("field") String field,
            @Param("keyword") String keyword,
            @Param("transactionType") TransactionType transactionType,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );



}
