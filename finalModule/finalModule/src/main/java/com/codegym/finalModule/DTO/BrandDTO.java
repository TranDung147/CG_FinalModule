package com.codegym.finalModule.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return BrandDTO.class.equals(clazz);
//    }

//    @Override
//    public void validate(Object target, Errors errors) {
//        BrandDTO brandDTO = (BrandDTO) target;
//
//        // Kiểm tra nếu tên thương hiệu chứa ký tự đặc biệt
//        if (!brandDTO.getName().matches("^[a-zA-Z0-9\\s]+$")) {
//            errors.rejectValue("name", "brand.name.invalid", "Tên thương hiệu không được chứa ký tự đặc biệt");
//        }
//
//        // Kiểm tra nếu URL ảnh không hợp lệ
//        if (brandDTO.getImageUrl() != null && !brandDTO.getImageUrl().matches("^(http|https)://.*$")) {
//            errors.rejectValue("imageUrl", "brand.imageUrl.invalid", "URL ảnh phải bắt đầu bằng http:// hoặc https://");
//        }
//    }

}
