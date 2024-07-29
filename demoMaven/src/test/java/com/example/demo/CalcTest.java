package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalcTest {
    @Test
    void given_Calc_instance__when_Call_add_with_1_and_2__then_Return_3(){
        Calc calc = new Calc();

        int result = calc.add(1,2);

        Assertions.assertThat(result).isEqualTo(3);
    }

    @ParameterizedTest(name = "Given calc instance, When call add with {0} and {1}, Then return {2}")
    @CsvSource(delimiterString = "|", textBlock = """
            1 | 2 | 3
            10 | 10 | 20
            """)
    void given_Calc_instance__when_Call_add_with_1_and_2__then_Return(
            int x , int y, int expectedValue
    ){
        Calc calc = new Calc();

        int result = calc.add(x,y);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    void given_Calc_instance__when_Call_sub_with_1_and_2__then_Return_minus1(){
        Calc calc = new Calc();

        int result = calc.sub(1,2);

        Assertions.assertThat(result).isEqualTo(-1);
    }

    @ParameterizedTest(name = "Given calc instance, When call sub with {0} and {1}, Then return {2}")
    @CsvSource(delimiterString = "|", textBlock = """
            1 | 2 | -1
            10 | 10 | 0
            """)
    void given_Calc_instance__when_Call_sub_with_1_and_2__then_Return(
            int x , int y, int expectedValue
    ){
        Calc calc = new Calc();

        int result = calc.sub(x,y);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    void given_Calc_instance__when_Call_multi_with_1_and_2__then_Return_2(){
        Calc calc = new Calc();

        int result = calc.multi(1,2);

        Assertions.assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest(name = "Given calc instance, When call multi with {0} and {1}, Then return {2}")
    @CsvSource(delimiterString = "|", textBlock = """
            1 | 2 | 2
            10 | 10 | 100
            """)
    void given_Calc_instance__when_Call_multi_with_1_and_2__then_Return(
            int x , int y, int expectedValue
    ){
        Calc calc = new Calc();

        int result = calc.multi(x,y);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    void given_Calc_instance__when_Call_div_with_1_and_2__then_Return_0dot5(){
        Calc calc = new Calc();

        double result = calc.div(1,2);

        Assertions.assertThat(result).isEqualTo(0.5);
    }

    @Test
    void given_Calc_instance__when_Call_div_with_1_and_2__then_Return_POSITIVE_INFINITY(){
        Calc calc = new Calc();

        double result = calc.div(1,0);

        Assertions.assertThat(result).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @ParameterizedTest(name = "Given calc instance, When call div with {0} and {1}, Then return {2}")
    @CsvSource(delimiterString = "|", textBlock = """
            1  | 2  | 0.5
            10 | 10 | 1
            100| 5  | 20
            """)
    void given_Calc_instance__when_Call_div_with_1_and_2__then_Return(
            int x , int y, double expectedValue
    ){
        Calc calc = new Calc();

        double result = calc.div(x,y);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }
}
