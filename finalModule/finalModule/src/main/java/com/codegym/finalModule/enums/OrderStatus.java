package com.codegym.finalModule.enums;

public enum  OrderStatus {
    PENDING,      // Đang chờ xử lý
    PROCESSING,   // Đang xử lý
    SHIPPED,      // Đã giao hàng cho bên vận chuyển
    DELIVERED,    // Đã giao thành công
    CANCELLED     // Đã hủy
}
