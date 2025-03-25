package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.enums.OrderStatus;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.Sale;
import com.codegym.finalModule.repository.IOrderRepository;
import com.codegym.finalModule.repository.ISaleRepository;
import com.codegym.finalModule.service.interfaces.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<Sale> getSales(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }

    @Override
    public long getTotalOrders(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate).size();
    }

    @Override
    public BigDecimal getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate)
                .stream()
                .map(Sale::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ✅ Ensure completed orders are added to sales table
    @Override
    public void syncCompletedOrdersToSales() {
        List<Order> completedOrders = orderRepository.findByStatus(OrderStatus.PENDING);

        for (Order order : completedOrders) {
            // Check if this order is already recorded in sales
            boolean exists = saleRepository.existsBySaleDateAndTotalPrice(order.getCreateAt().toLocalDate(), BigDecimal.valueOf(order.getTotalPrice()));
            if (!exists) {
                Sale sale = Sale.builder()
                        .saleDate(order.getCreateAt().toLocalDate())
                        .totalPrice(BigDecimal.valueOf(order.getTotalPrice()))
                        .build();
                saleRepository.save(sale);
            }
        }
    }
}
