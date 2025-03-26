package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatusAndCreateAtBetween(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCustomerAndCreateAtBetween(Customer customer, LocalDateTime start, LocalDateTime end);
    Order findByOrderID(Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_products SET total_price = ?2 WHERE orderid = ?1", nativeQuery = true)
    void updateTotalPrice(Integer orderId, Double totalPrice);
}
