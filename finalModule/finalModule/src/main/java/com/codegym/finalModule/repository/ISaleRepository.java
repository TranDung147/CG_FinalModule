package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);

    // ✅ Check if an order already exists in sales table
    boolean existsBySaleDateAndTotalPrice(LocalDate saleDate, BigDecimal totalPrice);
}
