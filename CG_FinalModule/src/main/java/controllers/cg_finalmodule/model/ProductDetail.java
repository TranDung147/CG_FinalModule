package controllers.cg_finalmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productDetailId;

    @NotNull(message = "Kích thước không được để trống")
//    @Digits(integer = 2, fraction = 1, message = "Kích thước phải có tối đa 2 chữ số nguyên và 1 chữ số thập phân")
    private Double size;

    @Size(max = 20, message = "Màu sắc tối đa 20 ký tự")
    private String color;

    @Size(max = 50, message = "Phong cách tối đa 50 ký tự")
    private String style;

    @DecimalMin(value = "0.0", inclusive = false, message = "Giá bán lẻ phải lớn hơn 0")
//    @Digits(integer = 10, fraction = 2, message = "Giá bán lẻ không hợp lệ")
    private BigDecimal retailPrice;

    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false, unique = true)
    private Product product;
}
