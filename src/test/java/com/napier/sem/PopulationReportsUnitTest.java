package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.*;

public class PopulationReportsUnitTest {

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
    void testReportPopulationByContinent() {
        try {
            populationReports.reportPopulationByContinent(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPopulationByRegion() {
        try {
            populationReports.reportPopulationByRegion(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPopulationByCountry() {
        try {
            populationReports.reportPopulationByCountry(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPopulationOfEntity() {
        try {
            // Testing the world population
            populationReports.reportPopulationOfEntity(connection, "world", null);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPopulationWithPercentages() {
        try {
            populationReports.reportPopulationWithPercentages(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }
}
