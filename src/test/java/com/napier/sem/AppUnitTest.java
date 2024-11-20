package com.napier.sem;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world?useSSL=true";
    private static final String USER = "root";
    private static final String PASSWORD = "example";

    @Test
    void testDatabaseConnection() {
        // Attempt to connect to the database
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            assertNotNull(connection, "Connection should not be null");
            assertTrue(connection.isValid(2), "Connection should be valid");
        } catch (SQLException e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }

    @Test
    void testReportGeneration() {
        // Attempt to generate a report using the database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reportGenerator = new CountryReports();
            reportGenerator.reportAllCountriesByPopulation(connection);
            // If no exception occurs and the report is generated, the test will pass
        } catch (SQLException e) {
            fail("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            fail("Report generation failed: " + e.getMessage());
        }
    }

    @Test
    void testAppMainMethod() {
        // This test ensures the main app runs without errors
        try {
            App app = new App();
            app.main(new String[]{});
        } catch (Exception e) {
            fail("App main method failed: " + e.getMessage());
        }
    }
}

