package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.OrderDetailDTO;
import com.codegym.finalModule.DTO.order.OrderHistoryRq;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    //    Page<CustomerDTO> searchCustomers(String keyword, String filter, Integer page, Integer size);
    List<Order> getCompletedOrders(LocalDateTime startDate, LocalDateTime endDate);
    long getTotalCompletedOrders(LocalDateTime startDate, LocalDateTime endDate);
    double getTotalRevenue(LocalDateTime startDate, LocalDateTime endDate);

    Integer saveOrder(OrderDTO orderDTO);
    Page<CustomerDTO> getAllCustomersDTO(Integer page, Integer size);
//    Page<CustomerDTO> searchCustomers(String keyword, String filter, Integer page, Integer size);
    OrderDTO getOrderDTOById(Integer orderId);
    Order getOrderById(Integer id);
    List<OrderHistoryRq> getAllOrderHistoryRqByCustomer(Customer customer);
    List<OrderDetailDTO> getAllOrderDetailDTOByCustomer(int orderId);

}
