package com.example.demo_attendance.application;

import com.example.demo_attendance.domain.entity.ApprovalRequest;
import com.example.demo_attendance.domain.entity.ApprovalStatus;
import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.domain.repository.ApprovalRequestRepository;
import com.example.demo_attendance.domain.repository.AttendanceRepository;
import com.example.demo_attendance.domain.repository.AttendanceSummaryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ApprovalRequestServiceTest {
    @Test
    void testViewAllApprovalRequests_Find() {
        List<ApprovalRequest> testApprovalRequests = List.of(
                new ApprovalRequest(
                        "UUID1",
                        "emp100",
                        YearMonth.of(2024, 8),
                        12000,
                        ApprovalStatus.PENDING,
                        LocalDateTime.of(2024, 8, 30, 9, 45, 0),
                        LocalDateTime.of(2024, 8, 30, 18, 10, 0),
                        "emp000"
                ),
                new ApprovalRequest(
                        "UUID2",
                        "emp101",
                        YearMonth.of(2024, 8),
                        9000,
                        ApprovalStatus.APPROVED,
                        LocalDateTime.of(2024, 8, 29, 9, 45, 0),
                        LocalDateTime.of(2024, 8, 29, 18, 10, 0),
                        "emp000"
                ),
                new ApprovalRequest(
                        "UUID3",
                        "emp102",
                        YearMonth.of(2024, 8),
                        200,
                        ApprovalStatus.REJECTED,
                        LocalDateTime.of(2024, 8, 28, 9, 45, 0),
                        LocalDateTime.of(2024, 8, 28, 18, 10, 0),
                        "emp000"
                )
        );
        ApprovalRequestRepository mockedApprovalRequestRepo = Mockito.mock(ApprovalRequestRepository.class);
        AttendanceSummaryRepository mockedAttendanceSummaryRepo = Mockito.mock(AttendanceSummaryRepository.class);
        Mockito.doReturn(testApprovalRequests).when(mockedApprovalRequestRepo).findAll();

        Clock mockedClock = Mockito.mock(Clock.class);

        ApprovalRequestService approvalRequestService = new ApprovalRequestService(mockedApprovalRequestRepo, mockedAttendanceSummaryRepo, mockedClock);
        List<ApprovalRequest> actual = approvalRequestService.viewAllApprovalRequests();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(testApprovalRequests.size());
        Assertions.assertThat(actual.getFirst()).isEqualTo(testApprovalRequests.getFirst());
        Assertions.assertThat(actual.get(1)).isEqualTo(testApprovalRequests.get(1));
        Assertions.assertThat(actual.get(2)).isEqualTo(testApprovalRequests.get(2));

        Mockito.verify(mockedApprovalRequestRepo, Mockito.times(1)).findAll();
    }

    @Test
    void testViewAllAttendances_Empty() {
        ApprovalRequestRepository mockedApprovalRequestRepo = Mockito.mock(ApprovalRequestRepository.class);
        AttendanceSummaryRepository mockedAttendanceSummaryRepo = Mockito.mock(AttendanceSummaryRepository.class);
        Mockito.doReturn(Collections.emptyList()).when(mockedApprovalRequestRepo).findAll();

        Clock mockedClock = Mockito.mock(Clock.class);

        ApprovalRequestService approvalRequestService = new ApprovalRequestService(mockedApprovalRequestRepo, mockedAttendanceSummaryRepo, mockedClock);
        List<ApprovalRequest> actual = approvalRequestService.viewAllApprovalRequests();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEmpty();

        Mockito.verify(mockedApprovalRequestRepo, Mockito.times(1)).findAll();
    }
}
