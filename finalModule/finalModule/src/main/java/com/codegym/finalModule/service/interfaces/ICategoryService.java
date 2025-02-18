package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    List<Category> findByNameContaining(String keyword);
}
