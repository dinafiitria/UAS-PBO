CREATE DATABASE IF NOT EXISTS student_master;
USE student_master;

CREATE TABLE IF NOT EXISTS students (
  student_id VARCHAR(20) PRIMARY KEY,
  student_name VARCHAR(100) NOT NULL,
  major VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL,
  phone VARCHAR(20),
  address VARCHAR(255)
);

INSERT INTO students (student_id, student_name, major, email, phone, address) VALUES
('2212510038','Alya Putri','Information Systems','alya@example.com','081234560038','Jakarta'),
('2212510039','Bima Pratama','Computer Science','bima@example.com','081234560039','Depok'),
('2212510040','Dina Fitria','Information Systems','dina@example.com','081234560040','Jakarta')
ON DUPLICATE KEY UPDATE student_name=VALUES(student_name);
