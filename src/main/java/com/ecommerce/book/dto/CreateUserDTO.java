package com.ecommerce.book.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
    private LocalDateTime creationAt;
    private Integer roleId;
}
