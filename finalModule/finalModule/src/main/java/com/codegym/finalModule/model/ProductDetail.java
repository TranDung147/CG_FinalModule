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
public class ProductDetail {
    @Id
    private Integer productDetailID;
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
    @MapsId // Dùng chung khóa chính với Products
    @JoinColumn(name = "productDetailID") // Liên kết với productID
    private Products product;
}
