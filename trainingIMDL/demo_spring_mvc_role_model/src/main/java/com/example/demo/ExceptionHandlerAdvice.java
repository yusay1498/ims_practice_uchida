package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * ## @RestControllerAdvice
 *
 * コントローラーの各種メソッドに介入するためのアノテーション
 *
 * `Advice` とは、AOP（アスペクト指向プログラミング）の用語で、
 * 介入時に挿入させる処理内容のことを指している
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    /*
     * ## @ExceptionHandler
     *
     * コントローラー内（コントローラーから呼び出したメソッドの例外も含む）で発生した例外を、
     * 一元的にハンドリングするためのアノテーション
     *
     * アノテーションの引数にハンドリングしたい例外の型を指定し、
     * 指定した例外型と互換性のある（派生クラスの関係にある）クラスはまとめてハンドリングができる
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );

        return ResponseEntity
                .of(problemDetail)
                .build();
    }

    /*
     * 親子関係にあるクラスがそれぞれ別のハンドリングメソッドに設定されている場合、
     * より具体的な（子側の）クラスによるハンドリングが優先される
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleException(IllegalArgumentException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );

        return ResponseEntity
                .of(problemDetail)
                .build();
    }
}
