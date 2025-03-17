package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Sale;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

public interface ISaleService {
    List<Sale> getSales(LocalDate startDate, LocalDate endDate);
    long getTotalOrders(LocalDate startDate, LocalDate endDate);
    BigDecimal getTotalRevenue(LocalDate startDate, LocalDate endDate);

    // ✅ Ensure completed orders are added to sales table
    void syncCompletedOrdersToSales();
}
