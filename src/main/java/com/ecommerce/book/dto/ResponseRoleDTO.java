package com.ecommerce.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResponseRoleDTO {
    private int id;

    @NotBlank(message = "Role name cannot be blank") // Ensures role is not null or empty
    @Size(max = 50, message = "Role name cannot exceed 50 characters") // Limits the length of the role name
    private String role;
}
