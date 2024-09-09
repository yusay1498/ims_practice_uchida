-- 既存のテーブルを削除（必要に応じて）
DROP TABLE IF EXISTS attendance;

-- テーブルの作成
CREATE TABLE attendance
(
    id          VARCHAR(40) PRIMARY KEY,
    employee_id VARCHAR(40) NOT NULL,
    begin_work  TIMESTAMP   NOT NULL,
    finish_work TIMESTAMP,
    -- ユニークキーに設定し同じ社員が同じ勤怠データを持つことを防いでいる
    CONSTRAINT unique_employee_date UNIQUE (employee_id, begin_work, finish_work)
);

-- サンプルデータの挿入
INSERT INTO attendance (id, employee_id, begin_work, finish_work)
VALUES ('a5e5e9d4-40d7-4f7d-8b95-5a5d6d881ba4', 'emp001', '2024-08-25 09:00:00', '2024-08-25 17:00:00'),
       ('0d3d2e8e-fc7c-4f0b-8c95-7a6e1b0b2d3d', 'emp002', '2024-08-25 08:30:00', '2024-08-25 16:30:00'),
       ('7d8e8b2d-4c68-44d3-b8f8-7e1f8b8a5d7d', 'emp003', '2024-08-25 09:15:00', '2024-08-25 17:15:00');

-- データ確認用のクエリ
SELECT *
FROM attendance;
