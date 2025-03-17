package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCustomerAndCreateAtBetween(Customer customer, LocalDateTime start, LocalDateTime end);
    Order findByOrderID(Integer orderId);
}
