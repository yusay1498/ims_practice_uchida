package com.example.demo_attendance.domain.repository;

import com.example.demo_attendance.domain.entity.AttendanceSummary;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface AttendanceSummaryRepository {
    Optional<AttendanceSummary> findByEmployeeIdAndYearMonth(String employeeId, YearMonth yearMonth);
    List<AttendanceSummary> findMonthlyAttendanceSummaryByEmployeeId(String employeeId);
}
