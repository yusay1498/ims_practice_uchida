package com.example.demo_attendance.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Attendance(
        String id,
        String employeeId,
        LocalDateTime beginWork,
        LocalDateTime finishWork
) {
    public LocalDate getDate() {
        return beginWork.toLocalDate();
    }

    public Attendance updateBeginWork(LocalDateTime newBeginWork) {
        return new Attendance(id(), employeeId(), newBeginWork, finishWork());
    }

    public Attendance updateFinishWork(LocalDateTime newFinishWork) {
        return new Attendance(id(), employeeId(), beginWork(), newFinishWork);
    }

    public Attendance updateBothWork(LocalDateTime newBeginWork, LocalDateTime newFinishWork) {
        return new Attendance(id(), employeeId(), newBeginWork, newFinishWork);
    }
}
