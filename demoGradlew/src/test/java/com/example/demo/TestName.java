package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestName {
    @Test
    void test_name () {
        MyName myName = new MyName();

        String result = myName.name();

        System.out.println(result);

        Assertions.assertThat(result)
                .isEqualTo("内田悠聖");
    }
}
