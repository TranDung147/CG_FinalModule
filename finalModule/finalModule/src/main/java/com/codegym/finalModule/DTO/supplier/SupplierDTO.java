package com.codegym.finalModule.DTO.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDTO {

    private Long id;

    @NotBlank(message = "Mã nhà cung cấp không được để trống")
    @Size(min = 3, max = 20, message = "Mã nhà cung cấp phải có từ 3 đến 20 ký tự")
    private String supplierCode;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 50, message = "Tên phải có từ 2 đến 50 ký tự")
    private String name;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Số điện thoại không hợp lệ, vui lòng nhập đúng định dạng")
    private String phone;

    @Email(message = "Email không hợp lệ, vui lòng nhập đúng định dạng")
    private String email;
}
