import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database URL, username, and password for connection
    private static final String URL = "jdbc:mysql://localhost:3306/World";
    private static final String USER = "40692347";
    private static final String PASSWORD = "World123";

    // Method to establish and return a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


