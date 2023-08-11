package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.Category;
import com.sistema.examenes.repositories.CategoryRepository;
import com.sistema.examenes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getOnlyCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        categoryRepository.delete(category);
    }
}
