package com.ecommerce.book.dto;

import lombok.Data;

import java.util.List;
@Data
public class CategoryWithBooksDTO {
    private int id;
    private String name;
    private String description;
    private List<ResponseBookDTO> books;

}
