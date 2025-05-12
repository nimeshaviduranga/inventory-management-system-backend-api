package com.inventory.product.service;

import com.inventory.product.dto.CategoryDTO;
import com.inventory.product.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Category findCategoryById(Long id);
    boolean isCategoryExists(String name);
}