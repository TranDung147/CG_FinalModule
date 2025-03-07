package com.codegym.finalModule.DTO.product;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductChoiceDTO {

    private Integer productId;
    private String productName;
    private String supplierName;
    private Integer productQuantity ;
    private Integer quantityInput ;
    private Double productPrice;

}
