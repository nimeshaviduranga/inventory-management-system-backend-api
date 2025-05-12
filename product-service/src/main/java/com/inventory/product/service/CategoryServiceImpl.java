package com.inventory.product.service;

import com.inventory.product.dto.CategoryDTO;
import com.inventory.product.dto.ProductDTO;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.model.Category;
import com.inventory.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Check if category name already exists
        if (isCategoryExists(categoryDTO.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryDTO.getName());
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return mapToDTO(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = findCategoryById(id);
        return mapToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = findCategoryById(id);

        // Check if name is being changed and if new name already exists
        if (!existingCategory.getName().equals(categoryDTO.getName()) &&
                isCategoryExists(categoryDTO.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryDTO.getName());
        }

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public boolean isCategoryExists(String name) {
        return categoryRepository.existsByName(name);
    }

    private CategoryDTO mapToDTO(Category category) {
        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .sku(product.getSku())
                        .price(product.getPrice())
                        .imageUrl(product.getImageUrl())
                        .categoryId(category.getId())
                        .categoryName(category.getName())
                        .build())
                .collect(Collectors.toList());

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .products(productDTOs)
                .build();
    }
}