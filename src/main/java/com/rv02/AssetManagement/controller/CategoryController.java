package com.rv02.AssetManagement.controller;

import com.rv02.AssetManagement.exceptionHandling.DataNotFoundException;
import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for Category Management.
 * Category endpoint: /api/category
 */
@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    /**
     * Category service to connect to the service layer
     */
    private CategoryService categoryService;

    /**
     * Provide service class beans.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * List All Categories
     * @return List of Category
     */
    @GetMapping
    public List<Category> getCategoriesList() {
        return categoryService.getCategories();
    }

    /**
     * Creates a category with valid data
     * @param category Valid category json to be added
     * @return 200 HttpStus on creation
     */
    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    /**
     *
     * @param id Id of the category to be updated
     * @param newCategory Updated category json body
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id,
                                                   @Valid @RequestBody Category newCategory) {
        Category old = categoryService.getCategory(id)
                .orElseThrow(DataNotFoundException::new);
        Category updated = categoryService.getUpdatedCategory(old, newCategory);
        return ResponseEntity.ok(updated);
    }
}
