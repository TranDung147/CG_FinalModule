package com.codegym.finalModule.DTO.order;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductChosen {
    private Integer productId;
    private String productDetail;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được dài quá 100 ký tự")
    private String productName;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "1000.0", message = "Giá sản phẩm phải lớn hơn 1000 VND")
    @Digits(integer = 10, fraction = 2, message = "Giá sản phẩm không hợp lệ")
    private String productPrice;

    private Integer quantity;
}
