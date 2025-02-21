package com.codegym.finalModule.DTO.product;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ProductDTO {

    private Integer productID;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 3, max = 100, message = "Tên sản phẩm phải có từ 3 đến 100 ký tự")
    private String name;

    @NotBlank(message = "URL hình ảnh không được để trống")
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg)$",
            message = "URL hình ảnh không hợp lệ")
    private String imageUrl;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Positive(message = "Giá sản phẩm phải là số dương")
    private Double price;

    @Size(max = 500, message = "Mô tả không được quá 500 ký tự")
    private String description;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Getters and Setters
    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
