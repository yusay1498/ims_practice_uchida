package com.example.demo_attendance.domain.repository;

import com.example.demo_attendance.domain.entity.Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository {
    List<Attendance> findAll();
    Optional<Attendance> findById(String id);
    Optional<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
    Attendance save(Attendance attendance);
    void deleteById(String id);
}
