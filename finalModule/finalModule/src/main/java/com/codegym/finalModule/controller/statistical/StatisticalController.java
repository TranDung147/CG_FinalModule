package com.codegym.finalModule.controller.statistical;

import com.codegym.finalModule.DTO.product.ProductStatisticalDTO;
import com.codegym.finalModule.DTO.statistical.*;
import com.codegym.finalModule.service.impl.StatisticalService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/statistical")
public class StatisticalController {

    private final StatisticalService statisticalService;
    public StatisticalController(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }


    @GetMapping
    public String showOverView(@RequestParam(required = false) String type){
        switch (type.toLowerCase()) {
            case "day":
                return "admin/statistical/sales_report_day";
            case "month":
                return "admin/statistical/sales_report_month";
            case "year":
                return "admin/statistical/sales_report_year";
        }
        return "admin/statistical/sales_report_day";

    }
    @GetMapping( "/api/revenue" )
    @ResponseBody
    public ResponseEntity<OverViewRevenueDTO> getRevenueSummaryByDay(
            @RequestParam(required = false ) String type,
            @RequestParam(required = false ) Integer day,
            @RequestParam(required = false ) Integer month,
            @RequestParam(required = false ) Integer year) {

        LocalDate today = LocalDate.now();
        int selectedDay = (day != null) ? day : today.getDayOfMonth();
        int selectedMonth = (month != null) ? month : today.getMonthValue();
        int selectedYear = (year != null) ? year : today.getYear();

        
        RevenueSummaryDTO revenueSummaryDTO = null;
        List<DailyRevenueDTO> dailyRevenueDTOList = new ArrayList<>();
        List<TopSellingProductDTO> topSellingProductDTOList = new ArrayList<>();

        switch (type.toLowerCase()) {
            case "day":
                 revenueSummaryDTO = this.statisticalService.getRevenueSummary(
                        this.statisticalService.getListOrdersByDate(selectedDay, selectedMonth, selectedYear));
                 dailyRevenueDTOList = this.statisticalService.getDailyRevenue(
                        this.statisticalService.getListOrdersByDate(0, selectedMonth, selectedYear) , "day");
                 topSellingProductDTOList = this.statisticalService.getTopSellingProduct(
                        this.statisticalService.getListOrdersByDate(0, selectedMonth, selectedYear));
                break;
            case "month":
                revenueSummaryDTO = this.statisticalService.getRevenueSummary(
                        this.statisticalService.getListOrdersByDate(0, selectedMonth, selectedYear));
                dailyRevenueDTOList = this.statisticalService.getDailyRevenue(
                        this.statisticalService.getListOrdersByDate(0, 0, selectedYear) , "month");
                topSellingProductDTOList = this.statisticalService.getTopSellingProduct(
                        this.statisticalService.getListOrdersByDate(0, 0, selectedYear));
                break;
            case "year":
                revenueSummaryDTO = this.statisticalService.getRevenueSummary(
                        this.statisticalService.getListOrdersByDate(0, 0, selectedYear));
                dailyRevenueDTOList = this.statisticalService.getDailyRevenue(
                        this.statisticalService.getAllOrders() , "year");
                topSellingProductDTOList = this.statisticalService.getTopSellingProduct(
                        this.statisticalService.getAllOrders());
                break;
        }

        return ResponseEntity.ok( OverViewRevenueDTO.builder()
                .revenueSummary(revenueSummaryDTO)
                .dailyRevenue(dailyRevenueDTOList)
                .topSellingProducts(topSellingProductDTOList)
                .build()) ;
    }

    @GetMapping("/orders/detail")
    public String getOrdersDetail (@RequestParam(required = false ) String type,
                                   @RequestParam(required = false ) Integer day,
                                   @RequestParam(required = false ) Integer month,
                                   @RequestParam(required = false ) Integer year,
                                   @RequestParam(required = false , defaultValue = "1") Integer page ,
                                   @RequestParam(required = false , defaultValue = "2") Integer size,
                                   Model model) {

        switch (type.toLowerCase()) {
            case "day":
                model.addAttribute("orders", this.statisticalService.getOrderDetailRevenue
                        (this.statisticalService.getListOrdersByDate(day, month, year) , page, size));
                break;
            case "month":
                model.addAttribute("orders", this.statisticalService.getOrderDetailRevenue
                        (this.statisticalService.getListOrdersByDate(0, month, year) , page, size));
                break;
            case "year":
                model.addAttribute("orders", this.statisticalService.getOrderDetailRevenue
                        (this.statisticalService.getListOrdersByDate(0, 0, year) , page, size));
                break;
        }
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("type", type);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);


