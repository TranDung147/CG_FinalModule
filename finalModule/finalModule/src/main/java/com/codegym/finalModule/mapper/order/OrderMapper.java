package com.codegym.finalModule.mapper.order;

import com.codegym.finalModule.DTO.order.OrderDetailDTO;
import com.codegym.finalModule.DTO.order.OrderDetailRevenueDTO;
import com.codegym.finalModule.DTO.order.OrderHistoryRq;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class OrderMapper {

    public OrderHistoryRq toOrderHistoryRq(Order order) {

        return OrderHistoryRq.builder()
                .id(order.getOrderID())
                .orderStatus(order.getStatus().toString())
                .orderDate(order.getCreateAt())
                .totalPrice(order.getTotalPrice())
                .paymentMethod(order.getPayment().getPaymentMethod().toString())
                .build();
    }
    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .productQuantity(orderDetail.getQuantity())
                .productPrice(orderDetail.getPrice())
                .productName(orderDetail.getProduct().getName())
                .build();
    }
}
