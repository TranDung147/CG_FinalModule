package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer productID);
    Product saveProduct(Product product);
    ProductDetail saveProductDetail(ProductDetail productDetail);
    void saveProductWithImages(Product product, List<ProductImage> productImages);


}
