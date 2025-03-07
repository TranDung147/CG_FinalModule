package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.SalesReport;
import java.time.LocalDate;
import java.util.List;

public interface ISalesReportService {
    List<SalesReport> getSalesReport(LocalDate startDate, LocalDate endDate);
}
