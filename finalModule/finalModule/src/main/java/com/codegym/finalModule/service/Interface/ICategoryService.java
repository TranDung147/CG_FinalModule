package com.codegym.finalModule.service.Interface;

import com.codegym.finalModule.model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    List<Category> findByNameContaining(String keyword);
}
