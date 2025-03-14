package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderChoiceDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    void saveOrder(OrderDTO orderDTO);
}
