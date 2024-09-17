package com.example.demo_attendance.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// ControllerAdviceで共通のエラー処理にし、ひとつひとつのControllerの見通しを良くする
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    // リクエストの内容が不正な場合に対する処理
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    // その他の予期しないエラーが発生した場合の処理
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }
}
