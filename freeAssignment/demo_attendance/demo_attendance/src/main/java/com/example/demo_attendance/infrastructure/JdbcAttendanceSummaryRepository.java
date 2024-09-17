package com.example.demo_attendance.infrastructure;

import com.example.demo_attendance.domain.entity.AttendanceSummary;
import com.example.demo_attendance.domain.repository.AttendanceSummaryRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAttendanceSummaryRepository implements AttendanceSummaryRepository {
    private final JdbcClient jdbcClient;

    public JdbcAttendanceSummaryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<AttendanceSummary> findMonthlyAttendanceSummaryByEmployeeId(String employeeId) {
        // date_trunc関数でmonthを設定し日付以下を切り落とし
        // EXTRACT(EPOCH FROM (finish_work - begin_work)) / 3600に書き換えた
        // h2だけならDATEDIFFでよかった
        // Testはpostgresを使っていたのでエラーが起きた
        // どうせなら両方に通用する書き方がこれ
        return jdbcClient.sql("""
                    SELECT
                        employee_id,
                        date_trunc('month', begin_work) AS year_month,
                        SUM(EXTRACT(EPOCH FROM (finish_work - begin_work)) / 3600) AS total_hours
                    FROM
                        attendance
                    WHERE
                        employee_id = :employeeId
                    GROUP BY
                        employee_id,
                        year_month
                    ORDER BY
                        year_month;
                """)
                .param("employeeId", employeeId)
                .query(
                        // sqlから得られたデータをAttendanceSummaryの型に直す
                        (ResultSet resultSet, int rowNum) -> {
                            String employeeIdResult = resultSet.getString("employee_id");
                            LocalDate yearMonthDate = resultSet.getDate("year_month").toLocalDate();
                            double totalHours = resultSet.getDouble("total_hours");

                            YearMonth yearMonth = YearMonth.from(yearMonthDate);

                            return new AttendanceSummary(employeeIdResult, yearMonth, totalHours);
                        }
                )
                .list();
    }

    public Optional<AttendanceSummary> findByEmployeeIdAndYearMonth(String employeeId, YearMonth yearMonth) {
        LocalDate changeDate = yearMonth.atDay(1);

        return jdbcClient.sql("""
                SELECT
                    employee_id,
                    date_trunc('month', begin_work) AS year_month,
                    SUM(EXTRACT(EPOCH FROM (finish_work - begin_work)) / 60) AS total_hours
                FROM
                    attendance
                WHERE
                    employee_id = :employeeId
                AND
                    date_trunc('month', begin_work) = :yearMonth
                GROUP BY
                    employee_id,
                    year_month
                ORDER BY
                    year_month;
            """)
                .param("employeeId", employeeId)
                .param("yearMonth", changeDate)
                .query(
                        (ResultSet resultSet, int rowNum) -> {
                            String employeeIdResult = resultSet.getString("employee_id");
                            LocalDate yearMonthResult = resultSet.getDate("year_month").toLocalDate();
                            double totalHours = resultSet.getDouble("total_hours");

                            YearMonth resultYearMonth = YearMonth.from(yearMonthResult);

                            return new AttendanceSummary(employeeIdResult, resultYearMonth, totalHours);
                        }
                )
                .optional();
    }
}
