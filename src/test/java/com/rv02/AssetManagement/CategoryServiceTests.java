package com.rv02.AssetManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Category updatedCategory = new Category(2, "Stationery", "Pens, Pencils, etc");
        Mockito.when(categoryRepository.save(prevCategory)).thenReturn(updatedCategory);
        assertEquals(updatedCategory, categoryService.getUpdatedCategory(prevCategory, updatedCategory));
    }
}
