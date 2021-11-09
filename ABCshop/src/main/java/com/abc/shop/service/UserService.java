package com.abc.shop.service;

import com.abc.shop.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUser();

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> getUser(Long userId);

    ResponseEntity<User> updateUser(User user, Long userId);

//    ResponseEntity updateUserProfile(MultipartFile file, Long userId);

    ResponseEntity deleteUser(Long userId);

    void verifyRefreshToken(HttpServletRequest request, HttpServletResponse response, Long userId)
            throws IOException;
}
