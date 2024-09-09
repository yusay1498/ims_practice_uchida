-- テーブルの作成
CREATE TABLE IF NOT EXISTS attendance
(
    id          VARCHAR(40) PRIMARY KEY,
    employee_id VARCHAR(40) NOT NULL,
    begin_work  TIMESTAMP   NOT NULL,
    finish_work TIMESTAMP,
    -- ユニークキーに設定し同じ社員が同じ勤怠データを持つことを防いでいる
    CONSTRAINT unique_employee_date UNIQUE (employee_id, begin_work, finish_work)
);