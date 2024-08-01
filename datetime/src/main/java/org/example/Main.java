package org.example;

import java.time.*;
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

        dateTime.plusHours(12);
        System.out.println(dateTime);

        LocalDateTime dateTimePlus12 = dateTime.plusHours(12);
        System.out.println(dateTimePlus12);

        Clock clock = Clock.fixed(Instant.parse("2024-08-01T12:00:00+09:00"), ZoneId.of("Asia/Tokyo"));
        LocalDateTime now = LocalDateTime.now(clock);
        System.out.println(now);
    }
}