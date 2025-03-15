package com.codegym.finalModule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    private String name;
    private String mainImageUrl;
    private Double price;
    private String description;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)  // Không cho phép null
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)  // Không cho phép null
    private Supplier supplier;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDetail productDetail;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;


    @Transient
    private String formattedPrice;

    @PostLoad
    public void formatPrice() {
        if (this.price != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
            this.formattedPrice = decimalFormat.format(this.price);
        }
    }
}
