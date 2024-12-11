package com.ecommerce.book.service;

import com.ecommerce.book.dto.CreateRoleDTO;
import com.ecommerce.book.dto.ResponseRoleDTO;
import com.ecommerce.book.dto.UpdateRoleDTO;
import com.ecommerce.book.entity.Role;
import com.ecommerce.book.mapper.RoleMapper;
import com.ecommerce.book.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public ResponseRoleDTO addRole(CreateRoleDTO role) {
        Role savedrole = RoleMapper.instance.toEntity(role);
        return RoleMapper.instance.toDTO(roleRepo.save(savedrole));
    }

    public ResponseRoleDTO updateRole(int id, UpdateRoleDTO role) {
        if (id <= 0) {
            throw new IllegalArgumentException("Role id cannot be null or empty");
        }
            // Check if the category exists
            Role existingRole = roleRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Role with ID " + id + " not found"));
            RoleMapper.instance.toUpdateEntity(role, existingRole);
            return RoleMapper.instance.toDTO(roleRepo.save(existingRole));
    }

    public List<ResponseRoleDTO> getRoles(){
        return RoleMapper.instance.toDTO(roleRepo.findAll());
    }

    public ResponseRoleDTO getRoleById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Role id cannot be null or empty");
        }
        Optional <Role> role = roleRepo.findById(id);
        return role.map(RoleMapper.instance::toDTO).orElse(null);
    }

    public void deleteRole(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Role id cannot be null or empty");
        }
        roleRepo.deleteById(id);
    }
}