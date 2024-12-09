package com.ecommerce.book.mapper;

import com.ecommerce.book.dto.*;
import com.ecommerce.book.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role.id", source = "roleId") // Updated to category_id
    User toEntity(CreateUserDTO createUserDTO);

    @Mapping(target = "roleId", source = "role.id") // Mapping from category.id to categoryId in DTO
    ResponseUserDTO toDTO(User user);

    @Mapping(target = "roleId", source = "role.id") // Mapping from category.id to categoryId in DTO
    UpdateUserDTO toUpdateDTO(User user);

    @Mapping(target = "role.id", source = "roleId") // Updated to category_id
    User toUpdateEntity(UpdateUserDTO updateUserDTO);

    List<ResponseUserDTO> toDTOList(List<User> users);

}
