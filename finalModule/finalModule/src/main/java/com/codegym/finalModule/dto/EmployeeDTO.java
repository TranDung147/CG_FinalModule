package com.codegym.finalModule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    @NotBlank(message = "Tên không được để trống !")
    private String employeeName;
    private LocalDate employeeBirthday;
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String employeeAddress;
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$" ,
            message = "Không đúng định dạng ! (Bắt đầu bằng 03,05,07,09 ,Và phải đủ 10 số)")
    private String employeePhone;
    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$\n" ,
            message = "Email không đúng định dạng ! (example12@gmail.com , 134yourName@gmail.com)")
    private String employeeEmail;
    @NotBlank(message = "Cần điền công việc cụ thể !")
    private String employeeWork;
}