    return "admin/statistical/order-detail";
    }
    @GetMapping("/products/detail")
    public String getProductsSalesDetail (@RequestParam(required = false ) String type,
                                   @RequestParam(required = false ) Integer day,
                                   @RequestParam(required = false ) Integer month,
                                   @RequestParam(required = false ) Integer year,
                                          @RequestParam(required = false , defaultValue = "1") Integer page ,
                                          @RequestParam(required = false , defaultValue = "2") Integer size,
                                   Model model) {
        Page<ProductStatisticalDTO> productStatisticalDTOList = null;
        switch (type.toLowerCase()) {
            case "day":
                productStatisticalDTOList = this.statisticalService.getProductStatistical(
                        this.statisticalService.getListOrdersByDate(day, month, year) ,page, size);
                break;
              case "month":
                  productStatisticalDTOList = this.statisticalService.getProductStatistical(
                          this.statisticalService.getListOrdersByDate(0, month, year) , page, size);
                  break;
              case "year":
                  productStatisticalDTOList = this.statisticalService.getProductStatistical(
                          this.statisticalService.getListOrdersByDate(0, 0, year) , page, size);
                  break;
        }
        long totalProductsSales = 0;
        assert productStatisticalDTOList != null;
        for (ProductStatisticalDTO productStatisticalDTO : productStatisticalDTOList.getContent()) {
            totalProductsSales += productStatisticalDTO.getStock();
        }
        model.addAttribute("page", page) ;
        model.addAttribute("size", size) ;
        model.addAttribute("type", type) ;
        model.addAttribute("totalProductsSales", totalProductsSales);
        model.addAttribute("products" , productStatisticalDTOList);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "admin/statistical/products-detail";
    }
    @GetMapping("/revenue/detail")
    public String getRevenueDetail (@RequestParam(required = false ) String type,
                                    @RequestParam(required = false ) Integer day,
                                    @RequestParam(required = false ) Integer month,
                                    @RequestParam(required = false ) Integer year,
                                    @RequestParam(required = false , defaultValue = "1") Integer page ,
                                    @RequestParam(required = false , defaultValue = "2") Integer size,
                                    Model model) {

        Page<RevenueDetailDTO> revenueDetailDTOS = null ;
        switch (type.toLowerCase()) {
            case "day":
                revenueDetailDTOS = this.statisticalService.getRevenueDetail
                        (this.statisticalService.getListOrdersByDate(day, month, year) , page, size);
                break;
            case "month":
                revenueDetailDTOS = this.statisticalService.getRevenueDetail
                        (this.statisticalService.getListOrdersByDate(0, month, year) , page, size);
                break;
            case "year":
                revenueDetailDTOS = this.statisticalService.getRevenueDetail
                        (this.statisticalService.getListOrdersByDate(0, 0, year) , page, size);
                break;
        }

        double totalRevenue = 0.0;
        double totalCost = 0.0;
        double profitRate = 0.0;

        assert revenueDetailDTOS != null;
        for (RevenueDetailDTO revenueDetailDTO : revenueDetailDTOS) {
            totalRevenue += revenueDetailDTO.getTotalSellingPrice();
            totalCost += revenueDetailDTO.getTotalImportCost();
            profitRate += revenueDetailDTO.getProfitRate();
        }
        model.addAttribute("page", page) ;
        model.addAttribute("size", size) ;
        model.addAttribute("type", type) ;
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("netProfit",totalRevenue-totalCost );
        model.addAttribute("profitRate", profitRate / revenueDetailDTOS.getContent().size());
        model.addAttribute("revenues", revenueDetailDTOS);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "admin/statistical/revenue-detail";

    }


}
