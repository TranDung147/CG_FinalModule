package com.codegym.finalModule.dto.customer;

import com.codegym.finalModule.vatidator.customer.DobConstraint;
import com.codegym.finalModule.vatidator.customer.UniquePhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    @NotBlank(message = "Tên không được để trống !")
    @Size(min = 5 , message = "Tên tối thiểu là 5 kí tự !")
    private String fullName ;
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$" ,
            message = "Không đúng định dạng ! (Bắt đầu bằng 03,05,07,09 ,Và phải đủ 10 số)")
    @UniquePhone(message = "Số điện thoại đã tồn tại !")
    private String phone;
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được để trống !")
    @DobConstraint(min = 15 , message = "Không đủ điều kiện , tuổi phải lớn hơn 15")
    private LocalDate birthDate;
}
