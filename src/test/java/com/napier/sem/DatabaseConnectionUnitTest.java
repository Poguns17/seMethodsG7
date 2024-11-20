package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectionUnitTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "example";

    // Test for successful database connection
    @Test
    void testDatabaseConnectionSuccess() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection successful.");
            } else {
                fail("Database connection was unsuccessful: Connection is null or closed.");
            }
        } catch (SQLException e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }

    // Test for attempting to connect to the database with invalid credentials
    @Test
    void testDatabaseConnectionInvalidCredentials() {
        try (Connection connection = DriverManager.getConnection(DB_URL, "wrongUser", "wrongPassword")) {
            fail("Connection should not have been established with invalid credentials.");
        } catch (SQLException e) {
            // This is expected, so test passes
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    // Test for successful retrieval of data (e.g., running the report)
    @Test
    void testReportAllCountriesByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportAllCountriesByPopulation(connection);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    // Test for attempting to connect to a non-existent database
    @Test
    void testDatabaseConnectionNonExistentDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonexistentDB", USER, PASSWORD)) {
            fail("Connection should not have been established to a non-existent database.");
        } catch (SQLException e) {
            // This is expected, so test passes
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    // Test for checking the validity of the connection
    @Test
    void testConnectionIsValid() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (connection != null && connection.isValid(2)) {
                System.out.println("Connection is valid.");
            } else {
                fail("Connection is not valid.");
            }
        } catch (SQLException e) {
            fail("Failed to validate connection: " + e.getMessage());
        }
    }

    // Test for checking if the connection is closed properly
    @Test
    void testCloseConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Connection closed successfully.");
            } else {
                fail("Connection did not close properly.");
            }
        } catch (SQLException e) {
            fail("An error occurred while closing the connection: " + e.getMessage());
        }
    }
}
