package com.napier.sem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class CountryReportsIntTest {

    private Connection connection;
    private CountryReports countryReports;

    // Set up the test environment, before each test, establish a connection to MySQL
    @BeforeEach
    public void setUp() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/world?useSSL=true"; // Update with your test DB
        String username = "root"; // Your test username
        String password = "example"; // Your test password

        connection = DriverManager.getConnection(jdbcUrl, username, password);
        countryReports = new CountryReports();
    }

    // Example Test for Report 1: All countries in the world by population
    @Test
    public void testReportAllCountriesByPopulation() {
        try {
            setUp();
            // Capture standard output
            countryReports.reportAllCountriesByPopulation(connection);
            // Validate that the method executed without exceptions
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 2: Countries in a continent by population
    @Test
    public void testReportCountriesInContinentByPopulation() {
        String continent = "Asia"; // Example continent
        try {
            setUp();
            countryReports.reportCountriesInContinentByPopulation(connection, continent);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 3: Countries in a region by population
    @Test
    public void testReportCountriesInRegionByPopulation() {
        String region = "Europe"; // Example region
        try {
            setUp();
            countryReports.reportCountriesInRegionByPopulation(connection, region);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 4: Top N countries in the world
    @Test
    public void testReportTopNCountriesInWorld() {
        int n = 10; // Example: top 10 countries
        try {
            setUp();
            countryReports.reportTopNCountriesInWorld(connection, n);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 5: Top N countries in a continent
    @Test
    public void testReportTopNCountriesInContinent() {
        int n = 5; // Example: top 5 countries
        String continent = "Asia"; // Example continent
        try {
            setUp();
            countryReports.reportTopNCountriesInContinent(connection, n, continent);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 6: Top N countries in a region
    @Test
    public void testReportTopNCountriesInRegion() {
        int n = 5; // Example: top 5 countries
        String region = "Europe"; // Example region
        try {
            setUp();
            countryReports.reportTopNCountriesInRegion(connection, n, region);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Example Test for Report 7: Country report with detailed fields
    @Test
    public void testReportCountryDetails() {
        try {
            setUp();
            countryReports.reportCountryDetails(connection);
        } catch (Exception e) {
            fail("Method threw exception: " + e.getMessage());
        }
    }

    // Clean up the connection after each test
    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
