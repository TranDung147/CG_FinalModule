package com.codegym.finalModule.DTO.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer employeeId;

    @NotBlank(message = "Tên không được để trống !")
    private String employeeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employeeBirthday;
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String employeeAddress;
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$" ,
            message = "Không đúng định dạng ! (Bắt đầu bằng 03,05,07,09 ,Và phải đủ 10 số)")
    private String employeePhone;
    @NotNull(message = "Cần chọn công việc cụ thể !")
    private Integer employeePosition;
    @NotBlank(message = "Tên đăng nhập không được để trống !")
    private String username ;
    @NotBlank(message = "Mật khẩu không được để trống !")
    private String password ;
    @Pattern(regexp = "^[a-z0-9]+@gmail\\.com$" , message = "Email không đúng định dạng !")
    private String email ;
}
