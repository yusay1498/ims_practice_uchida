package com.example.demo;

import java.util.*;
import java.util.stream.Stream;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Hello World");

        String givenName = "Yusei";

        String familyName = "Uchida";

        System.out.println(givenName + familyName);

        int age = 1;

        if (age >= 20) {
            System.out.println("sake");
        } else {
            System.out.println("NO");
        }

//        List<Integer>

        for (int i = 0 ; i < 20; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }

        List<String> list = List.of("a", "b", "c", "d", "e");

        for (String s : list) {
            System.out.println(s);
        }

        List<String> emptyList = Collections.emptyList();

        for (String s : emptyList) {
            System.out.println(s);
        }

        Map<String, Integer> scores = Map.ofEntries(
                Map.entry("Alice", 80),
                Map.entry("Bob", 75),
                Map.entry("Carol", 70)
        );

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }

        scores.entrySet().stream()
                .filter(stringIntegerEntry -> {
           return stringIntegerEntry.getValue() >= 80;
        }).map(stringIntegerEntry -> {
            return stringIntegerEntry.getKey() + ":" + stringIntegerEntry.getValue();
                }).forEach(s -> {
            System.out.println(s);
        });

        int sum = scores.entrySet().stream()
                .mapToInt(stringIntegerEntry -> stringIntegerEntry.getValue())
                .sum();

        System.out.println("Sum" + sum);

        List<String> mut = new ArrayList<>();

        mut.add("A");
        mut.add("B");

        for (String s : mut) {
            System.out.println(s);
        }

        List<Integer> intList = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i *100)
                .toList();

        for (Integer i : intList) {
            System.out.println(i);
        }

    }
}