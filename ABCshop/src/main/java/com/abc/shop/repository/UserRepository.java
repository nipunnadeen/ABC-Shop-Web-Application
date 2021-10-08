package com.abc.shop.repository;

import com.abc.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String username);
    User findUserById(Long id);
    User findByEmail(String email);
}
