package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(5);
        numbers.add(6);
        numbers.add(3);
        numbers.add(2);
        numbers.add(5);
        numbers.add(23);
        numbers.add(2);

        Collections.sort(numbers);

        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
