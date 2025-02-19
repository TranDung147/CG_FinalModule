package com.codegym.finalModule.dto.employee;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Cần điền công việc cụ thể !")
    private String employeeWork;
}
