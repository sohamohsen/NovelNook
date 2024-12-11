package com.ecommerce.book.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateBookDTO {
    @Size(max = 100, message = "Title cannot exceed 100 characters") // Limits the title length
    private String title;

    @Size(max = 50, message = "Author name cannot exceed 50 characters") // Limits the author name length
    private String author;

    @Size(max = 500, message = "Description cannot exceed 500 characters") // Limits the description length
    private String description;

    @Min(value = 1, message = "Price must be greater than 0") // Ensures price is positive
    private float price;

    @Min(value = 0, message = "Stock must not be negative") // Ensures stock is non-negative
    private int stock;

    private String coverImageUrl;

    @Min(value = 1000, message = "Publish year must be a valid year") // Ensures publish year is a valid number
    private int publishYear;

    @Size(max = 100, message = "Publisher name cannot exceed 100 characters") // Limits the publisher name length
    private String publisher;

    @Min(value = 1, message = "Category ID must be greater than 0") // Ensures category ID is positive
    private int categoryId;


}
