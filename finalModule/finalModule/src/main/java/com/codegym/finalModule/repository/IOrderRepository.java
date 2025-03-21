package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatusAndCreateAtBetween(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate);
    // ✅ Fix: Add this method to find orders by status
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCustomerAndCreateAtBetween(Customer customer, LocalDateTime start, LocalDateTime end);
    Order findByOrderID(Integer orderId);
}
