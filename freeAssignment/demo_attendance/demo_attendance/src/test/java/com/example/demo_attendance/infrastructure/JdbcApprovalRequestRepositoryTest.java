package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.ApprovalRequest;
import com.example.demo_attendance.domain.entity.ApprovalStatus;
import com.example.demo_attendance.domain.entity.Attendance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JdbcApprovalRequestRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName
            .parse("postgres"));

    @BeforeAll
    static void startContainers() {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void repositoryProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

        registry.add("spring.sql.init.mode", () -> "always");
        registry.add("spring.h2.console.enabled", () -> "true");
    }

    @Autowired
    JdbcClient jdbcClient;

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-8888-1e2f3g4h5i6j', 'emp600', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    void testFindAll() {
        JdbcApprovalRequestRepository approvalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        List<ApprovalRequest> approvalRequests = approvalRequestRepository.findAll();

        Assertions.assertThat(approvalRequests).isNotEmpty();
        Assertions.assertThat(approvalRequests.getFirst().id()).isNotNull();
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-8887-1e2f3g4h5i6j', 'emp601', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    void testFindById() {
        JdbcApprovalRequestRepository approvalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        Optional<ApprovalRequest> approvalRequest = approvalRequestRepository.findById("1a2b3c4d-5e6f-7a8b-8887-1e2f3g4h5i6j");

        approvalRequest.ifPresentOrElse(actual -> {
            Assertions.assertThat(actual.id()).isEqualTo("1a2b3c4d-5e6f-7a8b-8887-1e2f3g4h5i6j");
            Assertions.assertThat(actual.employeeId()).isEqualTo("emp601");
            Assertions.assertThat(actual.yearMonth()).isEqualTo(YearMonth.of(2024, 5));
            Assertions.assertThat(actual.totalHours()).isEqualTo(9000);
            Assertions.assertThat(actual.status()).isEqualTo(ApprovalStatus.PENDING);
            Assertions.assertThat(actual.requestDate()).isEqualTo(LocalDateTime.of(2024, 5, 31 ,8, 0, 0));
            Assertions.assertThat(actual.approvalDate()).isNull();
            Assertions.assertThat(actual.approvedBy()).isNull();
        }, () -> {
            Assertions.fail("Required not null");
        });
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-8877-1e2f3g4h5i6j', 'emp602', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    void testFindByEmployeeIdAndYearMonth() {
        JdbcApprovalRequestRepository approvalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        Optional<ApprovalRequest> approvalRequest = approvalRequestRepository.findByEmployeeIdAndYearMonth(
                "emp602", YearMonth.of(2024, 5)
        );

        approvalRequest.ifPresentOrElse(actual -> {
            Assertions.assertThat(actual.id()).isEqualTo("1a2b3c4d-5e6f-7a8b-8877-1e2f3g4h5i6j");
            Assertions.assertThat(actual.employeeId()).isEqualTo("emp602");
            Assertions.assertThat(actual.yearMonth()).isEqualTo(YearMonth.of(2024, 5));
            Assertions.assertThat(actual.totalHours()).isEqualTo(9000);
            Assertions.assertThat(actual.status()).isEqualTo(ApprovalStatus.PENDING);
            Assertions.assertThat(actual.requestDate()).isEqualTo(LocalDateTime.of(2024, 5, 31 ,8, 0, 0));
            Assertions.assertThat(actual.approvalDate()).isNull();
            Assertions.assertThat(actual.approvedBy()).isNull();
        }, () -> {
            Assertions.fail("Required not null");
        });
    }

    @Test
    void testSaveINSERT() {
        JdbcApprovalRequestRepository jdbcApprovalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        ApprovalRequest approvalRequest = new ApprovalRequest(
                null,
                "emp191",
                YearMonth.of(2024, 3),
                9000,
                ApprovalStatus.PENDING,
                LocalDateTime.of(2024, 3, 31, 10, 0, 0),
                null,
                null
        );

        ApprovalRequest savedApprovalRequest = jdbcApprovalRequestRepository.save(approvalRequest);

        Assertions.assertThat(savedApprovalRequest.id()).isNotNull();
        Assertions.assertThat(savedApprovalRequest.employeeId()).isEqualTo("emp191");
        Assertions.assertThat(savedApprovalRequest.yearMonth()).isEqualTo(YearMonth.of(2024, 3));
        Assertions.assertThat(savedApprovalRequest.totalHours()).isEqualTo(9000);
        Assertions.assertThat(savedApprovalRequest.status()).isEqualTo(ApprovalStatus.PENDING);
        Assertions.assertThat(savedApprovalRequest.requestDate()).isEqualTo(LocalDateTime.of(2024, 3, 31, 10, 0, 0));
        Assertions.assertThat(savedApprovalRequest.approvalDate()).isNull();
        Assertions.assertThat(savedApprovalRequest.approvedBy()).isNull();

        jdbcClient.sql("""
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
                         employee_id = 'emp191'
                 """)
                .query(DataClassRowMapper.class)
                .single();
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-8777-1e2f3g4h5i6j', 'emp603', '2024-05-01', 9000.0, 'PENDING', '2024-05-31 08:00:00', NULL, NULL);
            """
    })
    void testSaveUPDATE_APPROVED() {
        JdbcApprovalRequestRepository jdbcApprovalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        ApprovalRequest approvalRequest = new ApprovalRequest(
                "1a2b3c4d-5e6f-7a8b-8777-1e2f3g4h5i6j",
                null,
                null,
                9000,
                ApprovalStatus.APPROVED,
                null,
                LocalDateTime.of(2024, 5, 31, 18, 0, 0),
                "emp000"
        );

        ApprovalRequest savedApprovalRequest = jdbcApprovalRequestRepository.save(approvalRequest);

        Assertions.assertThat(savedApprovalRequest.id()).isNotNull();
        Assertions.assertThat(savedApprovalRequest.employeeId()).isEqualTo("emp603");
        Assertions.assertThat(savedApprovalRequest.yearMonth()).isEqualTo(YearMonth.of(2024, 5));
        Assertions.assertThat(savedApprovalRequest.totalHours()).isEqualTo(9000);
        Assertions.assertThat(savedApprovalRequest.status()).isEqualTo(ApprovalStatus.APPROVED);
        Assertions.assertThat(savedApprovalRequest.requestDate()).isEqualTo(LocalDateTime.of(2024, 5, 31, 8, 0, 0));
        Assertions.assertThat(savedApprovalRequest.approvalDate()).isEqualTo(LocalDateTime.of(2024, 5, 31, 18, 0, 0));
        Assertions.assertThat(savedApprovalRequest.approvedBy()).isEqualTo("emp000");
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-7777-1e2f3g4h5i6j', 'emp604', '2024-05-01', 9000.0, 'REJECTED', '2024-05-31 08:00:00', '2024-05-31 18:00:00', 'emp000');
            """
    })
    void testSaveUPDATE_REJECTED() {
        JdbcApprovalRequestRepository jdbcApprovalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        ApprovalRequest approvalRequest = new ApprovalRequest(
                "1a2b3c4d-5e6f-7a8b-7777-1e2f3g4h5i6j",
                null,
                YearMonth.of(2024, 5),
                9600,
                ApprovalStatus.PENDING,
                LocalDateTime.of(2024, 6, 1, 8, 0, 0),
                null,
                null
        );

        ApprovalRequest savedApprovalRequest = jdbcApprovalRequestRepository.save(approvalRequest);

        Assertions.assertThat(savedApprovalRequest.id()).isNotNull();
        Assertions.assertThat(savedApprovalRequest.employeeId()).isEqualTo("emp604");
        Assertions.assertThat(savedApprovalRequest.yearMonth()).isEqualTo(YearMonth.of(2024, 5));
        Assertions.assertThat(savedApprovalRequest.totalHours()).isEqualTo(9600);
        Assertions.assertThat(savedApprovalRequest.status()).isEqualTo(ApprovalStatus.PENDING);
        Assertions.assertThat(savedApprovalRequest.requestDate()).isEqualTo(LocalDateTime.of(2024, 6, 1, 8, 0, 0));
        Assertions.assertThat(savedApprovalRequest.approvalDate()).isEqualTo(LocalDateTime.of(2024, 5, 31, 18, 0, 0));
        Assertions.assertThat(savedApprovalRequest.approvedBy()).isEqualTo("emp000");
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
            VALUES ('1a2b3c4d-5e6f-7a8b-6777-1e2f3g4h5i6j', 'emp605', '2024-05-01', 9000.0, 'REJECTED', '2024-05-31 08:00:00', '2024-05-31 18:00:00', 'emp000');
            """
    })
    void testDeleteById() {
        JdbcApprovalRequestRepository jdbcApprovalRequestRepository = new JdbcApprovalRequestRepository(jdbcClient);

        Optional<ApprovalRequest> approvalRequestBeforeDelete = jdbcApprovalRequestRepository
                .findById("1a2b3c4d-5e6f-7a8b-6777-1e2f3g4h5i6j");

        Assertions.assertThat(approvalRequestBeforeDelete).isPresent();
        Assertions.assertThat(approvalRequestBeforeDelete.get().employeeId()).isEqualTo("emp605");

        jdbcApprovalRequestRepository.deleteById("1a2b3c4d-5e6f-7a8b-6777-1e2f3g4h5i6j");

        Optional<ApprovalRequest> approvalRequestAfterDelete = jdbcApprovalRequestRepository
                .findById("1a2b3c4d-5e6f-7a8b-6777-1e2f3g4h5i6j");

        Assertions.assertThat(approvalRequestAfterDelete).isNotPresent();
    }
}
