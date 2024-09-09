package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.Attendance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class JdbcAttendanceRepositoryTest {
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
            VALUES ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba5', 'emp000', '2024-08-30 09:00:00', '2024-08-30 17:00:00');
            """
    })
    void testFindAll() {
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        List<Attendance> attendances = jdbcAttendanceRepository.findAll();

        Assertions.assertThat(attendances).isNotEmpty();
        Assertions.assertThat(attendances.getFirst().id()).isNotNull();
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('b5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba5', 'emp100', '2024-07-30 09:00:00', '2024-07-30 17:00:00');
            """
    })
    void testFindById() {
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        Optional<Attendance> attendance = jdbcAttendanceRepository.findById("b5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba5");

        attendance.ifPresentOrElse(actual -> {
            Assertions.assertThat(actual.id()).isEqualTo("b5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba5");
            Assertions.assertThat(actual.employeeId()).isEqualTo("emp100");
            Assertions.assertThat(actual.beginWork()).isEqualTo(LocalDateTime.of(2024, 7, 30, 9, 0));
            Assertions.assertThat(actual.finishWork()).isEqualTo(LocalDateTime.of(2024, 7, 30, 17, 0));
        }, () -> {
            Assertions.fail("Required not null");
        });
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-0a0a-8b95-5a5d6d881ba5', 'emp200', '2024-06-30 09:00:00', '2024-06-30 17:00:00');
            """
    })
    void testFindByEmployeeIdAndDate() {
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        Optional<Attendance> attendance = jdbcAttendanceRepository
                .findByEmployeeIdAndDate("emp200", LocalDate.parse("2024-06-30"));

        Assertions.assertThat(attendance).isPresent();
        Assertions.assertThat(attendance.get().employeeId()).isEqualTo("emp200");
        Assertions.assertThat(attendance.get().getDate()).isEqualTo(LocalDate.parse("2024-06-30"));
    }

    @Test
    void testSaveINSERT() {
        JdbcAttendanceRepository jdbcAttendanceRepository  = new JdbcAttendanceRepository(jdbcClient);

        Attendance attendance = new Attendance(
                null,
                "emp300",
                LocalDateTime.of(2024, 9, 15, 8, 0, 0),
                LocalDateTime.of(2024, 9, 15, 17, 0, 0));

        Attendance savedAttendance = jdbcAttendanceRepository.save(attendance);

        Assertions.assertThat(savedAttendance.id()).isNotNull();
        Assertions.assertThat(savedAttendance.employeeId()).isEqualTo("emp300");
        Assertions.assertThat(savedAttendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 9, 15, 8, 0, 0));
        Assertions.assertThat(savedAttendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 9, 15, 17, 0, 0));

        jdbcClient.sql("""
                     SELECT 
                         id,
                         employee_id,
                         begin_work,
                         finish_work
                     FROM 
                         attendance
                     WHERE
                         employee_id = 'emp300'
                 """)
                .query(DataClassRowMapper.class)
                .single();
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba6', 'emp400', '2024-06-30 09:00:00', null);
            """
    })
    void testSaveUPDATE() {
        JdbcAttendanceRepository jdbcAttendanceRepository  = new JdbcAttendanceRepository(jdbcClient);

        Attendance attendance = new Attendance(
                "a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba6",
                "emp400",
                LocalDateTime.of(2024, 8, 25, 10, 30, 0),
                LocalDateTime.of(2024, 8, 25, 19, 30, 0));

        Attendance savedAttendance = jdbcAttendanceRepository.save(attendance);
        Assertions.assertThat(savedAttendance.id()).isNotNull();
        Assertions.assertThat(savedAttendance.id()).isEqualTo("a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba6");
        Assertions.assertThat(savedAttendance.employeeId()).isEqualTo("emp400");
        Assertions.assertThat(savedAttendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 8, 25, 10, 30, 0));
        Assertions.assertThat(savedAttendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 8, 25, 19, 30, 0));
    }

    @Test
    @Sql(statements = {
            """
            INSERT INTO attendance (id, employee_id, begin_work, finish_work) 
            VALUES ('a5e5e9d4-40d7-4f7d-8b95-5555555ba6', 'emp500', '2024-01-30 09:00:00', null);
            """
    })
    void testDeleteById() {
        // 事前に削除対象のデータを準備する
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        // 初期状態で対象が存在することを確認する
        Optional<Attendance> attendanceBeforeDelete = jdbcAttendanceRepository
                .findById("a5e5e9d4-40d7-4f7d-8b95-5555555ba6");

        Assertions.assertThat(attendanceBeforeDelete).isPresent();
        Assertions.assertThat(attendanceBeforeDelete.get().employeeId()).isEqualTo("emp500");

        // 削除操作を実行する
        jdbcAttendanceRepository.deleteById("a5e5e9d4-40d7-4f7d-8b95-5555555ba6");

        // 削除後に再度確認する
        Optional<Attendance> attendanceAfterDelete = jdbcAttendanceRepository
                .findById("a5e5e9d4-40d7-4f7d-8b95-5555555ba6");

        Assertions.assertThat(attendanceAfterDelete).isNotPresent();
    }

    @Test
    void testSaveInvalidData() {
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        // 不正なデータを作成
        Attendance invalidAttendance = new Attendance(null, null, LocalDateTime.now(), null);

        // 例外が発生することを確認する
        Assertions.assertThatThrownBy(() -> jdbcAttendanceRepository.save(invalidAttendance))
                .isInstanceOf(DataIntegrityViolationException.class);  // 適切な例外クラスを指定
    }

    @Test
    void testFindByEmployeeIdAndDate_NoRecord() {
        JdbcAttendanceRepository jdbcAttendanceRepository = new JdbcAttendanceRepository(jdbcClient);

        Optional<Attendance> result = jdbcAttendanceRepository.findByEmployeeIdAndDate("a", LocalDate.parse("2024-08-25"));

        Assertions.assertThat(result).isNotPresent();
    }

    @Test
    @Sql(statements = {
            """
            DELETE FROM attendance
            WHERE id = 'abc';
            """
    })
    void testDeleteById_NonExistentId() {
        JdbcAttendanceRepository jdbcAttendanceRepository  = new JdbcAttendanceRepository(jdbcClient);

        // 事前に削除対象のデータが存在しないことを確認する
        Optional<Attendance> attendanceBeforeDelete = jdbcAttendanceRepository.findById("abc");

        Assertions.assertThat(attendanceBeforeDelete).isNotPresent();

        // 存在しないIDで削除操作を実行する
        jdbcAttendanceRepository.deleteById("abc");

        // 再度、削除操作が行われた結果を確認する
        Optional<Attendance> attendanceAfterDelete = jdbcAttendanceRepository.findById("abc");

        Assertions.assertThat(attendanceAfterDelete).isNotPresent();
    }
}
