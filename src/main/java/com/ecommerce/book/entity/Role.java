package com.ecommerce.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles") // Explicitly map the entity to the "roles" table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Role cannot be blank")
    @Column(nullable = false, unique = true) // Enforce non-null and unique role names
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

}
