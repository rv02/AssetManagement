package com.rv02.AssetManagement.controller;

import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategoriesList() {
        return categoryService.getCategories();
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }
}
