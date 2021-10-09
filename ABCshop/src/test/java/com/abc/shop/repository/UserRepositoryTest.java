package com.abc.shop.repository;

import com.abc.shop.model.User;
import com.abc.shop.utill.CommonUtill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findUserByName() {
        //given
        User user = new User(
                "nipun",
                10,
                "nipunttt@gmail.com",
                "1234"
        );
        user.setRoleId(CommonUtill.customer);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        underTest.save(user);
        //when
        User userData = underTest.findByEmail("nipunttt@gmail.com");

        //then
        boolean expected = true;
        if(userData != null){
            assertThat(expected).isTrue();
        } else {
            assertThat(expected).isFalse();
        }


    }

//    @Test
//    void findUserById() {
//    }
}