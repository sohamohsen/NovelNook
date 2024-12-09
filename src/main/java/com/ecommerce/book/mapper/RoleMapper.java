package com.ecommerce.book.mapper;

import com.ecommerce.book.dto.CreateRoleDTO;
import com.ecommerce.book.dto.ResponseRoleDTO;
import com.ecommerce.book.dto.UpdateRoleDTO;
import com.ecommerce.book.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper instance = Mappers.getMapper(RoleMapper.class);

    Role toEntity(CreateRoleDTO createRoleDTO);
    ResponseRoleDTO toDTO(Role role);
    Role toUpdateEntity(UpdateRoleDTO updateRoleDTO, @MappingTarget Role role);
    List<ResponseRoleDTO> toDTO(List<Role> roles);
}
