package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.domain.entity.AttendanceSummary;
import com.example.demo_attendance.domain.repository.AttendanceRepository;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcAttendanceRepository implements AttendanceRepository {
    private final JdbcClient jdbcClient;

    public JdbcAttendanceRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Attendance> findAll() {
        return jdbcClient.sql("""
                     SELECT 
                         id,
                         employee_id,
                         begin_work,
                         finish_work
                     FROM 
                         attendance
                """)
                .query(DataClassRowMapper.newInstance(Attendance.class))
                .list();
    }

    public Optional<Attendance> findById(String id) {
        // Optionalについて調べたことメモ
        // IDが存在しない場合にnullを返す代わりに、Optional.empty()を返すことで、呼び出し元でのnullチェックを明示的に行える
        // Optionalを使用することで、結果が存在するかどうかの判断をより直感的に行えるようになり、コードの可読性と安全性が向上
        // Optionalは、結果が存在しない場合のエラーハンドリングをより簡潔に扱う手段を提供し、例外処理の複雑さを軽減
        return jdbcClient.sql("""
                    SELECT
                        id,
                        employee_id,
                        begin_work,
                        finish_work
                    FROM
                        attendance
                    WHERE
                        id = :id
                """)
                .param("id", id)
                .query(DataClassRowMapper.newInstance(Attendance.class))
                .optional();
    }

    public Optional<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return jdbcClient.sql("""
                    SELECT
                        id,
                        employee_id,
                        begin_work,
                        finish_work
                    FROM
                        attendance
                    WHERE
                        employee_id = :employeeId
                    AND
                        begin_work::date = :workDate
                """)
                .param("employeeId", employeeId)
                .param("workDate", date)
                .query(DataClassRowMapper.newInstance(Attendance.class))
                .optional();
    }

    public Attendance save(Attendance attendance) {
        // idが指定されていなければINSERT
        if (attendance.id() == null) {
            String newId = UUID.randomUUID().toString();

            jdbcClient.sql("""
                    INSERT INTO attendance (id, employee_id, begin_work, finish_work)
                    VALUES (:id, :employeeId, :beginWork, :finishWork)
                """)
                    .param("id", newId)
                    .param("employeeId", attendance.employeeId())
                    .param("beginWork", attendance.beginWork())
                    .param("finishWork", attendance.finishWork())
                    .update();

            // 新しいIDを持つAttendanceオブジェクトを返す
            // Optionalを返すメソッドに対して、Optional.get()を使うのをやめた
            // 代わりにorElseThrowなどのメソッドを使うことで、エラー処理がより明示的になる
            return findById(newId).orElseThrow(
                    () -> new RuntimeException("INSERT operation was unsuccessful"));
        } else {
            // idが存在すればUPDATE
            // COALESCE文を使用して、指定されたパラメータがNULLでない場合にのみその値でカラムを更新
            // そうでない場合は、カラムの現在の値を保持
            int updatedRows = jdbcClient.sql("""
                    UPDATE attendance
                    SET
                        begin_work = COALESCE(:beginWork, begin_work),
                        finish_work = COALESCE(:finishWork, finish_work)
                    WHERE id = :id
                """)
                    .param("beginWork", attendance.beginWork())
                    .param("finishWork", attendance.finishWork())
                    .param("id", attendance.id())
                    .update();

            if (updatedRows == 1) {
                // 更新が成功した場合はそのままAttendanceオブジェクトを返す
                return findById(attendance.id()).orElseThrow(
                        () -> new RuntimeException("UPDATE operation was unsuccessful"));
            } else if (updatedRows == 0) {
                // 更新が成功しなかった場合（idが存在しない場合）の処理
                throw new RuntimeException("Failed to update attendance for ID " + attendance.id());
            } else {
                // 更新が成功しなかった場合（複数更新される場合）の処理
                throw new RuntimeException("Failed to update attendance for multiple updates are performed");
            }
        }
    }

    public void deleteById(String id) {
        jdbcClient.sql("""
                DELETE FROM attendance
                WHERE id = :id
                """)
                .param("id", id)
                .update();
    }
}
