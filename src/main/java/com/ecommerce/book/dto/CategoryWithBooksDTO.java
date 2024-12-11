package com.ecommerce.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryWithBooksDTO {
    private int id;

    @NotBlank(message = "Category name cannot be blank") // Ensures the name is not null or empty
    @Size(max = 100, message = "Category name cannot exceed 100 characters") // Limits the name length
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters") // Limits the description length
    private String description;

    @NotEmpty(message = "Books list cannot be empty") // Ensures the books list is not empty
    private List<ResponseBookDTO> books;
}
