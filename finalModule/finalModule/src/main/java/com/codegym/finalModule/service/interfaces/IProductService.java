package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.order.ProductChosen;
import com.codegym.finalModule.DTO.order.ProductOrderChoiceDTO;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer productID);
    void deleteProduct(List<Integer> productIds);
    Product findById(Integer id);
     void updateProduct(Product product, List<MultipartFile> files);
    //Choose product in order
    Page<ProductOrderChoiceDTO> getProducts(String keyword, Integer page, Integer size);
    ProductChosen getProductByIdUseInOrder(Integer id);


    Product saveProduct(Product product);
    ProductDetail saveProductDetail(ProductDetail productDetail);
    void saveProductWithImages(Product product, List<ProductImage> productImages);
    List<ProductImage> saveProductImages(List<ProductImage> productImages);
    Product saveProductWithDetailsAndImages(Product product, ProductDetail productDetail, List<MultipartFile> files);
    List<Product> getProductsBySupplier(Integer supplierId);
}
