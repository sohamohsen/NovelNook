package com.ecommerce.book.dto;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class UpdateBookDTO {
    private String title;
    private String author;
    private String description;
    private float price;
    private int stock;
    private String cover_image_url;
    @Column(name = "publish_year")
    private int publishYear;
    private String publisher;
    private int categoryId;

}
