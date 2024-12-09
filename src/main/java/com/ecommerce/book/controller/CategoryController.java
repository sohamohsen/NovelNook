package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateCategoryDTO;
import com.ecommerce.book.dto.ResponseCategoryDTO;
import com.ecommerce.book.dto.UpdateCategoryDTO;
import com.ecommerce.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Removed CategoryMapper injection as it's already handled by CategoryService

    @PostMapping("/addcategory")
    public ResponseEntity<ResponseCategoryDTO> addCategory(@RequestBody CreateCategoryDTO category) {
        ResponseCategoryDTO categoryDTO = categoryService.addCategory(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/updater/{id}")
    public ResponseEntity<ResponseCategoryDTO> changeCategory(@PathVariable int id, @RequestBody UpdateCategoryDTO category) {
        ResponseCategoryDTO categoryDTO = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<ResponseCategoryDTO>> getCategory(@PathVariable int id) {
        Optional<ResponseCategoryDTO> categoryDTO = Optional.ofNullable(categoryService.getCategoryById(id));
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ResponseCategoryDTO>> getAllCategory() {
        List<ResponseCategoryDTO> categoryDTOS = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDTOS);
    }

    @DeleteMapping("/deletecategory/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
