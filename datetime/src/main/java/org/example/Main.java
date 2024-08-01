package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("古い方");

        Date legcyDate = new Date(124, 8, 1, 15, 2);
        System.out.println(legcyDate);
        legcyDate = new Date(2024 - 1900, Calendar.AUGUST, 1, 15, 2);
        System.out.println(legcyDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(legcyDate);
        calendar.add(Calendar.HOUR_OF_DAY, 12);
        System.out.println(calendar.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss");
        System.out.println(dateFormat.format(calendar.getTime()));

        System.out.println("新しい方");

        LocalDate date = LocalDate.of(2024, 8, 1);
        System.out.println(date);

        LocalTime time = LocalTime.of(15, 2);
        System.out.println(time);

        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 1, 15, 2);
        System.out.println(dateTime);

        dateTime.plusHours(12);
        System.out.println(dateTime);

        LocalDateTime dateTimePlus12 = dateTime.plusHours(12);
        System.out.println(dateTimePlus12);

        Clock clock = Clock.fixed(Instant.parse("2024-08-01T12:00:00+09:00"), ZoneId.of("Asia/Tokyo"));
        LocalDateTime now = LocalDateTime.now(clock);
        System.out.println(now);

        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, ZoneOffset.of("+09:00"));
        System.out.println(offsetDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);

        Duration seconds = Duration.ofSeconds(5);
        Duration minutes = Duration.ofMinutes(5);
        Duration hours = Duration.ofHours(2);
        Duration days = Duration.ofDays(90);

        System.out.println(seconds);
        System.out.println(minutes);
        System.out.println(hours);
        System.out.println(days);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu年 MM月 dd日 HH:mm:ss");
        System.out.println(formatter.format(dateTime));
    }
}