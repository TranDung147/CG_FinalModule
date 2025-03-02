package com.codegym.finalModule.model;


import com.codegym.finalModule.enums.ProductStockStatus;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ware_house")
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Không cho phép null
    private Product product;


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    private Integer quantity;
    private Double price;

    @Enumerated(EnumType.STRING)
    private ProductStockStatus status_stock; // Enum trạng thái kho

    // GETTER hiển thị trạng thái bằng tiếng Việt
    public String getDisplayStatus() {
        if (status_stock == null) {
            return "Không xác định";
        }
        switch (status_stock) {
            case IN_STOCK:
                return "Còn hàng";
            case LOW_STOCK:
                return "Sắp hết";
            case OUT_OF_STOCK:
                return "Hết hàng";
            default:
                return "Không xác định";
        }
    }


}