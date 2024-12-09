package com.ecommerce.book.repository;

import com.ecommerce.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
