package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    Integer saveOrder(OrderDTO orderDTO);
    Page<CustomerDTO> getAllCustomersDTO(Integer page, Integer size);
    Page<CustomerDTO> searchCustomers(String keyword, String filter, Integer page, Integer size);
    OrderDTO getOrderDTOById(Integer orderId);
}
