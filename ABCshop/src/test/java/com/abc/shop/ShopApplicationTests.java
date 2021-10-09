package com.abc.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ShopApplicationTests {

    @Test
    void contextLoads() {
        int k = 5;
        int l = 3;

        int result = k+l;
        int expected = 10;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void contextLoads1() {
        int k = 6;
        int l = 3;

        int result = k+l;
        int expected = 9;

        assertThat(result).isEqualTo(expected);
    }

}
