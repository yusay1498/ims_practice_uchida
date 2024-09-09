package com.example.demo_attendance.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AttendanceTest {
    @Test
    public void testUpdateBeginWork() {
        Attendance beforeAttendance = new Attendance(
                "abc",
                "emp000",
                LocalDateTime.of(2024, 8, 25, 9, 0, 0),
                null
        );

        Attendance afterAttendance = beforeAttendance.updateBeginWork(LocalDateTime.of(2024, 9, 15, 10, 0, 0));

        Assertions.assertThat(afterAttendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 9, 15, 10, 0, 0));
    }

    @Test
    public void testUpdateFinishWork() {
        Attendance beforeAttendance = new Attendance(
                "123",
                "emp010",
                LocalDateTime.of(2024, 9, 15, 10, 0, 0),
                null
        );

        Attendance afterAttendance = beforeAttendance.updateFinishWork(LocalDateTime.of(2024, 9, 15, 17, 0, 0));

        Assertions.assertThat(afterAttendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 9, 15, 17, 0, 0));
    }

    @Test
    public void testUpdateBothWork() {
        Attendance beforeAttendance = new Attendance(
                "ABC",
                "emp020",
                LocalDateTime.of(2020,1,1,0,0,0),
                null
        );

        Attendance afterAttendance = beforeAttendance.updateBothWork(
                LocalDateTime.of(2024, 8, 29, 9, 45, 0),
                LocalDateTime.of(2024, 8, 29, 18, 10, 0)
        );

        Assertions.assertThat(afterAttendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 8, 29, 9, 45, 0));
        Assertions.assertThat(afterAttendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 8, 29, 18, 10, 0));
    }
}
