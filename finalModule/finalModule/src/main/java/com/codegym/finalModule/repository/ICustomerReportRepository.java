package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.CustomerReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerReportRepository extends JpaRepository<CustomerReport, Integer> {
    Optional<CustomerReport> findByCustomer_CustomerId(Integer customerId);
    Optional<CustomerReport> findByCustomer_CustomerIdAndLastOrderDate(Integer customerId, LocalDateTime lastOrderDate);


    @Query("SELECT r FROM CustomerReport r WHERE r.lastOrderDate BETWEEN :startDate AND :endDate")
    List<CustomerReport> findReportsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r FROM CustomerReport r WHERE r.lastOrderDate BETWEEN :startDate AND :endDate")
    Page<CustomerReport> findReportsByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    @Query("SELECT COUNT(DISTINCT cr.customer) FROM CustomerReport cr WHERE cr.lastOrderDate BETWEEN :startDate AND :endDate")
    long countDistinctCustomersByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(DISTINCT c.customer) FROM CustomerReport c WHERE c.lastOrderDate BETWEEN :fromDate AND :toDate")
    long countTotalCustomersByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

}
