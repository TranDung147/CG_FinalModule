package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISalesReportRepository extends JpaRepository<SalesReport, Long> {
    List<SalesReport> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}
