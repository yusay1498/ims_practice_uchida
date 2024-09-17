package com.example.demo_attendance.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.YearMonth;

@SpringBootTest
public class ApprovalRequestTest {
    @Test
    public void testUpdateRequest() {
        ApprovalRequest beforeApprovalRequest = new ApprovalRequest(
                "abc",
                "emp001",
                YearMonth.of(2024, 8),
                2000,
                ApprovalStatus.REJECTED,
                LocalDateTime.of(2024, 8, 31, 10, 0, 0),
                LocalDateTime.of(2024, 8, 31, 18, 0, 0),
                "emp000"
        );

        ApprovalRequest afterApprovalRequest = beforeApprovalRequest.updateRequest(9600, ApprovalStatus.PENDING, LocalDateTime.of(2024, 9, 1, 10, 0, 0));

        Assertions.assertThat(afterApprovalRequest.totalHours()).isEqualTo(9600);
        Assertions.assertThat(afterApprovalRequest.status()).isEqualTo(ApprovalStatus.PENDING);
        Assertions.assertThat(afterApprovalRequest.requestDate()).isEqualTo(LocalDateTime.of(2024, 9, 1, 10, 0, 0));
    }
}
