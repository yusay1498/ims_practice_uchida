package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.domain.entity.AttendanceSummary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
public class JdbcAttendanceSummaryRepositoryTest {
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
            INSERT INTO attendance (id, employee_id, begin_work, finish_work)
            VALUES ('a5e5e9d4-1111-4f7d-8b95-5a5d6d881ba5', 'emp050', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    void testFindMonthlyAttendanceSummaryByEmployeeId() {
        JdbcAttendanceSummaryRepository jdbcAttendanceSummaryRepository = new JdbcAttendanceSummaryRepository(jdbcClient);

        List<AttendanceSummary> attendanceSummaries = jdbcAttendanceSummaryRepository.findMonthlyAttendanceSummaryByEmployeeId("emp050");

        Assertions.assertThat(attendanceSummaries).isNotEmpty();
        Assertions.assertThat(attendanceSummaries.getFirst().employeeId()).isNotNull();
        Assertions.assertThat(attendanceSummaries.getFirst().employeeId()).isEqualTo("emp050");
        Assertions.assertThat(attendanceSummaries.getFirst().yearMonth()).isEqualTo(YearMonth.of(2024, 8));
        Assertions.assertThat(attendanceSummaries.getFirst().totalHours()).isEqualTo(8.0);
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('b5e5e9d4-40d7-4444-8b95-5a5d6d881ba5', 'emp060', '2024-07-30 09:00:00', '2024-07-30 17:00:00');
            """
    })
    void testFindByEmployeeIdAndYearMonth() {
        JdbcAttendanceSummaryRepository jdbcAttendanceSummaryRepository = new JdbcAttendanceSummaryRepository(jdbcClient);

        Optional<AttendanceSummary> attendanceSummary = jdbcAttendanceSummaryRepository.findByEmployeeIdAndYearMonth("emp060", YearMonth.of(2024, 7));

        attendanceSummary.ifPresentOrElse(actual -> {
            Assertions.assertThat(actual.employeeId()).isEqualTo("emp060");
            Assertions.assertThat(actual.yearMonth()).isEqualTo(YearMonth.of(2024, 7));
            Assertions.assertThat(actual.totalHours()).isEqualTo(480);
        }, () -> {
            Assertions.fail("Required not null");
        });
    }
}
