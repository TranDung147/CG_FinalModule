package com.codegym.finalModule.service.interfaces;


import com.codegym.finalModule.DTO.report.CustomerReportDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ICustomerReportService {
    Page<CustomerReportDTO> getCustomerReportsByDateRange(LocalDate fromDate, LocalDate toDate, Pageable pageable); // Thêm phân trang
    long countTotalCustomersByDateRange(LocalDate fromDate, LocalDate toDate);
    void exportCustomerReportsToExcel(HttpServletResponse response, LocalDate fromDate, LocalDate toDate) throws IOException; // Cập nhật

}