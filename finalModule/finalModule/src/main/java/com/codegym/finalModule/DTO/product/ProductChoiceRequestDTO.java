package com.codegym.finalModule.DTO.product;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductChoiceRequestDTO {
    private List<ProductChoiceDTO> products;
}
