package com.example.demo.animal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PigeonTest {
    @Test
    public void testCall() {
        Pigeon pigeon = new Pigeon();

        String result = pigeon.call();

        Assertions.assertThat(result)
                .isEqualTo("Coo");
    }

    @Test
    public void testFly() {
        Pigeon pigeon = new Pigeon();

        String result = pigeon.fly();

        Assertions.assertThat(result)
                .isEqualTo("I can fly");
    }
}
