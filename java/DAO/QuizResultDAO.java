package java.DAO;

import java.sql.*;

public class QuizResultDAO {
    private Connection connection;

    public QuizResultDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveQuizResult(QuizResult result) throws SQLException {
        String query = "INSERT INTO quiz_results (student_id, correct_answers, total_questions, time_taken) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, result.getStudent().getStudentId());
            stmt.setInt(2, result.getCorrectAnswers());
            stmt.setInt(3, result.getTotalQuestions());
            stmt.setLong(4, result.getTimeTaken());
            stmt.executeUpdate();
        }
    }

    public QuizResult getResultByStudentId(String studentId) throws SQLException {
        String query = "SELECT * FROM quiz_results WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new QuizResult(
                        new Student(studentId, "", ""),
                        null,  // You would need to fetch questions separately
                        rs.getInt("correct_answers"),
                        rs.getInt("total_questions"),
                        rs.getLong("time_taken")
                );
            }
        }
        return null;
    }
}
