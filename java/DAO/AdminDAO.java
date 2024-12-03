package java.DAO;

import java.sql.*;

public class AdminDAO {
    private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admins (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.executeUpdate();
        }
    }

    public Admin getAdminByUsername(String username) throws SQLException {
        String query = "SELECT * FROM admins WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getString("username"), rs.getString("password"));
            }
        }
        return null;
    }
}
