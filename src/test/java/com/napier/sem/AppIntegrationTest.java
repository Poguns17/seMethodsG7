package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    private static Connection con;

    @BeforeAll
    static void setUp() throws SQLException {
        // Set up real MySQL database connection
        String url = "jdbc:mysql://localhost:3306/world?useSSL=true";
        String user = "root";
        String password = "example";

        // Create the connection
        con = DriverManager.getConnection(url, user, password);
        assertNotNull(con, "Database connection should not be null");
    }


    @BeforeEach
    void init() throws SQLException {
        // Reinitialize the connection if it's closed
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        }

        Statement stmt = con.createStatement();
        // Reset tables
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
        stmt.executeUpdate("DELETE FROM city");
        stmt.executeUpdate("DELETE FROM country");
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    }


    @Test
    void testDatabaseConnection() throws SQLException {
        // Ensure the connection is valid
        assertTrue(con.isValid(0), "Connection to the database should be valid");
    }

    @Test
    void testDatabaseConnectionClose() throws SQLException {
        // Test that the connection is closed correctly
        con.close();
        assertTrue(con.isClosed(), "The connection should be closed.");
    }
}
