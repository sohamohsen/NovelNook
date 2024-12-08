package com.ecommerce.book.service;

import com.ecommerce.book.dto.CreateCategoryDTO;
import com.ecommerce.book.dto.ResponseCategoryDTO;
import com.ecommerce.book.dto.UpdateCategoryDTO;
import com.ecommerce.book.entity.Category;
import com.ecommerce.book.mapper.CategoryMapper;
import com.ecommerce.book.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseCategoryDTO addCategory(CreateCategoryDTO category) {
        // Check if category name is null or empty
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        // Convert DTO to Entity using MapStruct
        Category toEntity = CategoryMapper.instance.toEntity(category);

        // Convert saved entity to DTO and return
        return CategoryMapper.instance.toDTO(categoryRepo.save(toEntity));
    }

    public ResponseCategoryDTO getCategoryById(int id) {
        Optional<Category> category = categoryRepo.findById(id);
        // Handle the case when category is not found
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category with ID " + id + " not found");
        }
        return CategoryMapper.instance.toDTO(category.get());
    }

    public List<ResponseCategoryDTO> getAllCategories() {
        // Retrieve all categories
        List<Category> categories = categoryRepo.findAll();

        // Map categories to DTOs
        return CategoryMapper.instance.toResponseCategoryDTOList(categories);
    }

    public ResponseCategoryDTO updateCategory(int id, UpdateCategoryDTO category) {
        // Validate input
        if (id <= 0) {
            throw new IllegalArgumentException("Category id cannot be null or empty");
        }
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        // Check if the category exists
        Optional<Category> existingCategoryOpt = categoryRepo.findById(id);

        if (existingCategoryOpt.isPresent()) {
            // If the category exists, update its fields
            Category existingCategory = existingCategoryOpt.get();

            // Map the data from UpdateCategoryDTO to the existing entity
            existingCategory.setName(category.getName());
            if (category.getDescription() != null) {
                existingCategory.setDescription(category.getDescription());
            }

            // Save the updated category
            Category updatedCategory = categoryRepo.save(existingCategory);

            // Return the updated category as a DTO
            return CategoryMapper.instance.toDTO(updatedCategory);
        }

        // If category doesn't exist, return null or handle differently
        return null; // or you can return a default ResponseCategoryDTO
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
