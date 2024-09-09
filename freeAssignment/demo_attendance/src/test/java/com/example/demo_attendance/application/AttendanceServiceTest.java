package com.example.demo_attendance.application;

import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.domain.repository.AttendanceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AttendanceServiceTest {
    @Test
    void testViewAllAttendance_Find() {
        List<Attendance> testAttendances = List.of(
                new Attendance(
                        "UUID1",
                        "emp999",
                        LocalDateTime.of(2024, 8, 30, 9, 45, 0),
                        LocalDateTime.of(2024, 8,30, 18, 10, 0)
                ),
                new Attendance(
                        "UUID2",
                        "emp998",
                        LocalDateTime.of(2024, 8, 29, 9, 45, 0),
                        LocalDateTime.of(2024, 8,29, 18, 10, 0)
                ),
                new Attendance(
                        "UUID3",
                        "emp997",
                        LocalDateTime.of(2024, 8, 28, 9, 45, 0),
                        LocalDateTime.of(2024, 8,28, 18, 10, 0)
                )
        );
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Mockito.doReturn(testAttendances).when(mockedRepo).findAll();

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        List<Attendance> actual = attendanceService.viewAllAttendances();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(testAttendances.size());
        Assertions.assertThat(actual.getFirst()).isEqualTo(testAttendances.getFirst());
        Assertions.assertThat(actual.get(1)).isEqualTo(testAttendances.get(1));
        Assertions.assertThat(actual.get(2)).isEqualTo(testAttendances.get(2));
    }

    @Test
    void testViewAllAttendances_Empty() {
        // Repositoryインターフェースをモックで作成
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Mockito.doReturn(Collections.emptyList()).when(mockedRepo).findAll();

        Clock mockedClock = Mockito.mock(Clock.class);

        // モックでサービスクラスを呼び出す
        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        List<Attendance> actual = attendanceService.viewAllAttendances();

        // 何も入っていないので空で返す
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEmpty();

        // 呼び出された回数を確認
        Mockito.verify(mockedRepo, Mockito.times(1)).findAll();
    }

    @Test
    void testViewAttendanceById_Find() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Attendance findAttendance = new Attendance(
                "UUID4",
                "emp996",
                LocalDateTime.of(2024, 7, 30, 9, 45, 0),
                LocalDateTime.of(2024, 7,30, 18, 10, 0)
        );
        Optional<Attendance> returnAttendance = Optional.of(findAttendance);
        String testId = "UUID4";
        Mockito.doReturn(returnAttendance).when(mockedRepo).findById(testId);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Optional<Attendance> actual = attendanceService.viewAttendanceById(testId);

        actual.ifPresentOrElse(attendance -> {
            Assertions.assertThat(attendance.id()).isEqualTo("UUID4");
            Assertions.assertThat(attendance.employeeId()).isEqualTo("emp996");
            Assertions.assertThat(attendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 7, 30, 9, 45, 0));
            Assertions.assertThat(attendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 7, 30, 18, 10, 0));
        }, () -> {
            Assertions.fail("Required not null");
        });

        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);
    }

    @Test
    void testViewAttendanceById_Empty() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "testId";
        Mockito.doReturn(Optional.empty()).when(mockedRepo).findById(testId);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Optional<Attendance> actual = attendanceService.viewAttendanceById(testId);

        Assertions.assertThat(actual).isEmpty();
        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);
    }

    @Test
    void testViewAttendanceByEmployeeIdAndDate_Find() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Attendance findAttendance = new Attendance(
                "UUID5",
                "emp995",
                LocalDateTime.of(2024, 6, 30, 9, 45, 0),
                LocalDateTime.of(2024, 6,30, 18, 10, 0)
        );
        Optional<Attendance> returnAttendance = Optional.of(findAttendance);
        String testId = "UUID5";
        LocalDate testDate = LocalDate.of(2024, 6, 30);
        Mockito.doReturn(returnAttendance).when(mockedRepo).findByEmployeeIdAndDate(testId, testDate);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Optional<Attendance> actual = attendanceService.viewAttendanceByEmployeeIdAndDate(testId, testDate);

        actual.ifPresentOrElse(attendance -> {
            Assertions.assertThat(attendance.id()).isEqualTo("UUID5");
            Assertions.assertThat(attendance.employeeId()).isEqualTo("emp995");
            Assertions.assertThat(attendance.beginWork()).isEqualTo(LocalDateTime.of(2024, 6, 30, 9, 45, 0));
            Assertions.assertThat(attendance.finishWork()).isEqualTo(LocalDateTime.of(2024, 6, 30, 18, 10, 0));
        }, () -> {
            Assertions.fail("Required not null");
        });

        Mockito.verify(mockedRepo, Mockito.times(1)).findByEmployeeIdAndDate(testId, testDate);
    }

    @Test
    void testViewAttendanceByEmployeeIdAndDate_Empty() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "testId";
        LocalDate testDate = LocalDate.of(2020, 1, 1);
        Mockito.doReturn(Optional.empty()).when(mockedRepo).findByEmployeeIdAndDate(testId, testDate);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Optional<Attendance> actual = attendanceService.viewAttendanceByEmployeeIdAndDate(testId, testDate);

        Assertions.assertThat(actual).isEmpty();
        Mockito.verify(mockedRepo, Mockito.times(1)).findByEmployeeIdAndDate(testId, testDate);
    }

    @Test
    void testRecordBeginWork() {
        // モックのリポジトリを作成
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Attendance testAttendance = new Attendance(
                null,
                "994",
                LocalDateTime.of(2024, 8, 30, 9, 0,0),
                null
        );
        // Repositoryインターフェースは自動でID生成してくれるわけではないので
        // そのままの値が返ってくる
        Mockito.doReturn(testAttendance).when(mockedRepo).save(Mockito.any(Attendance.class));

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Attendance actual = attendanceService.recordBeginWork(testAttendance);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(testAttendance);

        Mockito.verify(mockedRepo, Mockito.times(1)).save(Mockito.any(Attendance.class));
    }

    @Test
    void testRecordBeginWorkAndFinishWork() {
        // モックのリポジトリを作成
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        Attendance testAttendance = new Attendance(
                null,
                "emp993",
                LocalDateTime.of(2024, 8, 31, 9, 0,0),
                LocalDateTime.of(2024, 8, 31, 18, 0,0)
        );
        Mockito.doReturn(testAttendance).when(mockedRepo).save(Mockito.any(Attendance.class));

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        Attendance actual = attendanceService.recordBeginWorkAndFinishWork(testAttendance);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(testAttendance);

        Mockito.verify(mockedRepo, Mockito.times(1)).save(Mockito.any(Attendance.class));
    }

    // 正常に動作する場合
    @Test
    void testRecordFinishWork_Success() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "UUID6";
        Attendance beforeAttendance = new Attendance(
                "UUID6",
                "emp992",
                LocalDateTime.of(2024,9,1, 9, 45, 0),
                null
        );
        Attendance afterAttendance = new Attendance(
                "UUID6",
                "emp992",
                LocalDateTime.of(2024,9,1, 9, 45, 0),
                LocalDateTime.of(2024, 9, 1, 18, 10)
        );
        Mockito.doReturn(Optional.of(beforeAttendance)).when(mockedRepo).findById(testId);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);

        attendanceService.recordFinishWork(testId, LocalDateTime.of(2024, 9, 1, 18, 10));

        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);
        Mockito.verify(mockedRepo, Mockito.times(1)).save(Mockito.argThat(arg ->
                arg.equals(afterAttendance)));
    }

    // 存在しないIDに対する退勤処理
    // 出勤はNotNullに設定されているので退勤だけ登録はできない
    @Test
    void testRecordFinishWork_FindNoRecord() {
        // モックのリポジトリを作成
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "testId";
        Mockito.doReturn(Optional.empty()).when(mockedRepo).findById(testId);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);

        // recordFinishWorkの呼び出しと例外の検証
        Assertions.assertThatThrownBy(() -> attendanceService.recordFinishWork(testId,
                        LocalDateTime.of(2024, 8, 30, 17, 0, 0)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No recorded attendance found for ID " + testId);

        // findByIdメソッドが1回呼び出されたことを確認
        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);

        // saveメソッドは呼び出されないことを確認
        Mockito.verify(mockedRepo, Mockito.never()).save(Mockito.any(Attendance.class));
    }

    @Test
    void testUpdateAttendance_Success() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "UUID7";
        Attendance beforeAttendance = new Attendance(
                "UUID7",
                "emp991",
                LocalDateTime.of(2024,9,1, 9, 45, 0),
                null
        );
        Attendance afterAttendance = new Attendance(
                "UUID7",
                "emp991",
                LocalDateTime.of(2024,9,15, 9, 45, 0),
                LocalDateTime.of(2024, 9, 15, 18, 10)
        );
        Mockito.doReturn(Optional.of(beforeAttendance)).when(mockedRepo).findById(testId);

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);

        attendanceService.updateRecordedAttendance(
                testId,
                LocalDateTime.of(2024, 9, 15, 9, 45, 0),
                LocalDateTime.of(2024, 9, 15, 18, 10, 0)
        );

        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);
        Mockito.verify(mockedRepo, Mockito.times(1)).save(Mockito.argThat(arg ->
                arg.equals(afterAttendance)));
    }

    // 存在しないIDに対するUPDATE
    @Test
    void testUpdateRecordAttendance_FindNoRecord() {
        // モックのリポジトリを作成
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "testId";
        Mockito.doReturn(Optional.empty()).when(mockedRepo).findById(testId);

        // findByIdが空のOptionalを返す設定
        Mockito.when(mockedRepo.findById(testId)).thenReturn(Optional.empty());

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);

        // recordFinishWorkの呼び出しと例外の検証
        Assertions.assertThatThrownBy(() -> attendanceService.updateRecordedAttendance(testId,
                        LocalDateTime.of(2024, 8, 30, 9, 0, 0),
                        LocalDateTime.of(2024, 8, 30, 18, 0,0)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("No recorded attendance found for ID " + testId);

        // findByIdメソッドが1回呼び出されたことを確認
        Mockito.verify(mockedRepo, Mockito.times(1)).findById(testId);

        // saveメソッドは呼び出されないことを確認
        Mockito.verify(mockedRepo, Mockito.never()).save(Mockito.any(Attendance.class));
    }

    @Test
    void testDeleteById() {
        AttendanceRepository mockedRepo = Mockito.mock(AttendanceRepository.class);
        String testId = "testId";

        Clock mockedClock = Mockito.mock(Clock.class);

        AttendanceService attendanceService = new AttendanceService(mockedRepo, mockedClock);
        attendanceService.deleteById(testId);

        Mockito.verify(mockedRepo, Mockito.times(1)).deleteById(testId);
    }
}
