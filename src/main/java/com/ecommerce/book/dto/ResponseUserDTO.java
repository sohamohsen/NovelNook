package com.ecommerce.book.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserDTO {
    private int id;
    @NotBlank(message = "Name cannot be blank") // Ensures name is not null or empty
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") // Limits the length of the name
    private String name;

    @NotBlank(message = "Email cannot be blank") // Ensures email is not null or empty
    @Email(message = "Invalid email format") // Validates email format
    private String email;

    @NotBlank(message = "Password cannot be blank") // Ensures password is not null or empty
    @Size(min = 8, message = "Password must be at least 8 characters long") // Enforces a minimum password length
    private String password;

    private LocalDateTime creationAt;

    @NotNull(message = "Role ID cannot be null") // Ensures roleId is provided
    private Integer roleId;
}
