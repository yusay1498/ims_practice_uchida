# Demo Attendance

## 概要

このプロジェクトは、社員の出勤・退勤の管理を行うシステムのデモです。
Spring Boot を使用しており、h2, PostgresSQL データベースをバックエンドに採用しています。
主な機能としては、出勤・退勤の記録、勤怠データの取得、更新、削除などがあります。

## 構成

- **`domain`**: ドメインモデルとエンティティ
- **`infrastructure`**: データベースリポジトリとリポジトリの実装
- **`application`**: ビジネスロジックとサービス層
- **`presentation`**: REST API エンドポイントとコントローラ

## 依存関係

- Spring Boot
- PostgresSQL
- h2
- Testcontainers（テスト用のコンテナ管理）

## セットアップ

### 1. プロジェクトのクローン

```bash
git clone https://github.com/yourusername/demo-attendance.git
cd demo-attendance
```

## アプリケーションの動作

### 1. アプリケーションの起動

```bash
./mvnw spring-boot:run
```

### 2. CURLコマンド例
#### 1.出退勤管理
GET (findAll)
```bash
curl localhost:8080/attendances
```

GET  (findById)
```bash
curl localhost:8080/attendances/a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4
```

GET (findByEmployeeIdAndDate)
```bash
curl -X GET "http://localhost:8080/attendances/searchAttendance?employeeId=emp001&date=2024-08-25"
```

GET (findMonthlyAttendanceSummaryByEmployeeId)
```bash
curl -X GET "http://localhost:8080/attendances/monthlySummary?employeeId=emp001"
```

POST (FUll)
```bash
curl -X POST "http://localhost:8080/attendances" \
         -H "Content-Type: application/json" \
         -d '{
               "employeeId": "emp004",
               "beginWork": "2024-08-26T09:00:00",
               "finishWork": "2024-08-26T17:00:00"
             }'
```

POST (出勤)
```bash
curl -X POST "http://localhost:8080/attendances" \
     -H "Content-Type: application/json" \
     -d '{
           "employeeId": "emp004",
           "beginWork": "2024-08-27T09:00:00"
         }'
```

PUT (退勤)
```bash
curl -X PUT "http://localhost:8080/attendances/a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4" \
     -H "Content-Type: application/json" \
     -d '{
           "id": "a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4",
           "finishWork": "2024-08-26T18:00:00"
         }'
```

PUT (更新)
```bash
curl -X PUT "http://localhost:8080/attendances/a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4" \
     -H "Content-Type: application/json" \
     -d '{
           "id": "a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4",
           "beginWork": "2024-08-26T10:00:00",
           "finishWork": "2024-08-26T18:00:00"
         }'
```

DELETE
```bash
curl -X DELETE "http://localhost:8080/attendances/a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4"
```

#### 2.承認申請
GET (findAll)
```bash
curl localhost:8080/approvalRequests
```

GET (findById)
```bash
curl localhost:8080/approvalRequests/1a2b3c4d-5e6f-7a8b-9c0d-1e2f3g4h5i6j
```

GET (findByEmployeeIdAndYearMonth)
```bash
curl -X GET "http://localhost:8080/approvalRequests/?employeeId=emp001&yearMonth=2024-07"
```

POST
```bash
curl -X POST http://localhost:8080/approvalRequests \
     -H "Content-Type: application/json" \
     -d '{
           "employeeId": "emp001",
           "yearMonth": "2024-08"
         }'
```

PUT (承認)
```bash
curl -X PUT http://localhost:8080/approvalRequests/1a2b3c4d-5e6f-7a8b-9c0d-1e2f3g4h5i6j \
     -H "Content-Type: application/json" \
     -d '{
           "id": "1a2b3c4d-5e6f-7a8b-9c0d-1e2f3g4h5i6j",
           "status": "APPROVED",
           "approvedBy": "emp100"
         }'
```

PUT (再申請)
```bash
curl -X PUT http://localhost:8080/approvalRequests/3c4d5e6f-7a8b-9c0d-1e2f-3g4h5i6j7k8l \
     -H "Content-Type: application/json" \
     -d '{
           "id": "3c4d5e6f-7a8b-9c0d-1e2f-3g4h5i6j7k8l",
           "yearMonth": "2024-08"
        }'
```

DELETE
```bash
curl -X DELETE "http://localhost:8080/approvalRequests/1a2b3c4d-5e6f-7a8b-9c0d-1e2f3g4h5i6j"
```


## Test

```bash
./mvnw test
```