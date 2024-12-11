package com.ecommerce.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categories") // Explicitly map to the "categories" table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

//    @NotBlank(message = "Category name cannot be blank") // Validates non-blank input
    @Column(nullable = false, unique = true) // Enforces non-null and unique names in the DB
    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books;

}
