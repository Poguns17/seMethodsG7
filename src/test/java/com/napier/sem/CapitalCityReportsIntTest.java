package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityReportsIntTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "example";

    @Test
    void integrationTestReportAllCapitalCitiesByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC";

            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                assertTrue(rs.next(), "No data returned for the query");

                String firstCapital = rs.getString("Name");
                assertNotNull(firstCapital, "Capital city name should not be null");
            }
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void integrationTestTopNCapitalCitiesInWorld() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC LIMIT 10";

            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    String capitalName = rs.getString("Name");
                    assertNotNull(capitalName, "Capital name should not be null");
                }
                assertEquals(10, count, "Top N query did not return the correct number of results");
            }
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }
}
