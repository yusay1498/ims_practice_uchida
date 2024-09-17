package com.example.demo_attendance.domain.entity;

import java.time.LocalDateTime;
import java.time.YearMonth;

public record ApprovalRequest(
        String id,
        String employeeId,
        YearMonth yearMonth,
        double totalHours,
        ApprovalStatus status,
        LocalDateTime requestDate,
        LocalDateTime approvalDate,
        String approvedBy
) {
    public ApprovalRequest updateRequest(double newTotalHours, ApprovalStatus newStatus, LocalDateTime newRequestDate) {
        return new ApprovalRequest(id(), employeeId(), yearMonth(), newTotalHours, newStatus, newRequestDate, approvalDate(), approvedBy());
    }
}
