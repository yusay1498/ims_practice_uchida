package com.example.demo_attendance.application;

import com.example.demo_attendance.domain.entity.ApprovalRequest;
import com.example.demo_attendance.domain.entity.ApprovalStatus;
import com.example.demo_attendance.domain.entity.AttendanceSummary;
import com.example.demo_attendance.domain.repository.ApprovalRequestRepository;
import com.example.demo_attendance.domain.repository.AttendanceSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApprovalRequestService {
    private final ApprovalRequestRepository approvalRequestRepository;
    private final AttendanceSummaryRepository attendanceSummaryRepository;
    private final Clock clock;

    public ApprovalRequestService(ApprovalRequestRepository approvalRequestRepository, AttendanceSummaryRepository attendanceSummaryRepository, Clock clock) {
        this.approvalRequestRepository = approvalRequestRepository;
        this.attendanceSummaryRepository = attendanceSummaryRepository;
        this.clock = clock;
    }

    public List<ApprovalRequest> viewAllApprovalRequests() {
        return approvalRequestRepository.findAll();
    }

    public Optional<ApprovalRequest> viewApprovalRequestById(String id) {
        return approvalRequestRepository.findById(id);
    }

    public Optional<ApprovalRequest> viewApprovalRequestByEmployeeIdAndYearMonth(String employeeId, YearMonth yearMonth) {
        return approvalRequestRepository.findByEmployeeIdAndYearMonth(employeeId, yearMonth);
    }

    public ApprovalRequest recordRequest(ApprovalRequest approvalRequest) {
        if (approvalRequest.id() != null) {
            throw new IllegalArgumentException("ID should be null for new records.");
        }

        // 社員idと年月だけわかれば勝手に算出
        Optional<AttendanceSummary> attendanceSummaryOptional = attendanceSummaryRepository
                .findByEmployeeIdAndYearMonth(approvalRequest.employeeId(), approvalRequest.yearMonth());

        return attendanceSummaryOptional
                .map(attendanceSummary -> approvalRequestRepository.save(approvalRequest.updateRequest(
                        attendanceSummary.totalHours(),
                        ApprovalStatus.PENDING,
                        getNowDateTime()
                )))
                .orElseThrow(() -> new RuntimeException("No attendance summary found for the given employeeId and yearMonth."));
    }

    // 承認処理
    // 承認する人とその状態だけわかれば十分なので余計なのはカット
    public void recordApproval(String id, ApprovalStatus status, String approvedBy) {
        if (approvedBy == null) {
            throw new IllegalArgumentException("ApprovedBy should not be null.");
        }

        Optional<ApprovalRequest> foundApprovalRequest = approvalRequestRepository.findById(id);

        foundApprovalRequest.ifPresentOrElse(approvalRequest -> {
            approvalRequestRepository.save(new ApprovalRequest(
                    approvalRequest.id(),
                    approvalRequest.employeeId(),
                    approvalRequest.yearMonth(),
                    approvalRequest.totalHours(),
                    status,
                    approvalRequest.requestDate(),
                    getNowDateTime(),
                    approvedBy
            ));
        }, () -> {
            throw new RuntimeException("No approval request found for ID " + id);
        });
    }

    // 拒否された場合の再申請
    // 他のデータは再申請には必要ないので年月と就業時間だけ
    public void updateRejectedRequest(String id, YearMonth yearMonth) {
        ApprovalRequest approvalRequest = approvalRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No recorded request found for ID " + id));

        // 指定された年月とデータの年月が一致するか確認
        // 年月はユニークキーなので事前にエラーにしておく
        if (!approvalRequest.yearMonth().equals(yearMonth)) {
            throw new IllegalArgumentException("The provided year and month do not match the recorded request.");
        }

        // 申請が承認済みの場合、変更を受け付けない
        if (approvalRequest.status() == ApprovalStatus.APPROVED) {
            throw new IllegalStateException("This request has already been approved. Changes are not accepted.");
        }

        // 拒否された勤怠記録を修正したらちゃんと自動算出するようにする
        // 拒否されて合計就業時間だけ修正するのは根本的解決になっていないのでここはあえて人為的に変更できないように
        AttendanceSummary attendanceSummary = attendanceSummaryRepository
                .findByEmployeeIdAndYearMonth(
                        approvalRequest.employeeId(),
                        yearMonth
                )
                .orElseThrow(() -> new RuntimeException("No attendance summary found for employeeId " + approvalRequest.employeeId() + " and yearMonth " + yearMonth));

        approvalRequestRepository.save(
                approvalRequest.updateRequest(
                        attendanceSummary.totalHours(),
                        ApprovalStatus.PENDING,
                        getNowDateTime()
                )
        );
    }

    public void deleteById(String id) {
        approvalRequestRepository.deleteById(id);
    }

    private LocalDateTime getNowDateTime() {
        return LocalDateTime.now(clock).withNano(0);
    }
}
