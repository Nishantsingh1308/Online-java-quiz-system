import java.sql.*;

// Single class that combines DB connection and DAO operations
public class OnlineQuizSystem {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_db";
    private static final String USER = "yourUsername";
    private static final String PASSWORD = "yourPassword";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // DAO Methods for User operations
    public static class UserDAO {

        // Method to add a new user
        public boolean addUser(String username, String password, String email) {
            String query = "INSERT INTO User (username, password, email) VALUES (?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Method to get a user by username
        public void getUser(String username) {
            String query = "SELECT * FROM User WHERE username = ?";
            try (Connection connection = getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    System.out.println("User ID: " + rs.getInt("user_id"));
                    System.out.println("Username: " + rs.getString("username"));
                    System.out.println("Email: " + rs.getString("email"));
                } else {
                    System.out.println("User not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Method to update a user's email by username
        public boolean updateUser(String username, String newEmail) {
            String query = "UPDATE User SET email = ? WHERE username = ?";
            try (Connection connection = getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, newEmail);
                stmt.setString(2, username);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        // Method to delete a user by username
        public boolean deleteUser(String username) {
            String query = "DELETE FROM User WHERE username = ?";
            try (Connection connection = getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Adding a new user
        if (userDAO.addUser("john_doe", "password123", "john@example.com")) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }

        // Retrieving a user
        System.out.println("\nRetrieving user:");
        userDAO.getUser("john_doe");

        // Updating a user's email
        if (userDAO.updateUser("john_doe", "john.doe@newdomain.com")) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }

        // Deleting a user
        if (userDAO.deleteUser("john_doe")) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
