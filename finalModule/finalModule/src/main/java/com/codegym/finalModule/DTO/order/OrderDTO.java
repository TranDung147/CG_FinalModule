package com.codegym.finalModule.DTO.order;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;

    @Valid
    private  List<ProductOrderDTO> productOrderDTOList = new ArrayList<>();

    @NotNull(message = "Phương thức thanh toán không được để trống !")
    @Min(value = 1 , message = "Phương thức thanh toán không hợp lệ !")
    @Max(value = 2 , message = "Phương thức thanh toán không hợp lệ !")
    private Integer paymentMethod;
    @Valid
    @JsonIgnore
    private CustomerDTO customerDTO;
    private Integer customerId;
    private Boolean isPrintInvoice;
    private Integer totalPrice;

    public OrderDTO() {
        productOrderDTOList.add(new ProductOrderDTO());
        customerDTO = new CustomerDTO();
        totalPrice = 0;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", productOrderDTOList=" + productOrderDTOList +
                ", paymentMethod=" + paymentMethod +
                ", customerDTO=" + customerDTO +
                ", isPrintInvoice=" + isPrintInvoice +
                '}';
    }



}
