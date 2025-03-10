package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.repository.ICategoryRepository;
import com.codegym.finalModule.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByNameContaining(String keyword) {
        return categoryRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Optional<Category> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getCategoryID() != null&& categoryRepository.existsById(category.getCategoryID())) {
            Category existingCategory = categoryRepository.findById(category.getCategoryID()).orElse(null);
            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());
                existingCategory.setUpdateAt(LocalDateTime.now());
                categoryRepository.save(existingCategory);
                category.setCreateAt(existingCategory.getCreateAt());
            }
        } else {
            category.setCreateAt(LocalDateTime.now());
            category.setUpdateAt(LocalDateTime.now());
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(List<Integer> categoryIds) {
        categoryRepository.deleteAllById(categoryIds);
    }
}
