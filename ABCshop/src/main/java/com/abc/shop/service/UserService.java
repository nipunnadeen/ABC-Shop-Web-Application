package com.abc.shop.service;

import com.abc.shop.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity getAllUser();

    ResponseEntity createUser(User user);

    ResponseEntity getUser(Long userId);

    ResponseEntity updateUser(User user, Long userId);

    ResponseEntity deleteUser(Long userId);
}
