package com.codegym.finalModule.mapper.product;

import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setProductID(dto.getProductID());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCreateAt(dto.getCreateAt());
        product.setUpdateAt(dto.getUpdateAt());

        Category category = new Category();
        category.setCategoryID(dto.getCategoryId());
        product.setCategory(category);

        Brand brand = new Brand();
        brand.setBrandID(dto.getBrandId());
        product.setBrand(brand);

        // ✅ Gán thông tin chi tiết sản phẩm
        ProductDetail detail = new ProductDetail();
        detail.setScreenSize(dto.getScreenSize());
        detail.setCamera(dto.getCamera());
        detail.setColor(dto.getColor());
        detail.setCpu(dto.getCpu());
        detail.setRam(dto.getRam());
        detail.setRom(dto.getRom());
        detail.setBattery(dto.getBattery());

        product.setProductDetail(detail);
        return product;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setProductID(product.getProductID());
        dto.setName(product.getName());

        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCreateAt(product.getCreateAt());
        dto.setUpdateAt(product.getUpdateAt());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getCategoryID());
        }
        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getBrandID());
        }
        if (product.getProductDetail() != null) {
            dto.setScreenSize(product.getProductDetail().getScreenSize());
            dto.setCamera(product.getProductDetail().getCamera());
            dto.setColor(product.getProductDetail().getColor());
            dto.setCpu(product.getProductDetail().getCpu());
            dto.setRam(product.getProductDetail().getRam());
            dto.setRom(product.getProductDetail().getRom());
            dto.setBattery(product.getProductDetail().getBattery());
        }

        return dto;
    }


}
