package com.ecommerce.book.controller;

import com.ecommerce.book.dto.CreateUserDTO;
import com.ecommerce.book.dto.ResponseUserDTO;
import com.ecommerce.book.dto.UpdateUserDTO;
import com.ecommerce.book.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<ResponseUserDTO> addUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        if(createUserDTO == null){
            return ResponseEntity.noContent().build();
        }
        ResponseUserDTO responseUserDTO = userService.addUser(createUserDTO);
        return ResponseEntity.ok(responseUserDTO);
    }

    @PutMapping("/changeuser/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(
            @PathVariable("id") int id,
            @RequestBody @Valid UpdateUserDTO updateUserDTO
    ) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        if(updateUserDTO == null){
            return ResponseEntity.noContent().build();
        }
        ResponseUserDTO responseUserDTO = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(responseUserDTO);
    }

    @GetMapping("users")
    public ResponseEntity<List<ResponseUserDTO>> getUsers() {
        List<ResponseUserDTO> users = userService.getAllUsers();
        if(users == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<ResponseUserDTO>> getBookById(@PathVariable int id) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        Optional<ResponseUserDTO> userDTO = Optional.ofNullable(userService.getUserById(id));
        if(userDTO.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<ResponseUserDTO> deleteUser(@PathVariable int id) {
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
