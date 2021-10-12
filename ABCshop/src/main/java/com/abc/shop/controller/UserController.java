package com.abc.shop.controller;

import com.abc.shop.model.User;
import com.abc.shop.service.UserService;
import com.abc.shop.utill.CommonUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    private Long userId = CommonUtill.userId;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setRoleId(CommonUtill.customer);
        return userService.createUser(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        user.setRoleId(CommonUtill.admin);
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserDetail() {
        return userService.getUser(userId);
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User userDetails) {
        return userService.updateUser(userDetails, userId);
    }

//    @PostMapping(
//            path = "/user/updateProfile",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity updateUserProfile(@RequestParam("file") MultipartFile file){
//        return userService.updateUserProfile(file, userId);
//    }

    @DeleteMapping("/user/remove")
    public ResponseEntity removeUser() {
        return userService.deleteUser(userId);
    }
}
