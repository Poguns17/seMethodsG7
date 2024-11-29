package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionIntTest {

    private DatabaseConnection databaseConnection;  // Use the DatabaseConnection class to manage connection
    private Connection connection;
    private CountryReports countryReports;

    // Set up the test environment, before each test, establish a connection to MySQL
    @BeforeEach
    public void setUp() {
        // Initialize the DatabaseConnection class (which will handle retries and connection management)
        databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();  // Use the connection from the DatabaseConnection class
        countryReports = new CountryReports();
    }

    // Test: Check that the connection is established and not closed
    @Test
    public void testConnectionEstablished() {
        try {
            setUp();  // Ensure connection is established before running the test
            assertNotNull(connection, "The connection should not be null.");
            assertFalse(connection.isClosed(), "The connection should not be closed.");
        } catch (SQLException e) {
            fail("SQLException occurred while checking connection status: " + e.getMessage());
        }
    }

    // Test: Check that the connection is valid
    @Test
    public void testConnectionIsValid() {
        try {
            setUp();  // Ensure connection is established before running the test
            assertTrue(connection.isValid(2), "The connection should be valid.");
        } catch (SQLException e) {
            fail("SQLException occurred while validating the connection: " + e.getMessage());
        }
    }



    // After each test, close the connection if it's still open
    @AfterEach
    public void tearDown() {
        if (connection != null) {
            databaseConnection.closeConnection();
        }
    }
}
