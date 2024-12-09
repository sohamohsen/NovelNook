package com.ecommerce.book.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateUserDTO {
    private String name;
    private String email;
    private String password;
    private Integer roleId;

}
