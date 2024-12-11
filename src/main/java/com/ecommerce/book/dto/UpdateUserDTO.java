package com.ecommerce.book.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") // Limits the length of the name
    private String name;

    @Email(message = "Invalid email format") // Validates email format
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long") // Enforces a minimum password length
    private String password;

    private Integer roleId;
}
