package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.repository.ICategoryRepository;
import com.codegym.finalModule.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
