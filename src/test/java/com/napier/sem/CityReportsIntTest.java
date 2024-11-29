package com.napier.sem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CityReportsIntTest {

    private CityReports cityReports = new CityReports();
    private Connection connection;

    // Establish the database connection in a setup method
    public CityReportsIntTest() {
        try {
            // Update the connection string and credentials as per your environment
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
    }

    // Test for Report 1: All cities in the world organized by largest to smallest population
    @Test
    void testReportAllCitiesByPopulation() {
        try {
            cityReports.reportAllCitiesByPopulation(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 2: All cities in a continent organized by largest to smallest population
    @Test
    void testReportCitiesByContinent() {
        try {
            cityReports.reportCitiesByContinent(connection, "Europe");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 3: All cities in a region organized by largest to smallest population
    @Test
    void testReportCitiesByRegion() {
        try {
            cityReports.reportCitiesByRegion(connection, "Western Europe");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 4: All cities in a country organized by largest to smallest population
    @Test
    void testReportCitiesByCountry() {
        try {
            cityReports.reportCitiesByCountry(connection, "GB");  // Example country code for the United Kingdom
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 5: All cities in a district organized by largest to smallest population
    @Test
    void testReportCitiesByDistrict() {
        try {
            cityReports.reportCitiesByDistrict(connection, "England");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 6: The top N populated cities in the world where N is provided by the user
    @Test
    void testReportTopNCitiesByPopulation() {
        try {
            cityReports.reportTopNCitiesByPopulation(connection, 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 7: The top N populated cities in a continent where N is provided by the user
    @Test
    void testReportTopNCitiesByContinent() {
        try {
            cityReports.reportTopNCitiesByContinent(connection, "Europe", 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 8: The top N populated cities in a region where N is provided by the user
    @Test
    void testReportTopNCitiesByRegion() {
        try {
            cityReports.reportTopNCitiesByRegion(connection, "Western Europe", 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 9: The top N populated cities in a country where N is provided by the user
    @Test
    void testReportTopNCitiesByCountry() {
        try {
            cityReports.reportTopNCitiesByCountry(connection, "GB", 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 10: The top N populated cities in a district where N is provided by the user
    @Test
    void testReportTopNCitiesByDistrict() {
        try {
            cityReports.reportTopNCitiesByDistrict(connection, "England", 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    // Test for Report 11: City Report (Name, Country, District, Population) by city name
    @Test
    void testReportCity() {
        try {
            cityReports.reportCity(connection, "London");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }
}
