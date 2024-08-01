package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LocalDate date = LocalDate.of(2024, 8, 1);
        System.out.println(date);

        LocalTime time = LocalTime.of(15, 2);
        System.out.println(time);

        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 1, 15, 2);
        System.out.println(dateTime);


    }
}