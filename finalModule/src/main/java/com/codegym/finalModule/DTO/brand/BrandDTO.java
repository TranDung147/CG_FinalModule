package com.codegym.finalModule.DTO.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BrandDTO  {

    private Integer brandID;

    @NotBlank(message = "Tên thương hiệu không được để trống")
    @Size(min = 2, max = 100, message = "Tên thương hiệu phải từ 2 đến 100 ký tự")
    private String name;

    @Size(max = 500, message = "Mô tả thương hiệu tối đa 500 ký tự")
    private String description;
    @Size(max = 255, message = "URL ảnh không được dài quá 255 ký tự")
    private String imageUrl;

    @Size(max = 100, message = "Tên quốc gia không được dài quá 100 ký tự")
    private String country;

    private LocalDateTime createAt = LocalDateTime.now(); // Gán giá trị mặc định
    private LocalDateTime updateAt = LocalDateTime.now(); // Gán giá trị mặc định



}
