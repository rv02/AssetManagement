package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.CategoryRepository;
import com.rv02.AssetManagement.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    public Category getUpdatedCategory(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        oldCategory.setDescription(newCategory.getDescription());
        return categoryRepository.save(oldCategory);
    }
}
