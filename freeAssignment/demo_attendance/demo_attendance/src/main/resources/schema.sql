-- 既存のテーブルを削除（必要に応じて）
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS approval_requests;

-- 社員テーブルの作成
CREATE TABLE employees
(
    id          VARCHAR(40) PRIMARY KEY,
    employee_id VARCHAR(40) NOT NULL UNIQUE,
    name        VARCHAR(100) NOT NULL,
    password    TEXT NOT NULL  -- ハッシュ化されたパスワードを保存
);

-- 日毎のテーブルの作成
CREATE TABLE attendance
(
    id          VARCHAR(40) PRIMARY KEY,
    employee_id VARCHAR(40) NOT NULL,
    begin_work  TIMESTAMP   NOT NULL,
    finish_work TIMESTAMP,
    CONSTRAINT unique_employee_date UNIQUE (employee_id, begin_work, finish_work)
);

-- 申請・承認テーブルの作成
CREATE TABLE approval_requests
(
    id               VARCHAR(40)   PRIMARY KEY,
    employee_id      VARCHAR(40)   NOT NULL,      -- 社員 ID
    year_month       DATE          NOT NULL,      -- 年月 (例: 2024-08-01)
    total_hours      NUMERIC(7, 2) NOT NULL,      -- 集計された就業時間 (分単位)
    status           VARCHAR(20)   NOT NULL,      -- enum型にしてある
    request_date     TIMESTAMP     NOT NULL,      -- 申請日時
    approval_date    TIMESTAMP,                   -- 承認日時（承認されなかった場合はNULL）
    approved_by      VARCHAR(40),                 -- 承認者の社員ID（NULLの場合は未承認）
    CONSTRAINT unique_employee_month UNIQUE (employee_id, year_month)
);

-- サンプルデータの挿入
INSERT INTO employees (id, employee_id, name, password)
VALUES
    ('1', 'emp001', 'Alice', 'hashed_password1'),
    ('2', 'emp002', 'Bob', 'hashed_password2'),
    ('3', 'emp003', 'Charlie', 'hashed_password3');

INSERT INTO attendance (id, employee_id, begin_work, finish_work)
VALUES
    ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4', 'emp001', '2024-08-01 09:00:00', '2024-08-01 17:00:00'),
    ('1d71d6ab-1f29-4a6b-aafe-06f5f7a6a059', 'emp001', '2024-08-02 09:00:00', '2024-08-02 17:00:00'),
    ('d2a0e5f5-9170-4a5e-bc22-bd2d39dc68ab', 'emp001', '2024-08-05 09:00:00', '2024-08-05 17:00:00'),
    ('4f3f7e87-3c3c-4fc2-bde0-5500c0d82ec2', 'emp001', '2024-08-06 09:00:00', '2024-08-06 17:00:00'),
    ('e4d6dbb7-c64a-47c4-8a2e-5b0897e81a72', 'emp001', '2024-08-07 09:00:00', '2024-08-07 17:00:00'),
    ('de1bb885-87ed-4894-89d2-7a5f1a3d758b', 'emp001', '2024-08-08 09:00:00', '2024-08-08 17:00:00'),
    ('c9e6b77c-d01d-4f91-87cb-d62dbca9a535', 'emp001', '2024-08-09 09:00:00', '2024-08-09 17:00:00'),
    ('54e4b36e-5e4d-4642-8e2f-827501d282d0', 'emp001', '2024-08-13 09:00:00', '2024-08-13 17:00:00'),
    ('9c4d5e7b-8546-445d-815b-d8cdb67e9088', 'emp001', '2024-08-14 09:00:00', '2024-08-14 17:00:00'),
    ('8c924b54-0b4d-4d30-b865-40874684a7f4', 'emp001', '2024-08-15 09:00:00', '2024-08-15 17:00:00'),
    ('b852764a-bc8d-47cf-9290-7a4cb1e376d2', 'emp001', '2024-08-16 09:00:00', '2024-08-16 17:00:00'),
    ('d6749a6d-094f-4b56-b3f4-204ac8c47e68', 'emp001', '2024-08-19 09:00:00', '2024-08-19 17:00:00'),
    ('3c07c781-5eb5-4b1e-81ec-378f2d4c0d62', 'emp001', '2024-08-20 09:00:00', '2024-08-20 17:00:00'),
    ('f7b7486b-e9b7-4d9d-b4fc-2a6d40f0699b', 'emp001', '2024-08-21 09:00:00', '2024-08-21 17:00:00'),
    ('fa6b7de3-8933-4c6d-ae56-8139e838c8eb', 'emp001', '2024-08-22 09:00:00', '2024-08-22 17:00:00'),
    ('7e0b4919-09e8-4c37-8733-cc85a8f2e0d3', 'emp001', '2024-08-26 09:00:00', '2024-08-26 17:00:00'),
    ('bff93f48-09ee-41a3-8c3f-4d9f03fcfd6b', 'emp001', '2024-08-27 09:00:00', '2024-08-27 17:00:00'),
    ('cd66eb9a-6c34-4e0f-a65b-2748e29d2f58', 'emp001', '2024-08-28 09:00:00', '2024-08-28 17:00:00'),
    ('0eebc8ae-6d2d-4531-90fc-500f5b069b3c', 'emp001', '2024-08-29 09:00:00', '2024-08-29 17:00:00'),
    ('e0cbb11e-c7d7-4822-a5b4-f1cb0ecb7e80', 'emp001', '2024-08-30 09:00:00', '2024-08-30 17:00:00'),
    ('6d17d7f5-1d23-4d92-8b8d-d947c3e33c8a', 'emp001', '2024-09-02 09:00:00', '2024-09-02 17:00:00'),
    ('ff0b28db-2736-41d5-bb9c-cb7d867a4c42', 'emp002', '2024-08-01 08:30:00', '2024-08-01 16:30:00'),
    ('1c7e92e4-e94a-485d-bb6e-0e4ef535f30c', 'emp002', '2024-08-02 08:30:00', '2024-08-02 16:45:00'),
    ('23c9c0d0-7d85-4c5e-825a-fc629ccf0385', 'emp002', '2024-08-03 08:30:00', '2024-08-03 16:00:00'),
    ('25dbe65d-13ae-4e7d-bc73-691fc0f8dfd2', 'emp003', '2024-08-01 09:15:00', '2024-08-01 17:00:00'),
    ('a0c29d41-6b67-4b76-bd9c-034db1f1a278', 'emp003', '2024-08-02 09:15:00', '2024-08-02 17:15:00'),
    ('2c2290a4-1f28-434c-94ef-b57e3cf0f9e5', 'emp003', '2024-08-03 09:15:00', '2024-08-03 17:30:00');

-- サンプルデータの挿入
INSERT INTO approval_requests (id, employee_id, year_month, total_hours, status, request_date, approval_date, approved_by)
VALUES
    ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3g4h5i6j', 'emp001', '2024-07-01', 9600.0, 'PENDING', '2024-07-31 08:00:00', NULL, NULL),
    ('2b3c4d5e-6f7a-8b9c-0d1e-2f3g4h5i6j7k', 'emp002', '2024-07-01', 9500.0, 'APPROVED', '2024-07-31 08:30:00', '2024-07-31 09:00:00', 'emp100'),
    ('3c4d5e6f-7a8b-9c0d-1e2f-3g4h5i6j7k8l', 'emp003', '2024-08-01', 7200.0, 'REJECTED', '2024-08-31 09:00:00', '2024-08-31 10:00:00', 'emp200');

-- データ確認用のクエリ
SELECT * FROM attendance;