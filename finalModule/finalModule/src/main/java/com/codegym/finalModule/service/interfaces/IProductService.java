package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.ProductChosen;
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
    void deleteProduct(List<Integer> productIds);
    Product saveProductWithDetailsAndImages(Product product, ProductDetail productDetail, List<MultipartFile> files);
    Product findById(Integer id);
    void updateProduct(Product product, List<MultipartFile> files);

    //Choose product in order
    Page<ProductOrderChoiceDTO> getProducts(String keyword, Integer page, Integer size);
    ProductChosen getProductByIdUseInOrder(Integer id);

}
