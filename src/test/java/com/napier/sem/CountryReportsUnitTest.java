package com.napier.sem;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

class CountryReportsUnitTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "example";


    @Test
    void testReportAllCountriesByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportAllCountriesByPopulation(connection);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCountriesInContinentByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportCountriesInContinentByPopulation(connection, "Asia");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCountriesInRegionByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportCountriesInRegionByPopulation(connection, "Eastern Asia");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCountriesInWorld() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportTopNCountriesInWorld(connection, 5);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCountriesInContinent() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportTopNCountriesInContinent(connection, 5, "Asia");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCountriesInRegion() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportTopNCountriesInRegion(connection, 5, "Eastern Asia");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCountryDetails() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CountryReports reports = new CountryReports();
            reports.reportCountryDetails(connection);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }
}

