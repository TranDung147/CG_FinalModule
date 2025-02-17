package com.codegym.finalModule.dto;

import jakarta.validation.constraints.NotBlank;
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

    private String employeePhone;
    private String employeeEmail;
    private String employeeWork;
}
