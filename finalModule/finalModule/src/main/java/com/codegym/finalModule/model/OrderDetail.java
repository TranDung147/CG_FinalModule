package com.codegym.finalModule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    //Lay orderID lam khoa chinh luon
    private Integer orderDetailID; //tam thoi
    //product
    private Integer quantity;
    private Double price;
}
