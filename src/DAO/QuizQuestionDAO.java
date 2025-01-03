package java.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionDAO {
    private Connection connection;

    public QuizQuestionDAO(Connection connection) {
        this.connection = connection;
    }

    public void createQuestion(QuizQuestion question) throws SQLException {
        String query = "INSERT INTO quiz_questions (question, option_1, option_2, option_3, option_4, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, question.getQuestion());
            stmt.setString(2, question.getOptions()[0]);
            stmt.setString(3, question.getOptions()[1]);
            stmt.setString(4, question.getOptions()[2]);
            stmt.setString(5, question.getOptions()[3]);
            stmt.setString(6, question.getCorrectAnswer());
            stmt.executeUpdate();
        }
    }

    public List<QuizQuestion> getAllQuestions() throws SQLException {
        List<QuizQuestion> questions = new ArrayList<>();
        String query = "SELECT * FROM quiz_questions";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] options = {rs.getString("option_1"), rs.getString("option_2"), rs.getString("option_3"), rs.getString("option_4")};
                questions.add(new QuizQuestion(rs.getInt("id"), rs.getString("question"), options, rs.getString("correct_answer")));
            }
        }
        return questions;
    }

    public void deleteQuestion(int id) throws SQLException {
        String query = "DELETE FROM quiz_questions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
