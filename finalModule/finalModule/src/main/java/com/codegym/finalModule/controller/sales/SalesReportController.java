package com.codegym.finalModule.controller.sales;

import com.codegym.finalModule.model.SalesReport;
import com.codegym.finalModule.service.interfaces.ISalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesReportController {

    @Autowired
    private ISalesReportService salesReportService;

    @GetMapping("/report")
    public String viewSalesReport(@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  Model model) {
        LocalDate start = startDate != null ? LocalDate.parse(startDate) : LocalDate.now().minusDays(30);
        LocalDate end = endDate != null ? LocalDate.parse(endDate) : LocalDate.now();

        List<SalesReport> reports = salesReportService.getSalesReport(start, end);
        model.addAttribute("reports", reports);
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);
        return "admin/sales/sales_report";
    }
}
