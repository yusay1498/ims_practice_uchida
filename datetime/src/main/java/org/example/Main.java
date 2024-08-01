package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LocalDate date = LocalDate.of(2024, 8, 1);
        System.out.println(date);

        LocalTime time = LocalTime.of(15, 2);
        System.out.println(time);

        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 1, 15, 2);
        System.out.println(dateTime);

        Date legcyDate = new Date(2024, 8, 1, 15, 2);
        System.out.println(legcyDate);
        legcyDate = new Date(2024, Calendar.AUGUST, 1, 15, 2);
        System.out.println(legcyDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(legcyDate);
        calendar.add(Calendar.HOUR_OF_DAY, 12);
        System.out.println(calendar.getTime());
    }
}