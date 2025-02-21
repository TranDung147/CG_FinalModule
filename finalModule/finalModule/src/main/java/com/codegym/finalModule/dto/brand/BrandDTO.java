package com.codegym.finalModule.DTO.brand;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDateTime;
@Data

public class BrandDTO {
    private Integer brandID;

    @NotBlank(message = "Tên thương hiệu không được để trống")
    @Size(min = 2, max = 100, message = "Tên thương hiệu phải từ 2 đến 100 ký tự")
    private String name;


    @NotBlank(message = "Xuất xứ không được để trống")
    @Size(max = 100, message = "Xuất xứ không được dài quá 100 ký tự")
    private String country;

    @Size(max = 500, message = "Mô tả thương hiệu tối đa 500 ký tự")
    private String description;
    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime updateAt = LocalDateTime.now();

    public BrandDTO() {
    }

    public BrandDTO(LocalDateTime updateAt, LocalDateTime createAt, String country, String description, String name, Integer brandID) {
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.country = country;
        this.description = description;
        this.name = name;
        this.brandID = brandID;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
