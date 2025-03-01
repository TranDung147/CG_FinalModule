package com.codegym.finalModule.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_code", unique = true, nullable = false)
    private String supplierCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Supplier(String supplierCode, String name, String address, String phone, String email) {
        this.supplierCode = supplierCode;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
}