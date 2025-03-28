package com.codegym.finalModule.model;

import com.codegym.finalModule.enums.OrderStatus;
import com.codegym.finalModule.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_Products")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String codeOrder;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;



}
