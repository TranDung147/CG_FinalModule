package com.codegym.finalModule.model;

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
@Table(name = "productDetails")
public class ProductDetail {
    @Id
    private Integer productDetailID;
    private String imageUrl;
    private Integer screenSize;
    private Integer camera;
    private String color;
    private String CPU;
    private String RAM;
    private String ROM;
    private String battery;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "productDetail_id")
    private Product product;
}
