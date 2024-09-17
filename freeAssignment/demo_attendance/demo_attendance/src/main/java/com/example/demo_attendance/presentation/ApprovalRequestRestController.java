package com.example.demo_attendance.presentation;

import com.example.demo_attendance.application.ApprovalRequestService;
import com.example.demo_attendance.domain.entity.ApprovalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/approvalRequests")
public class ApprovalRequestRestController {
    private final ApprovalRequestService approvalRequestService;

    public ApprovalRequestRestController(ApprovalRequestService approvalRequestService) {
        this.approvalRequestService = approvalRequestService;
    }

    @GetMapping
    public List<ApprovalRequest> getApprovalRequests() {
        return approvalRequestService.viewAllApprovalRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApprovalRequest> getApprovalRequestById(
            @PathVariable String id
    ) {
        return approvalRequestService.viewApprovalRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // "/"でエンドポイントのコンフリクトを回避
    @GetMapping("/")
    public ResponseEntity<ApprovalRequest> getApprovalRequestByEmployeeIdAndYearMonth(
            @RequestParam String employeeId,
            @RequestParam YearMonth yearMonth
    ) {
        return approvalRequestService.viewApprovalRequestByEmployeeIdAndYearMonth(employeeId, yearMonth)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> postApprovalRequest(
            @RequestBody ApprovalRequest approvalRequest
            // 社員idと指定年月だけわかれば自動で保存
    ) {
        ApprovalRequest newApprovalRequest = approvalRequestService.recordRequest(new ApprovalRequest(
                null,
                approvalRequest.employeeId(),
                approvalRequest.yearMonth(),
                0,
                null,
                null,
                null,
                null
                ));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newApprovalRequest.id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putApprovalRequest(
            @PathVariable String id,
            @RequestBody ApprovalRequest approvalRequest
    ) {
        if (!Objects.equals(id, approvalRequest.id())) {
            return ResponseEntity.badRequest().body("Request ID and body ID do not match.");
        }

        // 承認はこの２つの値を持っていないと意味ないので弾く
        if (approvalRequest.status() != null && approvalRequest.approvedBy() != null) {
            approvalRequestService.recordApproval(id, approvalRequest.status(), approvalRequest.approvedBy());
            return ResponseEntity.ok("Approval successfully.");
        } else if (approvalRequest.yearMonth() != null) {
            // 再申請には年月か就業時間が拒否される原因になると思うので
            // 年月はnullでないように
            // 就業時間は再度自動算出
            approvalRequestService.updateRejectedRequest(id, approvalRequest.yearMonth());
            return ResponseEntity.ok("Request successfully.");
        } else {
            return ResponseEntity.badRequest().body("Bad request.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalRequest(
            @PathVariable String id
    ) {
        approvalRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
