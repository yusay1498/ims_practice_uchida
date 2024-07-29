package com.example.demo.animal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DogTest {
    @Test
    public void testDog() {
        Dog dog = new Dog();

        String result = dog.call();

        Assertions.assertThat(result).
                isEqualTo("Bow");
    }
}
