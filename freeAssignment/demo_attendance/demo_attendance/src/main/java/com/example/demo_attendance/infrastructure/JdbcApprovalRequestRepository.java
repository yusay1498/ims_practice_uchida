package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.ApprovalRequest;
import com.example.demo_attendance.domain.entity.ApprovalStatus;
import com.example.demo_attendance.domain.entity.AttendanceSummary;
import com.example.demo_attendance.domain.repository.ApprovalRequestRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcApprovalRequestRepository implements ApprovalRequestRepository {
    private final JdbcClient jdbcClient;

    public JdbcApprovalRequestRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<ApprovalRequest> findAll() {
        return jdbcClient.sql("""
                            SELECT
                                id,
                                employee_id,
                                year_month,
                                total_hours,
                                status,
                                request_date,
                                approval_date,
                                approved_by
                            FROM 
                                approval_requests
                        """)
                .query(
                        (ResultSet resultSet, int rowNum) -> {
                            String id = resultSet.getString("id");
                            String employeeId = resultSet.getString("employee_id");
                            LocalDate yearMonthDate = resultSet.getDate("year_month").toLocalDate();
                            double totalHours = resultSet.getDouble("total_hours");
                            ApprovalStatus status = ApprovalStatus.valueOf(resultSet.getString("status"));
                            LocalDateTime requestDate = resultSet.getTimestamp("request_date").toLocalDateTime();
                            // ここはnullも許容するのでデータ変換にエラーが起きないように適切に処理
                            LocalDateTime approvalDate = resultSet.getTimestamp("approval_date") != null
                                    ? resultSet.getTimestamp("approval_date").toLocalDateTime()
                                    : null;
                            String approvedBy = resultSet.getString("approved_by");

                            YearMonth yearMonth = YearMonth.from(yearMonthDate);

                            return new ApprovalRequest(id, employeeId, yearMonth, totalHours, status, requestDate, approvalDate, approvedBy);
                        }
                )
                .list();
    }

    public Optional<ApprovalRequest> findById(String id) {
        return jdbcClient.sql("""
                    SELECT
                        id,
                        employee_id,
                        year_month,
                        total_hours,
                        status,
                        request_date,
                        approval_date,
                        approved_by
                    FROM
                        approval_requests
                    WHERE 
                        id = :id
                """)
                .param("id", id)
                .query(
                        (ResultSet resultSet, int rowNum) -> {
                            String setId = resultSet.getString("id");
                            String employeeId = resultSet.getString("employee_id");
                            LocalDate yearMonthDate = resultSet.getDate("year_month").toLocalDate();
                            double totalHours = resultSet.getDouble("total_hours");
                            ApprovalStatus status = ApprovalStatus.valueOf(resultSet.getString("status"));
                            LocalDateTime requestDate = resultSet.getTimestamp("request_date").toLocalDateTime();
                            LocalDateTime approvalDate = resultSet.getTimestamp("approval_date") != null
                                    ? resultSet.getTimestamp("approval_date").toLocalDateTime()
                                    : null;
                            String approvedBy = resultSet.getString("approved_by");

                            YearMonth yearMonth = YearMonth.from(yearMonthDate);

                            return new ApprovalRequest(setId, employeeId, yearMonth, totalHours, status, requestDate, approvalDate, approvedBy);
                        })
                .optional();
    }

    public Optional<ApprovalRequest> findByEmployeeIdAndYearMonth(String employeeId, YearMonth yearMonth) {
        LocalDate changeDate = yearMonth.atDay(1);
        return jdbcClient.sql("""
                    SELECT
                        id,
                        employee_id,
                        year_month,
                        total_hours,
                        status,
                        request_date,
                        approval_date,
                        approved_by
                    FROM
                        approval_requests
                    WHERE 
                        employee_id = :employeeId
                    AND
                        year_month = :yearMonth
                """)
                .param("employeeId", employeeId)
                .param("yearMonth", changeDate)
                .query(
                        (ResultSet resultSet, int rowNum) -> {
                            String id = resultSet.getString("id");
                            String setEmployeeId = resultSet.getString("employee_id");
                            LocalDate yearMonthDate = resultSet.getDate("year_month").toLocalDate();
                            double totalHours = resultSet.getDouble("total_hours");
                            ApprovalStatus status = ApprovalStatus.valueOf(resultSet.getString("status"));
                            LocalDateTime requestDate = resultSet.getTimestamp("request_date").toLocalDateTime();
                            LocalDateTime approvalDate = resultSet.getTimestamp("approval_date") != null
                                    ? resultSet.getTimestamp("approval_date").toLocalDateTime()
                                    : null;
                            String approvedBy = resultSet.getString("approved_by");

                            YearMonth setYearMonth = YearMonth.from(yearMonthDate);

                            return new ApprovalRequest(id, setEmployeeId, setYearMonth, totalHours, status, requestDate, approvalDate, approvedBy);
                        })
                .optional();
    }

    public ApprovalRequest save(ApprovalRequest approvalRequest) {
        // YearMonthで保存はできないのでDATEの形に合わせる

        LocalDate changeDate = null;
        if (approvalRequest.yearMonth() != null) {
             changeDate = approvalRequest.yearMonth().atDay(1);
        }

        // idが指定されていなければINSERT
        if (approvalRequest.id() == null) {
            String newId = UUID.randomUUID().toString();

            jdbcClient.sql("""
                    INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
                    VALUES (:id, :employeeId, :yearMonth, :totalHours, :status, :requestDate, :approvalDate, :approvedBy)
                """)
                    .param("id", newId)
                    .param("employeeId", approvalRequest.employeeId())
                    .param("yearMonth", changeDate)
                    .param("totalHours", approvalRequest.totalHours())
                    .param("status", approvalRequest.status().name())
                    .param("requestDate", approvalRequest.requestDate())
                    .param("approvalDate", approvalRequest.approvalDate())
                    .param("approvedBy", approvalRequest.approvedBy())
                    .update();

            return findById(newId).orElseThrow(
                    () -> new RuntimeException("INSERT operation was unsuccessful"));
        } else {
            // idが存在すればUPDATE
            // COALESCE文を使用して、指定されたパラメータがNULLでない場合にのみその値でカラムを更新
            // そうでない場合は、カラムの現在の値を保持
            int updatedRows = jdbcClient.sql("""
                     UPDATE approval_requests
                     SET
                         year_month = COALESCE(:yearMonth, year_month),
                         total_hours = COALESCE(:totalHours, total_hours),
                         status = COALESCE(:status, status),
                         request_date = COALESCE(:requestDate, request_date),
                         approval_date = COALESCE(:approvalDate, approval_date),
                         approved_by = COALESCE(:approvedBy, approved_by)
                     WHERE id = :id
             """)
                    .param("yearMonth", changeDate)
                    .param("totalHours", approvalRequest.totalHours())
                    .param("status", approvalRequest.status().name())
                    .param("requestDate", approvalRequest.requestDate())
                    .param("approvalDate", approvalRequest.approvalDate())
                    .param("approvedBy", approvalRequest.approvedBy())
                    .param("id", approvalRequest.id())
                    .update();
            if (updatedRows == 1) {
                return findById(approvalRequest.id()).orElseThrow(
                        () -> new RuntimeException("UPDATE operation was unsuccessful"));
            } else if (updatedRows == 0) {
                throw new RuntimeException("Failed to update approval request for ID " + approvalRequest.id());
            } else {
                throw new RuntimeException("Failed to update approval request for multiple updates are performed");
            }
        }
    }

    public void deleteById(String id) {
        jdbcClient.sql("""
                DELETE FROM approval_requests
                WHERE id = :id
                """)
                .param("id", id)
                .update();
    }
}
