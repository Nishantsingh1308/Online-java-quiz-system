import java.sql.*;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void createStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (username, password, student_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getUsername());
            stmt.setString(2, student.getPassword());
            stmt.setString(3, student.getStudentId());
            stmt.executeUpdate();
        }
    }

    public Student getStudentByStudentId(String studentId) throws SQLException {
        String query = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(rs.getString("username"), rs.getString("password"), rs.getString("student_id"));
            }
        }
        return null;
    }
}