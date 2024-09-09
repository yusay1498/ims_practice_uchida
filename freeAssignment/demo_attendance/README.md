# Demo Attendance

## 概要

このプロジェクトは、社員の出勤・退勤の管理を行うシステムのデモです。
Spring Boot を使用しており、PostgreSQL データベースをバックエンドに採用しています。
主な機能としては、出勤・退勤の記録、勤怠データの取得、更新、削除などがあります。

## 構成

- **`domain`**: ドメインモデルとエンティティ
- **`infrastructure`**: データベースリポジトリとリポジトリの実装
- **`application`**: ビジネスロジックとサービス層
- **`presentation`**: REST API エンドポイントとコントローラ

## 依存関係

- Spring Boot
- PostgreSQL
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

## Test

```bash
./mvnw test
```