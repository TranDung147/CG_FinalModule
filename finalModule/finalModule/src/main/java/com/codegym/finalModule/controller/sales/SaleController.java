package com.codegym.finalModule.controller.sales;

import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.Sale;
import com.codegym.finalModule.service.interfaces.IOrderService;
import com.codegym.finalModule.service.interfaces.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/report")
    public String viewSalesReport(@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  Model model) {
        // 🔹 Sync orders to sales before generating report
        saleService.syncCompletedOrdersToSales();

        // Process date input
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : LocalDate.now().minusDays(30);
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : LocalDate.now();

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);

        // Get completed orders
        List<Order> orders = orderService.getCompletedOrders(startDateTime, endDateTime);
        long totalOrders = orderService.getTotalCompletedOrders(startDateTime, endDateTime);
        double totalRevenue = orderService.getTotalRevenue(startDateTime, endDateTime);

        // Get sales data
        List<Sale> sales = saleService.getSales(start, end);
        long totalSalesOrders = saleService.getTotalOrders(start, end);
        double totalSalesRevenue = saleService.getTotalRevenue(start, end).doubleValue();

        // Send data to view
        model.addAttribute("orders", orders);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("sales", sales);
        model.addAttribute("totalSalesOrders", totalSalesOrders);
        model.addAttribute("totalSalesRevenue", totalSalesRevenue);
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);

        return "admin/sales/sales_report";
    }

}
