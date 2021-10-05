package com.abc.shop.service;

import com.abc.shop.model.User;
import com.abc.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(userName);

//        boolean t = passwordEncoder.matches("",user.getPassword());

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities is empty check it
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        ResponseEntity<List<User>> response;
        List<User> usersData;
        try {
            usersData = userRepository.findAll();
            response = new ResponseEntity<>(usersData, HttpStatus.OK);
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        ResponseEntity<User> response;
        User userData;
        try {
            if ((!user.getName().isEmpty() && user.getAge() > 0 && !user.getEmail().isEmpty()
                    && !user.getPassword().isEmpty()) && (user.getName() != null &&
                    user.getEmail() != null && user.getPassword() != null)) {
                if (userRepository.findUserByName(user.getEmail()) == null) {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
//                        user.setCreatedBy(user.getName());
//                        user.setUpdatedBy(user.getName());
                    userData = userRepository.save(user);
                    response = new ResponseEntity<>(userData, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<User> getUser(Long userId) {
        ResponseEntity<User> response;
        try {
            if (userId != null && userId > 0) {
                User userData = userRepository.findUserById(userId);
                if (userData != null) {
                    response = new ResponseEntity<>(userData, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<User> updateUser(User user, Long userId) {
        ResponseEntity<User> response;
        User userData;
        try {
            if((!user.getName().isEmpty() && user.getAge() > 0 && !user.getEmail().isEmpty()
                    && !user.getPassword().isEmpty()) && (user.getName() != null &&
                    user.getEmail() != null && user.getEmail() != null)) {
                userData = userRepository.findUserById(userId);
                if (userData != null) {
                    User users = userRepository.findUserByName(user.getEmail());

                    userData.setName(user.getName());
                    userData.setAge(user.getAge());
                    userData.setEmail(user.getEmail());
                    userData.setPassword(passwordEncoder.encode(user.getPassword()));
//                    userData.setUpdatedAt(new Date());
//                    userData.setUpdatedBy(8);


                    if(users == null ) {
                        User userDetail = userRepository.save(userData);
                        response = new ResponseEntity<>(userDetail, HttpStatus.OK);
                    } else {
                        if(users.getId() == userId ) {
                            User userDetail = userRepository.save(userData);
                            response = new ResponseEntity<>(userDetail, HttpStatus.OK);
                        } else {
                            response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                        }
                    }
                } else {
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity deleteUser(Long userId) {
        ResponseEntity response;
        User userData;
        try{
            if(userId != null && userId > 0) {
                userData = userRepository.findUserById(userId);
                if (userData != null) {
                    userRepository.deleteById(userId);
                    response = new ResponseEntity<>(userRepository, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(("User not exist with id :" + userId),
                            HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(("User Id is not acceptable"),
                        HttpStatus.NOT_ACCEPTABLE);
            }

        } catch(Exception e) {
            response = new ResponseEntity<>("Something went wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
