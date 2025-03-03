package com.codegym.finalModule.DTO.warehouse;

import com.codegym.finalModule.enums.ProductStockStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO {

    @NotNull(message = "Sản phẩm không được để trống")
    private Integer productId;

    @NotNull(message = "Nhà cung cấp không được để trống")
    private Long supplierId;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Giá nhập không được để trống")
    @Min(value = 1, message = "Giá nhập phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Trạng thái kho không được để trống")
    private ProductStockStatus statusStock;
}

