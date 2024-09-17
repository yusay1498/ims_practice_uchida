package com.example.demo_attendance.domain.repository;

import com.example.demo_attendance.domain.entity.ApprovalRequest;
import com.example.demo_attendance.domain.entity.AttendanceSummary;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ApprovalRequestRepository {
    List<ApprovalRequest> findAll();
    Optional<ApprovalRequest> findById(String id);
    Optional<ApprovalRequest> findByEmployeeIdAndYearMonth(String employeeId, YearMonth yearMonth);
    ApprovalRequest save(ApprovalRequest approvalRequest);
    void deleteById(String id);
}
