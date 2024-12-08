package com.ecommerce.book.mapper;

import com.ecommerce.book.dto.*;
import com.ecommerce.book.entity.Book;
import com.ecommerce.book.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper instance = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CreateCategoryDTO createCategoryDTO);

    ResponseCategoryDTO toDTO(Category category);

    Category toUpdateEntity(UpdateCategoryDTO updateCategoryDTO);

    // Map Category entity to CategoryWithBooksDTO
    @Mapping(target = "books", source = "books") // Map books to ResponseBookDTO list
    CategoryWithBooksDTO toCategoryWithBooksDTO(Category category);

    // Map Book entity to ResponseBookDTO
    List<ResponseCategoryDTO> toResponseCategoryDTOList(List<Category> categories);
}
