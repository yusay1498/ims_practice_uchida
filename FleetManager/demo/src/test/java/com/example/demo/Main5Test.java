package com.example.demo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Main5Test {
    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            0   | true
            1   | true
            10  | true
            11  | true
            00  | true
            1010| true
            1234| false
            """)
    void testIsBinaryNumber(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isBinaryNumber(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            0   | true
            1   | true
            10  | true
            11  | true
            00  | true
            AbC1| true
            GG  | false
            """)
    void testIsHexadecimal(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isHexadecimal(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            164-0002   | true
            353-0003   | true
            123-4567   | true
            aaa-aaaa   | false
            1234-567   | false
            123-45678  | false
            """)
    void testIsPostCode(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isPostCode(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            ij92400038   | true
            IJ00000000   | true
            Ij12345678   | true
            IJabcdefgh   | false
            ij1234567    | false
            1234567890   | false
            """)
    void testIsEmployeeNumber(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isEmployeeNumber(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            192.168.0.0  | true
            abcd         | false
            1            | false
            1234567890   | false
            """)
    void testIsIPAddress(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isIPAddress(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            Yusei Uchida   | true
            yusei uchida   | false
            1234567890     | false
            """)
    void testIsName(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isName(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            1999-09-15   | true
            0001-02-30   | false
            0000-02-28   | false
            2000-10-32   | false
            2000-11-31   | false
            2000-00-30   | false
            2000-10-00   | false
            """)
    void testIsBirthday(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isBirthday(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", textBlock = """
            1,000    | true
            20,000   | true
            100      | true
            1000     | false
            01       | false
            2,000,000| true
            """)
    void testIsMoney(
            String s, boolean expectedValue
    ) throws Throwable{
        boolean result = Main5.isMoney(s);

        Assertions.assertThat(result).isEqualTo(expectedValue);
    }
}
