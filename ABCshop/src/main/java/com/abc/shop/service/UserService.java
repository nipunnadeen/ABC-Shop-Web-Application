package com.abc.shop.service;

import com.abc.shop.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUser();

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> getUser(Long userId);

    ResponseEntity<User> updateUser(User user, Long userId);

    ResponseEntity deleteUser(Long userId);
}
