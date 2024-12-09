package com.ecommerce.book.mapper;

import com.ecommerce.book.dto.*;
import com.ecommerce.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper instance = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "category.id", source = "categoryId") // Updated to category_id
    Book toEntity(CreateBookDTO createBookDTO);

    @Mapping(target = "categoryId", source = "category.id") // Mapping from category.id to categoryId in DTO
    ResponseBookDTO toDTO(Book book);

    @Mapping(target = "categoryId", source = "category.id") // Mapping from category.id to categoryId in DTO
    UpdateBookDTO toUpdateDTO(Book book);

    @Mapping(target = "category.id", source = "categoryId") // Updated to category_id
    Book toUpdateEntity(UpdateBookDTO updateBookDTO);

    List<ResponseBookDTO> toDTOList(List<Book> books);
}

