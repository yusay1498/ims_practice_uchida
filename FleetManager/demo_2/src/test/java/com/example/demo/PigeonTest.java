package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PigeonTest {
    @Test
    void testCall() {
        Pigeon pigeon = new Pigeon();

        String result = pigeon.call();

        System.out.println(result);


        Assertions.assertThat(result)
                .isEqualTo("Coo");

    }

    @Test
    void testFly() {
        Pigeon pigeon  = new Pigeon();

        String result2 = pigeon.fly();

        System.out.println(result2);

        Assertions.assertThat(result2)
                .isEqualTo("I can fly");
    }
}
