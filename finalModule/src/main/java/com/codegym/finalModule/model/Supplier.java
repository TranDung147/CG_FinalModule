package com.codegym.finalModule.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WareHouse> wareHouses = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(Long id, String supplierCode, String name, String address, String phone, String email, List<WareHouse> wareHouses) {
        this.id = id;
        this.supplierCode = supplierCode;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.wareHouses = wareHouses;
    }

    // Getters and Setters (unchanged)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WareHouse> getWareHouses() {
        return wareHouses;
    }

    public void setWareHouses(List<WareHouse> wareHouses) {
        this.wareHouses = wareHouses;
    }
}