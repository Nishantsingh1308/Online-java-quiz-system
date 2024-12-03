-- Create Database
CREATE DATABASE IF NOT EXISTS online_quiz_system;
USE online_quiz_system;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'admin') NOT NULL
);

-- Create Admins Table
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create Students Table
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    student_number VARCHAR(50) NOT NULL UNIQUE
);

-- Create Quiz Questions Table
CREATE TABLE IF NOT EXISTS quiz_questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL,
    option_1 VARCHAR(255) NOT NULL,
    option_2 VARCHAR(255) NOT NULL,
    option_3 VARCHAR(255) NOT NULL,
    option_4 VARCHAR(255) NOT NULL,
    correct_answer VARCHAR(255) NOT NULL
);

-- Create Quiz Results Table
CREATE TABLE IF NOT EXISTS quiz_results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    correct_answers INT NOT NULL,
    total_questions INT NOT NULL,
    time_taken BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Create Exam Sessions Table
CREATE TABLE IF NOT EXISTS exam_sessions (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    start_time BIGINT NOT NULL,
    end_time BIGINT,
    time_spent BIGINT,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Create Timers Table
CREATE TABLE IF NOT EXISTS timers (
    timer_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    start_time BIGINT NOT NULL,
    end_time BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Insert Sample Data

-- Insert sample users (admins, students)
INSERT INTO users (username, password, role) VALUES
('admin1', 'password1', 'admin'),
('student1', 'password1', 'student'),
('student2', 'password2', 'student');

-- Insert sample quiz questions
INSERT INTO quiz_questions (question, option_1, option_2, option_3, option_4, correct_answer) VALUES
('What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'Paris'),
('Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Mars');

-- Insert sample students
INSERT INTO students (username, password, student_number) VALUES
('student1', 'password1', 'S12345'),
('student2', 'password2', 'S67890');

-- Insert sample quiz results
INSERT INTO quiz_results (student_id, correct_answers, total_questions, time_taken) VALUES
(1, 8, 10, 60000),
(2, 9, 10, 54000);

-- Insert sample exam sessions
INSERT INTO exam_sessions (student_id, start_time, end_time, time_spent) VALUES
(1, 1672483200, 1672486800, 3600),
(2, 1672486800, 1672490400, 3600);

-- Insert sample timers
INSERT INTO timers (student_id, start_time, end_time) VALUES
(1, 1672483200, 1672486800),
(2, 1672486800, 1672490400);

-- Indexes for better performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_quiz_questions_question ON quiz_questions(question);
CREATE INDEX idx_quiz_results_student_id ON quiz_results(student_id);

