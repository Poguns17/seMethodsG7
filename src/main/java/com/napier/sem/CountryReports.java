package com.napier.sem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountryReports {

    /// Report 1: All countries in the world by population
    public void reportAllCountriesByPopulation(Connection connection) {
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 2: All countries in a continent by population
    public void reportCountriesInContinentByPopulation(Connection connection, String continent) {
        String query = "SELECT Name, Population FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 3: All countries in a region by population
    public void reportCountriesInRegionByPopulation(Connection connection, String region) {
        String query = "SELECT Name, Population FROM country WHERE Region = '" + region + "' ORDER BY Population DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 4: Top N populated countries in the world
    public void reportTopNCountriesInWorld(Connection connection, int n) {
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT " + n;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 5: Top N populated countries in a continent
    public void reportTopNCountriesInContinent(Connection connection, int n, String continent) {
        String query = "SELECT Name, Population FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC LIMIT " + n;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 6: Top N populated countries in a region
    public void reportTopNCountriesInRegion(Connection connection, int n, String region) {
        String query = "SELECT Name, Population FROM country WHERE Region = '" + region + "' ORDER BY Population DESC LIMIT " + n;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-30s %-15s%n", "Country", "Population");
            System.out.println("--------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int population = resultSet.getInt("Population");
                System.out.printf("%-30s %-15d%n", name, population);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Report 7: Country report with detailed fields
    public void reportCountryDetails(Connection connection) {
        String query = "SELECT Code, Name, Continent, Region, Population, " +
                "(SELECT Name FROM city WHERE city.ID = country.Capital) AS Capital " +
                "FROM country";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print the report header
            System.out.printf("%n%-10s %-30s %-15s %-20s %-15s %-30s%n",
                    "Code", "Country", "Continent", "Region", "Population", "Capital");
            System.out.println("-------------------------------------------------------------------------------------------");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                String code = resultSet.getString("Code");
                String name = resultSet.getString("Name");
                String continent = resultSet.getString("Continent");
                String region = resultSet.getString("Region");
                int population = resultSet.getInt("Population");
                String capital = resultSet.getString("Capital");

                System.out.printf("%-10s %-30s %-15s %-20s %-15d %-30s%n",
                        code, name, continent, region, population, capital);
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



