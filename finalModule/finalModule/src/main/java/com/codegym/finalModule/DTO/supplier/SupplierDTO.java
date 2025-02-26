package com.codegym.finalModule.DTO.supplier;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    private Integer id;
    @NotBlank(message = "Tên không được để trống !")
    private String name;
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$" ,
            message = "Không đúng định dạng ! (Bắt đầu bằng 03,05,07,09 ,Và phải đủ 10 số)")
    private String phone;
    @Pattern(regexp = "^[a-z0-9]+@gmail\\.com$" , message = "Email không đúng định dạng !")
    private String email;
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String address;
    private LocalDateTime createdAt;
}
