package com.napier.sem;

import java.sql.*;

public class PopulationReports {

    /// Report 1: Population of people, people living in cities, and not living in cities in each continent
    public void reportPopulationByContinent(Connection connection) {
        String query = "SELECT co.Continent, " +
                "SUM(co.Population) AS TotalPopulation, " +
                "SUM(ci.Population) AS CityPopulation, " +
                "SUM(co.Population - ci.Population) AS NonCityPopulation " +
                "FROM country co " +
                "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                "GROUP BY co.Continent;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Continent", "Total Population", "City Population", "Non-City Population");
            System.out.println("----------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String continent = rs.getString("Continent");
                long totalPopulation = rs.getLong("TotalPopulation");
                long cityPopulation = rs.getLong("CityPopulation");
                long nonCityPopulation = rs.getLong("NonCityPopulation");

                System.out.printf("%-20s %-20d %-20d %-20d%n", continent, totalPopulation, cityPopulation, nonCityPopulation);
            }
            System.out.println("----------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating population report by continent: " + e.getMessage());
        }
    }

    /// Report 2: Population of people, people living in cities, and not living in cities in each region
    public void reportPopulationByRegion(Connection connection) {
        String query = "SELECT co.Region, " +
                "SUM(co.Population) AS TotalPopulation, " +
                "SUM(ci.Population) AS CityPopulation, " +
                "SUM(co.Population - ci.Population) AS NonCityPopulation " +
                "FROM country co " +
                "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                "GROUP BY co.Region;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Region", "Total Population", "City Population", "Non-City Population");
            System.out.println("-------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String region = rs.getString("Region");
                long totalPopulation = rs.getLong("TotalPopulation");
                long cityPopulation = rs.getLong("CityPopulation");
                long nonCityPopulation = rs.getLong("NonCityPopulation");

                System.out.printf("%-20s %-20d %-20d %-20d%n", region, totalPopulation, cityPopulation, nonCityPopulation);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating population report by region: " + e.getMessage());
        }
    }

    /// Report 3: Population of people, people living in cities, and not living in cities in each country
    public void reportPopulationByCountry(Connection connection) {
        String query = "SELECT co.Name AS CountryName, " +
                "co.Population AS TotalPopulation, " +
                "SUM(ci.Population) AS CityPopulation, " +
                "SUM(co.Population - ci.Population) AS NonCityPopulation " +
                "FROM country co " +
                "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                "GROUP BY co.Code, co.Name;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Country", "Total Population", "City Population", "Non-City Population");
            System.out.println("-------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String countryName = rs.getString("CountryName");
                long totalPopulation = rs.getLong("TotalPopulation");
                long cityPopulation = rs.getLong("CityPopulation");
                long nonCityPopulation = rs.getLong("NonCityPopulation");

                System.out.printf("%-20s %-20d %-20d %-20d%n", countryName, totalPopulation, cityPopulation, nonCityPopulation);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating population report by country: " + e.getMessage());
        }
    }

    /// Report 4: Population of specific entities
    public void reportPopulationOfEntity(Connection connection, String entityType, String entityName) {
        String query = "";
        switch (entityType.toLowerCase()) {
            case "world":
                query = "SELECT SUM(Population) AS Population FROM country;";
                break;
            case "continent":
                query = "SELECT SUM(Population) AS Population FROM country WHERE Continent = ?;";
                break;
            case "region":
                query = "SELECT SUM(Population) AS Population FROM country WHERE Region = ?;";
                break;
            case "country":
                query = "SELECT Population FROM country WHERE Name = ?;";
                break;
            case "district":
                query = "SELECT SUM(Population) AS Population FROM city WHERE District = ?;";
                break;
            case "city":
                query = "SELECT Population FROM city WHERE Name = ?;";
                break;
            default:
                System.err.println("Invalid entity type: " + entityType);
                return;
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            if (!entityType.equalsIgnoreCase("world")) {
                stmt.setString(1, entityName);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long population = rs.getLong("Population");
                    System.out.println("Population of " + entityName + " (" + entityType + "): " + population);
                } else {
                    System.out.println("No data found for " + entityName + " (" + entityType + ").");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error generating population report for " + entityName + " (" + entityType + "): " + e.getMessage());
        }
    }

    /// Report 5: Population report with percentages
    public void reportPopulationWithPercentages(Connection connection) {
        String query = "SELECT co.Continent AS Name, " +
                "SUM(co.Population) AS TotalPopulation, " +
                "SUM(ci.Population) AS CityPopulation, " +
                "SUM(co.Population - ci.Population) AS NonCityPopulation, " +
                "ROUND(SUM(ci.Population) * 100.0 / SUM(co.Population), 2) AS CityPercentage, " +
                "ROUND(SUM(co.Population - ci.Population) * 100.0 / SUM(co.Population), 2) AS NonCityPercentage " +
                "FROM country co " +
                "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                "GROUP BY co.Continent;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "Name", "Total Population", "City Population", "Non-City Population", "City %", "Non-City %");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("Name");
                long totalPopulation = rs.getLong("TotalPopulation");
                long cityPopulation = rs.getLong("CityPopulation");
                long nonCityPopulation = rs.getLong("NonCityPopulation");
                double cityPercentage = rs.getDouble("CityPercentage");
                double nonCityPercentage = rs.getDouble("NonCityPercentage");

                System.out.printf("%-20s %-20d %-20d %-20d %-20.2f %-20.2f%n", name, totalPopulation, cityPopulation, nonCityPopulation, cityPercentage, nonCityPercentage);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating population report with percentages: " + e.getMessage());
        }
    }
}
