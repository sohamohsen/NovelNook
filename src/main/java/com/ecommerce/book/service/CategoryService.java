package com.ecommerce.book.service;

import com.ecommerce.book.dto.CreateCategoryDTO;
import com.ecommerce.book.dto.ResponseCategoryDTO;
import com.ecommerce.book.dto.UpdateCategoryDTO;
import com.ecommerce.book.entity.Category;
import com.ecommerce.book.mapper.CategoryMapper;
import com.ecommerce.book.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseCategoryDTO addCategory(CreateCategoryDTO category) {

        // Convert DTO to Entity using MapStruct
        Category toEntity = CategoryMapper.instance.toEntity(category);

        // Convert saved entity to DTO and return
        return CategoryMapper.instance.toDTO(categoryRepo.save(toEntity));
    }

    public ResponseCategoryDTO getCategoryById(int id) {
        Optional<Category> category = categoryRepo.findById(id);
        return CategoryMapper.instance.toDTO(category.get());
    }

    public List<ResponseCategoryDTO> getAllCategories() {
        return CategoryMapper.instance.toDTO(categoryRepo.findAll());
    }

    public ResponseCategoryDTO updateCategory(int id, UpdateCategoryDTO category) {
        // Validate input
        if (id <= 0) {
            throw new IllegalArgumentException("Category id cannot be null or empty");
        }
                // Check if the category exists
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));
        // Map data from UpdateCategoryDTO to existing Category entity
        CategoryMapper.instance.toUpdateEntity(category, existingCategory);
        // Save the updated category
        Category updatedCategory = categoryRepo.save(existingCategory);
        // Return the updated category as a DTO
        return CategoryMapper.instance.toDTO(updatedCategory);
    }

    public void deleteCategory(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Category id cannot be null or empty");
        }
        Optional<Category> categoryOpt = categoryRepo.findById(id);
        if (categoryOpt.isPresent()) {
            categoryRepo.deleteById(id);
        }
    }
}