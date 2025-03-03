package com.codegym.finalModule.DTO.transaction;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InventoryTransactionDTO {

    private Integer id;
    private Integer product_id;
    private Integer stockQuantity;
    private Integer quantity;
    private Integer supplier_id;
    private Integer employee_id;
    private LocalDate created_at;
    private LocalDate updated_at;

}
