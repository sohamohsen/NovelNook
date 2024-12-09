package com.ecommerce.book.service;

import com.ecommerce.book.dto.CreateUserDTO;
import com.ecommerce.book.dto.ResponseUserDTO;
import com.ecommerce.book.dto.UpdateUserDTO;
import com.ecommerce.book.entity.User;
import com.ecommerce.book.mapper.UserMapper;
import com.ecommerce.book.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    UserService {
    @Autowired
    private UserRepo userRepo;

    public ResponseUserDTO addUser(CreateUserDTO user) {
        if (user.getName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("there is no data");
        }
        User savedUser = UserMapper.instance.toEntity(user);
        return UserMapper.instance.toDTO(userRepo.save(savedUser));
    }

    public ResponseUserDTO updateUser(int id, UpdateUserDTO user) {
        if (id <= 0) {
            throw new IllegalArgumentException("There is no data");
        }
        if (user == null) {
            throw new IllegalArgumentException("There is no data");
        }

        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("There is no user with this id");
        }

        UpdateUserDTO updatedUser = UserMapper.instance.toUpdateDTO(existingUser);

        if (updatedUser == null) {
            throw new IllegalArgumentException("There is no user with this id");
        } else {
            // Check and handle name update
            if (user.getName() == null || user.getName().isEmpty() || user.getName().equals(updatedUser.getName())) {
                // Log the message and continue
                System.out.println("User name is either empty or unchanged, skipping update.");
            } else {
                updatedUser.setName(user.getName());
            }

            // Check and handle password update
            if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().equals(updatedUser.getPassword())) {
                // Log the message and continue
                System.out.println("User password is either empty or unchanged, skipping update.");
            } else {
                updatedUser.setPassword(user.getPassword());
            }

            // Check and handle email update
            if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().equals(updatedUser.getEmail())) {
                // Log the message and continue
                System.out.println("User email is either empty or unchanged, skipping update.");
            } else {
                updatedUser.setEmail(user.getEmail());
            }

            // Check and handle role update
            if (user.getRoleId() == null || user.getRoleId().equals(updatedUser.getRoleId())) {
                // Log the message and continue
                System.out.println("User role is either empty or unchanged, skipping update.");
            } else {
                updatedUser.setRoleId(user.getRoleId());
            }
        }
        // Now ensure the existing user's ID is set in the entity
        User updateUser = UserMapper.instance.toUpdateEntity(updatedUser);
        updateUser.setId(id);  // Ensure the ID is set to the existing user's ID
        updateUser.setCreationAt(existingUser.getCreationAt());

        // Save the updated user, this will update the existing user instead of creating a new one
        return UserMapper.instance.toDTO(userRepo.save(updateUser));
    }

    public List<ResponseUserDTO> getAllUsers() {
        if (userRepo.findAll().isEmpty()) {
            throw new IllegalArgumentException("there is no users");
        }
        List<User> users = userRepo.findAll();
        return UserMapper.instance.toDTOList(users);
    }

    public ResponseUserDTO getUserById(int id) {
        if (id <=0){
            throw new IllegalArgumentException("there is no data");
        }
        Optional<User> user = userRepo.findById(id);
        return user.map(UserMapper.instance::toDTO).orElse(null);
    }

    public void deleteUser(int id) {
        if (id <=0){
            throw new IllegalArgumentException("there is no data");
        }
        userRepo.findById(id).orElse(null);
    }
}
