package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnectionIntTest {

    private Connection connection;
    private CountryReports countryReports;

    // Set up the test environment, before each test, establish a connection to MySQL
    @BeforeEach
    public void setUp() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/world?useSSL=true"; // Update with your test DB
        String username = "root"; // Your test username
        String password = "example"; // Your test password

        // Establish connection to the database
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        countryReports = new CountryReports();
    }

    // Test: Check that the connection is established and not closed
    @Test
    public void testConnectionEstablished() {
        assertNotNull(connection, "The connection should not be null.");
        try {
            setUp();
            assertFalse(connection.isClosed(), "The connection should not be closed.");
        } catch (SQLException e) {
            fail("SQLException occurred while checking connection status: " + e.getMessage());
        }
    }

    // Test: Check that the connection is valid
    @Test
    public void testConnectionIsValid() {
        try {
            setUp();
            assertTrue(connection.isValid(2), "The connection should be valid.");
        } catch (SQLException e) {
            fail("SQLException occurred while validating the connection: " + e.getMessage());
        }
    }


    // Test: Closing the connection after usage
    @Test
    public void testCloseConnection() {
        try {
            setUp();
            // Check if the connection is open before closing
            assertNotNull(connection, "Connection should not be null before closing.");
            assertFalse(connection.isClosed(), "Connection should not be closed before test.");

            // Close the connection and verify
            connection.close();
            assertTrue(connection.isClosed(), "The connection should be closed after close.");
        } catch (SQLException e) {
            fail("SQLException occurred while closing the connection: " + e.getMessage());
        }
    }

    // After each test, close the connection if it's still open
    @AfterEach
    public void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
