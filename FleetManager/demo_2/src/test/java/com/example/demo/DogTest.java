package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DogTest {
    @Test
    void testCall() {
        Dog dog = new Dog();

        String result = dog.call();

        System.out.println(result);

        Assertions.assertThat(result)
                .isEqualTo("Baw");
    }
}
