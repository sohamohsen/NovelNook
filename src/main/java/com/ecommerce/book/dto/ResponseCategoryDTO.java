package com.ecommerce.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResponseCategoryDTO {
    private int id;

//    @NotBlank(message = "Category name cannot be blank") // Ensures name is not null or empty
    @Size(max = 100, message = "Category name cannot exceed 100 characters") // Limits the length of the category name
    private String name;

    @Size(max = 500, message = "Category description cannot exceed 500 characters") // Limits the description length
    private String description;

}
