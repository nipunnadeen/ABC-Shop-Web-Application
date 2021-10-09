package com.abc.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Test
    void getAllUser() {
    }

    @Test
    void createUser() {

        int k = 5;
        int l = 3;

        int result = k+l;
        int expected = 10;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}