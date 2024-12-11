package com.ecommerce.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users") // Explicitly maps the entity to the "users" table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Column(nullable = false) // Enforce non-null name in the database
    private String name;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be blank")
    @Column(nullable = false, unique = true) // Enforce non-null and unique emails in the database
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @JsonIgnore // Ensure passwords are not exposed in API responses
    @Column(nullable = false) // Enforce non-null password in the database
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore // Prevent serialization of the role in API responses
    private Role role;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Automatically set creation timestamp
    }

}
