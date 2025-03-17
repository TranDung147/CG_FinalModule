package com.codegym.finalModule.service.interfaces;

public interface IPaymentService {
    Integer addPayment(Integer orderId, Integer paymentMethod);
    void updatePayment(Integer paymentId, Integer amount);
}
