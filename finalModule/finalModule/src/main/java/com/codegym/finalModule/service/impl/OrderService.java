package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderDTO;
import com.codegym.finalModule.enums.OrderStatus;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import com.codegym.finalModule.repository.IOrderDetailRepository;
import com.codegym.finalModule.repository.IOrderRepository;
import com.codegym.finalModule.service.interfaces.IOrderService;
import com.codegym.finalModule.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService implements IOrderService {

    @Autowired
    CustomerService customerService;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    IProductService productService;

    @Override
    public void saveOrder(OrderDTO orderDTO) {

        Order order= new Order();

        //customer
        Integer customerID = orderDTO.getCustomerId();
        Customer customer = customerService.getCustomerById(customerID);
        order.setCustomer(customer);

        order.setTotalPrice(0.00);
        order.setStatus(OrderStatus.PENDING);
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());
        Order saveOrder = orderRepository.save(order);

        Integer orderID = saveOrder.getOrderID();

        //orderDetails
        for ( ProductOrderDTO productOrderDTO : orderDTO.getProductOrderDTOList()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(saveOrder);
            orderDetail.setProduct(productService.findById(productOrderDTO.getProductId()));
            orderDetail.setQuantity(productOrderDTO.getQuantity());
            orderDetail.setPrice((double)productOrderDTO.getPriceIndex());
            saveOrderDetail(orderDetail);
        }

    }

    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

}
