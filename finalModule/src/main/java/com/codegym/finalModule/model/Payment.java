package com.codegym.finalModule.model;

import com.codegym.finalModule.enums.OrderStatus;
import com.codegym.finalModule.enums.PaymentMethod;
import com.codegym.finalModule.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentID;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;
}
