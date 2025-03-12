package com.codegym.finalModule.DTO.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePositionDTO {
    private Integer positionId;

    @NotBlank(message = "Tên chức vụ không được để trống!")
    private String positionName;

    @NotBlank(message = "Mô tả chức vụ không được để trống!")
    private String positionDescription;
}

