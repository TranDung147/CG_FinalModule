package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.ProductOrderChoiceDTO;
import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer productID);
    Product saveProduct(Product product);
    ProductDetail saveProductDetail(ProductDetail productDetail);
    void saveProductWithImages(Product product, List<ProductImage> productImages);
    void deleteProduct(List<Integer> productIds);
    List<ProductImage> saveProductImages(List<ProductImage> productImages);
    Product saveProductWithDetailsAndImages(Product product, ProductDetail productDetail, List<MultipartFile> files);
    List<ProductDTO> getProductsDTOByKeyword(String keyword);
    Product findById(Integer id);

    //Choose product in order
    Page<ProductOrderChoiceDTO> getAllProductsDTO(Integer page, Integer size);
    Page<ProductOrderChoiceDTO> searchProducts(String keyword, Integer page, Integer size);
}
