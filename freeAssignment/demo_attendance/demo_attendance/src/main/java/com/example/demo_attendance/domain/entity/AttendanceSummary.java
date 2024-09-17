package com.example.demo_attendance.domain.entity;

import java.time.YearMonth;

public record AttendanceSummary(
        String employeeId,
        YearMonth yearMonth,
        double totalHours
) {
}
