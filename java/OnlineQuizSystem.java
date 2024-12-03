package java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineQuizSystem {

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/online_quiz_system";
    private static final String DB_USERNAME = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "password"; // Replace with your DB password

    // DBUtility Method to Get Connection
    public static Connection getConnection() throws SQLException {
        try {
            // Register the JDBC driver (MySQL)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.", e);
        }
    }

    // Method to Close Connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // --- UserDAO Methods ---
    public static boolean verifyUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was successfully added
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- QuizQuestionDAO Methods ---
    public static List<QuizQuestion> getAllQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();
        String query = "SELECT * FROM quiz_questions";
        
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                int questionId = resultSet.getInt("question_id");
                String question = resultSet.getString("question");
                String option1 = resultSet.getString("option_1");
                String option2 = resultSet.getString("option_2");
                String option3 = resultSet.getString("option_3");
                String option4 = resultSet.getString("option_4");
                String correctAnswer = resultSet.getString("correct_answer");

                questions.add(new QuizQuestion(questionId, question, option1, option2, option3, option4, correctAnswer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static boolean addQuestion(QuizQuestion quizQuestion) {
        String query = "INSERT INTO quiz_questions (question, option_1, option_2, option_3, option_4, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, quizQuestion.getQuestion());
            preparedStatement.setString(2, quizQuestion.getOption1());
            preparedStatement.setString(3, quizQuestion.getOption2());
            preparedStatement.setString(4, quizQuestion.getOption3());
            preparedStatement.setString(5, quizQuestion.getOption4());
            preparedStatement.setString(6, quizQuestion.getCorrectAnswer());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- QuizResultDAO Methods ---
    public static boolean saveQuizResult(int studentId, int correctAnswers, int totalQuestions, long timeTaken) {
        String query = "INSERT INTO quiz_results (student_id, correct_answers, total_questions, time_taken) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, correctAnswers);
            preparedStatement.setInt(3, totalQuestions);
            preparedStatement.setLong(4, timeTaken);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- ExamSessionDAO Methods ---
    public static boolean startExamSession(int studentId, long startTime) {
        String query = "INSERT INTO exam_sessions (student_id, start_time) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, studentId);
            preparedStatement.setLong(2, startTime);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean endExamSession(int sessionId, long endTime) {
        String query = "UPDATE exam_sessions SET end_time = ?, time_spent = ? WHERE session_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setLong(1, endTime);
            preparedStatement.setLong(2, endTime - getSessionStartTime(sessionId));
            preparedStatement.setInt(3, sessionId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static long getSessionStartTime(int sessionId) {
        String query = "SELECT start_time FROM exam_sessions WHERE session_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("start_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // --- TimerDAO Methods ---
    public static boolean startTimer(int studentId, long startTime) {
        String query = "INSERT INTO timers (student_id, start_time) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, studentId);
            preparedStatement.setLong(2, startTime);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopTimer(int timerId, long endTime) {
        String query = "UPDATE timers SET end_time = ? WHERE timer_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setLong(1, endTime);
            preparedStatement.setInt(2, timerId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Main Method for Testing
    public static void main(String[] args) {
        // Test the methods here (for example, login verification, adding a user, etc.)
        boolean userVerified = verifyUser("testuser", "password123");
        System.out.println("User Verified: " + userVerified);
    }
}
