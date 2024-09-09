package com.example.demo_attendance.application;

import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.domain.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final Clock clock;

    public AttendanceService(AttendanceRepository AttendanceRepository, Clock clock) {
        this.attendanceRepository = AttendanceRepository;
        this.clock = clock;
    }

    public List<Attendance> viewAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> viewAttendanceById(String id) {
        return attendanceRepository.findById(id);
    }

    public Optional<Attendance> viewAttendanceByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }

    // 出勤のみのデータを追加したい場合に使用
    public Attendance recordBeginWork(Attendance attendance) {
        if (attendance.id() != null) {
            throw new IllegalArgumentException("ID should be null for new records.");
        }

        LocalDateTime newBeginWork = attendance.beginWork() == null
                ? getNowDateTime() // 出勤時間が指定されていればその時間
                : attendance.beginWork();  // 指定されていなければ自動で現在時刻

        return attendanceRepository.save(attendance.updateBeginWork(newBeginWork));
    }

    // 出退勤両方のデータを追加したい場合
    // 出勤の打刻忘れや、Time-3のように手動で入力する場合に同時入力があることを想定
    public Attendance recordBeginWorkAndFinishWork(Attendance attendance) {
        LocalDateTime newBeginWork = attendance.beginWork();
        LocalDateTime newFinishWork = attendance.finishWork() == null
                ? getNowDateTime() // 退勤時間が指定されていればその時間
                : attendance.finishWork(); // 指定されていなければ自動で現在時刻

        return attendanceRepository.save(attendance.updateBothWork(newBeginWork, newFinishWork));
    }

    // 退勤データだけの場合
    // 出勤データの日時と照らし合わせる必要があるのでidも必要
    public void recordFinishWork(String id, LocalDateTime finishWork) {
        LocalDateTime newFinishWork = finishWork == null
                ? getNowDateTime() // 退勤時間が指定されていればその時間
                : finishWork;              // 指定されていなければ自動で現在時刻

        Optional<Attendance> getRecordedAttendance = attendanceRepository.findById(id);
        // Optional が空でないことを確認してから値を取得する
        // そうしないとエラー処理が不適切な時に発生するNoSuchElementExceptionが発生する
        getRecordedAttendance.ifPresentOrElse(attendance -> {
            // （非Nullの場合の処理）
            attendanceRepository.save(new Attendance(
                    attendance.id(),
                    attendance.employeeId(),
                    attendance.beginWork(),
                    newFinishWork));
        }, () -> {
            // （Nullの場合の処理）
            // 出勤記録が存在しない場合、適切なエラーハンドリングを行う
            throw new RuntimeException("No recorded attendance found for ID " + id);
        });
    }

    // 勤怠データの更新
    // 手動で入力する場合の入力ミスや、打刻ミスなどを想定
    public void updateRecordedAttendance(String id, LocalDateTime beginWork, LocalDateTime finishWork) {
        // 記録された出勤情報を取得
        // 出勤情報が見つからなかった場合にエラーを投げる
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No recorded attendance found for ID " + id));

        // ここでupdateする値がNullかどうかのチェックを行う
        LocalDateTime updateBeginWork = Optional.ofNullable(beginWork).orElse(attendance.beginWork());
        LocalDateTime updateFinishWork = Optional.ofNullable(finishWork).orElse(attendance.finishWork());

        attendanceRepository.save(
                new Attendance(attendance.id(), attendance.employeeId(), updateBeginWork, updateFinishWork));
    }

    public void deleteById(String id) {
        attendanceRepository.deleteById(id);
    }

    private LocalDateTime getNowDateTime() {
        return LocalDateTime.now(clock).withNano(0);
    }
}
