package com.example.demo_attendance.presentation;

import com.example.demo_attendance.domain.entity.Attendance;
import com.example.demo_attendance.application.AttendanceService;
import com.example.demo_attendance.domain.entity.AttendanceSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/attendances")
public class AttendanceRestController {
    private final AttendanceService attendanceService;

    public AttendanceRestController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.viewAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(
            @PathVariable String id
    ) {
        return attendanceService.viewAttendanceById(id)
                // mapでOptionalが値を持っていたらResponseEntity::ok
                // ResponseEntity::okは、Attendanceオブジェクトが存在する場合に、
                // そのオブジェクトを含むHTTP 200 OKレスポンスを作成
                .map(ResponseEntity::ok)
                // ResponseEntity.notFound().build()は、HTTP 404 Not Foundレスポンスを作成
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/searchAttendance")
    public ResponseEntity<Attendance> getAttendanceByEmployeeIdAndDate(
            @RequestParam String employeeId,
            @RequestParam LocalDate date // yyyy-MM-ddの形で入力
    ) {
        return attendanceService.viewAttendanceByEmployeeIdAndDate(employeeId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/monthlySummary")
    public ResponseEntity<List<AttendanceSummary>> getMonthlyAttendanceSummary(
            @RequestParam String employeeId
    ) {
        List<AttendanceSummary> summary = attendanceService.viewAttendanceSummaryByEmployeeId(employeeId);
        return ResponseEntity.ok(summary);
    }

    @PostMapping
    public ResponseEntity<?> postAttendance(
            @RequestBody Attendance attendance
            // beginWork, finishWorkはyyyy-MM-dd hh:mm:ss
    ) {
        // ResponseEntityにすることでHTTPステータスコードを制御
        Attendance newAttendance;

        if (attendance.finishWork() == null) {
            // 退勤時間が指定されていない場合は、出勤の記録のみを行う
            newAttendance = attendanceService.recordBeginWork(attendance);
        } else {
            // 出勤および退勤の両方のデータを記録する
            newAttendance = attendanceService.recordBeginWorkAndFinishWork(attendance);
        }

        // 新しいリソースのURIを作成する
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAttendance.id())
                .toUri();

        // Createdステータスと新しいリソースのURIを返す
        // リソースが正常に作成されたことを示すために適切なHTTPステータスコードで応答
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putAttendance(
            @PathVariable String id,
            @RequestBody Attendance attendance
            // beginWork, finishWorkはyyyy-MM-dd hh:mm:ss
    ) {
        if (!Objects.equals(id, attendance.id())) {
            return ResponseEntity.badRequest().body("Request ID and body ID do not match.");
        }

        if (attendance.beginWork() == null) {
            attendanceService.recordFinishWork(id, attendance.finishWork());
        } else {
            attendanceService.updateRecordedAttendance(id, attendance.beginWork(), attendance.finishWork());
        }
        return ResponseEntity.ok("Attendance updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable String id
    ) {
        attendanceService.deleteById(id);
        // ResponseEntity.noContent().build()は、HTTP 204 No Contentレスポンスを作成
        return ResponseEntity.noContent().build();
    }
}
