package com.abc.shop.controller;

import com.abc.shop.model.User;
import com.abc.shop.service.UserService;
import com.abc.shop.utill.CommonUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        user.setRoleId(CommonUtill.customer);
        return userService.createUser(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<User> createAdmin(@RequestBody User user){
        user.setRoleId(CommonUtill.admin);
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserDetail(){
        return userService.getUser();
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User userDetails){
        return userService.updateUser(userDetails);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity removeUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }
}
