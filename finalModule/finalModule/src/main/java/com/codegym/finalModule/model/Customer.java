package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;
}
