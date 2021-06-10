package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.CategoryRepository;
import com.rv02.AssetManagement.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The service layer class for Category.
 */
@Service
public class CategoryService {

    /**
     * CategoryRepository to get access to db
     */
    private CategoryRepository categoryRepository;

    /**
     *
     * @param categoryRepository
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Returns the list of categories in db
     * @return List of Category
     */
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Saves the given category and returns the saved data
     * @param category Category object to be saved in db
     * @return Returns the saved data
     */
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     *
     * @param id Category Id to be found
     * @return Optional Category data if found
     */
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    /**
     * Changes the values of old data and stores it.
     * @param oldCategory Category to be updated
     * @param newCategory Update Category
     * @return The updated category stored in db
     */
    public Category getUpdatedCategory(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        oldCategory.setDescription(newCategory.getDescription());
        return categoryRepository.save(oldCategory);
    }
}
