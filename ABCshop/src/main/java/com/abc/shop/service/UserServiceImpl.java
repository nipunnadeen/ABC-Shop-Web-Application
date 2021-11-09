package com.abc.shop.service;

//import com.abc.shop.FileStore.FileStore;
import com.abc.shop.bucket.BucketName;
import com.abc.shop.exception.InvalidInputException;
import com.abc.shop.exception.NoSuchElementFoundException;
import com.abc.shop.model.User;
import com.abc.shop.repository.UserRepository;
import com.abc.shop.utill.CommonUtill;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.IMAGE_GIF;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_PNG;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private FileStore fileStore;

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

    @Override
    public void verifyRefreshToken(HttpServletRequest request, HttpServletResponse response,
                                   Long userId) throws IOException {
        if (userId == null || userId < 0) {
            throw new InvalidInputException("User Id is not acceptable");
        }

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                User user = userRepository.findUserById(decodedJWT.getClaim("id").asLong());
                if (user != null) {
                    throw new NoSuchElementFoundException("User is not found");
                }
                String username = decodedJWT.getSubject();
                CommonUtill.userId = decodedJWT.getClaim("id").asLong();

                String access_token = JWT.create()
                        .withSubject(username)
                        .withClaim("id", CommonUtill.userId)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing!");
        }
    }
}
