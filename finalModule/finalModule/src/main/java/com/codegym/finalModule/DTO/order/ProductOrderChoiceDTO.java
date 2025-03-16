package com.codegym.finalModule.DTO.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderChoiceDTO {

    private Integer productId;
    private String productName;
    private Double productPrice;
    private String productCPU;
    private String productRom;

    @Override
    public String toString() {
        return "ProductOrderChoiceDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCPU='" + productCPU + '\'' +
                ", productRom='" + productRom +
                '}';
    }
}
