package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.*;

public class PopulationReportsIntTest {

    private PopulationReports populationReports;
    private Connection connection;

    @BeforeEach
    void setUp() {
        populationReports = new PopulationReports();
        try {
            // Replace with your database credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "example");
        } catch (SQLException e) {
            Assertions.fail("Failed to connect to the database: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the connection: " + e.getMessage());
        }
    }

    @Test
    void testIntegrationReportPopulationByContinent() {
        try {
            System.out.println("Integration Test: Population By Continent");
            populationReports.reportPopulationByContinent(connection);
        } catch (Exception e) {
            Assertions.fail("Integration test failed: " + e.getMessage());
        }
    }

    @Test
    void testIntegrationReportPopulationByRegion() {
        try {
            System.out.println("Integration Test: Population By Region");
            populationReports.reportPopulationByRegion(connection);
        } catch (Exception e) {
            Assertions.fail("Integration test failed: " + e.getMessage());
        }
    }

    @Test
    void testIntegrationReportPopulationByCountry() {
        try {
            System.out.println("Integration Test: Population By Country");
            populationReports.reportPopulationByCountry(connection);
        } catch (Exception e) {
            Assertions.fail("Integration test failed: " + e.getMessage());
        }
    }

    @Test
    void testIntegrationReportPopulationOfEntity() {
        try {
            System.out.println("Integration Test: Population Of Entity - World");
            populationReports.reportPopulationOfEntity(connection, "world", null);
        } catch (Exception e) {
            Assertions.fail("Integration test failed: " + e.getMessage());
        }
    }

    @Test
    void testIntegrationReportPopulationWithPercentages() {
        try {
            System.out.println("Integration Test: Population With Percentages");
            populationReports.reportPopulationWithPercentages(connection);
        } catch (Exception e) {
            Assertions.fail("Integration test failed: " + e.getMessage());
        }
    }
}
