package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateRoleDTO;
import com.ecommerce.book.dto.ResponseRoleDTO;
import com.ecommerce.book.dto.UpdateRoleDTO;
import com.ecommerce.book.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/map")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/addrole")
    public ResponseEntity<ResponseRoleDTO> addRole(@RequestBody CreateRoleDTO role) {
        if(role == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @PutMapping("/updater/{id}")
    public ResponseEntity<ResponseRoleDTO> updateRole(
            @PathVariable int id,
            @RequestBody @Valid UpdateRoleDTO role
    ) {
        if(id<0){
            return ResponseEntity.badRequest().build();
        }else if(role == null) {
            return ResponseEntity.noContent().build();
        }return ResponseEntity.ok(roleService.updateRole(id, role));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<ResponseRoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Optional<ResponseRoleDTO>> getRole(@PathVariable int id) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        Optional<ResponseRoleDTO> roleDTO = Optional.ofNullable(roleService.getRoleById(id));
        if(roleDTO.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleDTO);
    }

    @DeleteMapping("deleterole/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
