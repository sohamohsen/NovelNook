package com.ecommerce.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UpdateCategoryDTO {
    @Size(max = 100, message = "Category name cannot exceed 100 characters") // Limits the length of the category name
    private String name;

    @Size(max = 500, message = "Category description cannot exceed 500 characters") // Limits the description length
    private String description;
}
