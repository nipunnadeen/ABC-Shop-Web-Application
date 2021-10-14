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
        int expected = 8;

        assertThat(result).isEqualTo(expected);
    }
}
