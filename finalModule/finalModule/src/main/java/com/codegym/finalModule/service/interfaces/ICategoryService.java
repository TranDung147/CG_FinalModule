package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategories();
    Page<Category> getAllCategoriesPaged(Pageable pageable);
    List<Category> findByNameContaining(String keyword);
    Page<Category> findByNameContainingPaged(String keyword, Pageable pageable);
    Optional<Category> getCategoryById(Integer categoryId);
    void saveCategory(Category category);
    void deleteCategory(List<Integer> categoryIds);
    boolean existsByName(String name);
    boolean existsByNameAndNotId(String name, Integer id);
    boolean hasRelatedProducts(List<Integer> ids);
}
