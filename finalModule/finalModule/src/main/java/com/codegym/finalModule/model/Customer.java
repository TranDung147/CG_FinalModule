package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;
}
