package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategories();
    List<Category> findByNameContaining(String keyword);
    Optional<Category> getCategoryById(Integer categoryId);
    void saveCategory(Category category);
    void deleteCategory(List<Integer> categoryIds);
}
