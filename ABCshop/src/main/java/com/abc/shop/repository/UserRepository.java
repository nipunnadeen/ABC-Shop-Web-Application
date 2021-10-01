package com.abc.shop.repository;

import com.abc.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);

    User findByUserName(String username);
}
