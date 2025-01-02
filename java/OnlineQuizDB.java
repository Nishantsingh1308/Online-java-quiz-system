import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class OnlineQuizDB {
    private static final String DB_PROPERTIES_FILE = "/db.properties";  // Use a properties file for DB config
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = OnlineQuizDB.class.getResourceAsStream(DB_PROPERTIES_FILE)) {
                Properties prop = new Properties();
                prop.load(input);

                String url = prop.getProperty("db.url");
                String username = prop.getProperty("db.username");
                String password = prop.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
