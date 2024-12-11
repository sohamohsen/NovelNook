package com.ecommerce.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "books") // Explicitly map the entity to the "books" table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Column(nullable = false) // Enforce non-null title in the database
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author name must not exceed 50 characters")
    @Column(nullable = false) // Enforce non-null author in the database
    private String author;

    @Size(max = 1000, message = "Description must not exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Price cannot be negative")
    @Column(nullable = false) // Enforce non-null price in the database
    private float price;

    @Min(value = 0, message = "Stock cannot be negative")
    @Column(nullable = false) // Enforce non-null stock in the database
    private int stock;

    @Size(max = 100, message = "Cover image URL must not exceed 255 characters")
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "publish_year")
    @Min(value = 1500, message = "Publish year must be later than 1500")
    private int publishYear;

    @Size(max = 100, message = "Publisher name must not exceed 50 characters")
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    @JsonIgnore // Prevent serialization of the category in API responses
    private Category category;

}
