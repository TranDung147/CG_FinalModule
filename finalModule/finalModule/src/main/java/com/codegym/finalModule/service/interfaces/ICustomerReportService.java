package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.CustomerReport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ICustomerReportService {
    Page<CustomerReport> findAll(Pageable pageable);
    boolean updateCustomerReportByDate(Customer customer, LocalDate date);
    Page<CustomerReport> getAllCustomerReports(Pageable pageable);

    int updateCustomerReportsByDateRange(LocalDate fromDate, LocalDate toDate);
    CustomerReport getReportByCustomerId(Integer customerId);

    void deleteCustomerReport(List<Integer> reportId);
    void editCustomerReport(Integer reportId, Integer totalOrders, Double totalSpent, Integer totalProductsPurchased);
    Page<CustomerReport> findByDateBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);
    void exportCustomerReportsToExcel(HttpServletResponse response) throws IOException;
    List<CustomerReport> getReportsByDateRange(LocalDate fromDate, LocalDate toDate);
    Page<CustomerReport> getCustomerReportsByDateRange(LocalDate fromDate, LocalDate toDate, Pageable pageable);
}
