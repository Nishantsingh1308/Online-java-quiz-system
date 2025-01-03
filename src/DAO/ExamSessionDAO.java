package java.DAO;

import java.sql.*;

public class ExamSessionDAO {
    private Connection connection;

    public ExamSessionDAO(Connection connection) {
        this.connection = connection;
    }

    public void startSession(ExamSession session) throws SQLException {
        String query = "INSERT INTO exam_sessions (student_id, start_time) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, session.getStudent().getStudentId());
            stmt.setLong(2, session.getStartTime());
            stmt.executeUpdate();
        }
    }

    public void endSession(ExamSession session) throws SQLException {
        String query = "UPDATE exam_sessions SET end_time = ?, time_spent = ? WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, session.getEndTime());
            stmt.setLong(2, session.getTimeSpent());
            stmt.setString(3, session.getStudent().getStudentId());
            stmt.executeUpdate();
        }
    }
}