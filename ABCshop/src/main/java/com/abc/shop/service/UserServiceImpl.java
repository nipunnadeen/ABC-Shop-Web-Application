package com.abc.shop.service;

import com.abc.shop.FileStore.FileStore;
//import com.abc.shop.bucket.BucketName;
import com.abc.shop.model.User;
import com.abc.shop.repository.UserRepository;
import com.abc.shop.utill.CommonUtill;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.IMAGE_GIF;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_PNG;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileStore fileStore;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("customer"));
        CommonUtill.userId = user.getId();
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
        } catch (Exception e) {
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
                if (userRepository.findByEmail(user.getEmail()) == null) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            if ((!user.getName().isEmpty() && user.getAge() > 0 && !user.getEmail().isEmpty()
                    && !user.getPassword().isEmpty()) && (user.getName() != null &&
                    user.getEmail() != null && user.getEmail() != null) && userId != null) {
                userData = userRepository.findUserById(userId);
                if (userData != null) {
                    User users = userRepository.findByEmail(user.getEmail());

                    userData.setName(user.getName());
                    userData.setAge(user.getAge());
                    userData.setEmail(user.getEmail());
                    userData.setPassword(passwordEncoder.encode(user.getPassword()));
                    userData.setUpdatedAt(new Date());

                    if (users == null) {
                        User userDetail = userRepository.save(userData);
                        response = new ResponseEntity<>(userDetail, HttpStatus.OK);
                    } else {
                        if (users.getId() == userId) {
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
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

//    @Override
//    public ResponseEntity updateUserProfile(MultipartFile file, Long userId) {
//
//        ResponseEntity response;
//        User userData;
//        if (file.isEmpty()) {
//            throw new IllegalStateException("File not found");
//        }
//        if (!Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF).contains(file.getContentType())) {
//            throw new IllegalStateException("File must be a png, jpeg or a gif");
//        }
//
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("Content-Type", file.getContentType());
//        metadata.put("Content-Length", String.valueOf(file.getSize()));
//
//        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),
//                userId);
//        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
//        try {
//            if (userId != null) {
//                userData = userRepository.findUserById(userId);
//                if (userData != null) {
//                    fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
////                    userData.setUserProfileImageLink(fileNamee);
//                    userData.setUpdatedAt(new Date());
//                    User userDetail = userRepository.save(userData);
//                    response = new ResponseEntity<>(userDetail, HttpStatus.OK);
//                } else {
//                    response = new ResponseEntity<>(("User not exist with id :" + userId),
//                            HttpStatus.NOT_FOUND);
//                }
//            } else {
//                response = new ResponseEntity<>(("User Id is not acceptable"),
//                        HttpStatus.NOT_ACCEPTABLE);
//            }
//        } catch (IOException e) {
//            response = new ResponseEntity<>("Something went wrong",
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return response;
//    }

    @Override
    public ResponseEntity deleteUser(Long userId) {
        ResponseEntity response;
        User userData;
        try {
            if (userId != null && userId > 0) {
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

        } catch (Exception e) {
            response = new ResponseEntity<>("Something went wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
