package com.abc.shop.controller;

import com.abc.shop.model.User;
import com.abc.shop.repository.UserRepository;
import com.abc.shop.service.UserService;
import com.abc.shop.utill.CommonUtill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    private UserRepository underTest;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @LocalServerPort
    private int port;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Test
    void checkTheControllerExistence() {
        assertThat(userController).isNotNull();
    }

    @Test
    void testTheGetAllUsersMethod() throws Exception {
        List<User> userList = null;
        when(userService.getAllUser()).thenReturn(new ResponseEntity<>(userList, HttpStatus.OK));


        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }



    @Test
    void getAllUsers() {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/",
                String.class)).contains("efwefwe");
    }

    @Test
    void createUser() {
        //given
        User user = new User(
                "nipun",
                10,
                "nipuntttdggggsd@gmail.com",
                "ddddd"
        );

        //when
        ResponseEntity<User> userData = userController.createUser(user);

        //then
        int expected = 200;
        assertThat(expected).isEqualTo(userData.getStatusCodeValue());
    }

    @Test
    void createAdmin() {
    }

    @Test
    void getUserDetail() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void removeUser() {
    }
}