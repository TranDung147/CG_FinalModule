package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.SalesReport;
import com.codegym.finalModule.repository.ISalesReportRepository;
import com.codegym.finalModule.service.interfaces.ISalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class SalesReportService implements ISalesReportService {

    @Autowired
    private ISalesReportRepository salesReportRepository;

    @Override
    public List<SalesReport> getSalesReport(LocalDate startDate, LocalDate endDate) {
        return salesReportRepository.findBySaleDateBetween(startDate, endDate);
    }
}
