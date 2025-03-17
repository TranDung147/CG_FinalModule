package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.model.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    void saveOrder(OrderDTO orderDTO);

    Page<CustomerDTO> getAllCustomersDTO(Integer page, Integer size);

    //    Page<CustomerDTO> searchCustomers(String keyword, String filter, Integer page, Integer size);
    List<Order> getCompletedOrders(LocalDateTime startDate, LocalDateTime endDate);
    long getTotalCompletedOrders(LocalDateTime startDate, LocalDateTime endDate);
    double getTotalRevenue(LocalDateTime startDate, LocalDateTime endDate);
}
