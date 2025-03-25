package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.order.OrderDetailRevenueDTO;
import com.codegym.finalModule.DTO.product.ProductStatisticalDTO;
import com.codegym.finalModule.DTO.statistical.DailyRevenueDTO;
import com.codegym.finalModule.DTO.statistical.RevenueDetailDTO;
import com.codegym.finalModule.DTO.statistical.RevenueSummaryDTO;
import com.codegym.finalModule.DTO.statistical.TopSellingProductDTO;
import com.codegym.finalModule.model.Order;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IStatisticalService {
    RevenueSummaryDTO getRevenueSummary(List<Order> orderList);
    List<DailyRevenueDTO> getDailyRevenue(List<Order> orderList , String type);
    List<TopSellingProductDTO> getTopSellingProduct(List<Order> orderList);
    List<Order> getListOrdersByDate(int day , int month , int year);
    List<Order> getAllOrders() ;
    Page<OrderDetailRevenueDTO> getOrderDetailRevenue(List<Order> orderList , int page , int size);
    Page<ProductStatisticalDTO> getProductStatistical(List<Order> orderList, int page , int size);
    Page<RevenueDetailDTO> getRevenueDetail(List<Order> orderList , int page , int size);
    List<RevenueDetailDTO> getAllRevenueDetail(List<Order> orderList );
    Integer getTotalProductsSales(List<Order> orderList);
    Integer getStockProducts (List<Order> orderList);
    HashMap<String , Double> getTotalDetailRevenue(List<RevenueDetailDTO> revenueDetailDTOS);
}
