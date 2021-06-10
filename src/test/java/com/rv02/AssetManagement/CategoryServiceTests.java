package com.rv02.AssetManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.rv02.AssetManagement.dao.CategoryRepository;
import com.rv02.AssetManagement.model.Category;
import com.rv02.AssetManagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class CategoryServiceTests {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategoryTest() {
        Category category = new Category(1, "Electronics",
                "All electronics assets under this category");
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoryService.addCategory(category));
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void getAllCategoriesTest() {
        Mockito.when(categoryRepository.findAll()).thenReturn(Stream.
                of(new Category(1, "Electronics", "All electronics assets under this category"),
                   new Category(2, "Stationery", "Pens, Pencils, etc")
                ).collect(Collectors.toList()));
        assertEquals(2, categoryService.getCategories().size());
    }

    @Test
    public void updateCategory() {
        Category prevCategory = new Category(2, "Stationary", "");
        Mockito.when(categoryRepository.save(prevCategory)).thenReturn(prevCategory);
        Category updatedCategory = new Category(2, "Stationery", "Pens, Pencils, etc");
        assertEquals(updatedCategory.getDescription(), categoryService
                .getUpdatedCategory(prevCategory, updatedCategory)
                .getDescription());
        verify(categoryRepository, times(1)).save(prevCategory);
    }
}
