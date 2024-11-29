package com.napier.sem;
import java.sql.*;

public class CityReports {

    /// Report 1: All cities in the world organized by largest to smallest population
    public void reportAllCitiesByPopulation(Connection connection) {
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    /// Report 2: All cities in a continent organized by largest to smallest population
    public void reportCitiesByContinent(Connection connection, String continent) {
        String query = "SELECT c.Name, c.CountryCode, c.District, c.Population FROM city c "
                + "JOIN country co ON c.CountryCode = co.Code WHERE co.Continent = ? ORDER BY c.Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, continent);
            try (ResultSet rs = stmt.executeQuery()) {
                // Print the header
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("----------------------------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");

                    // Format the output
                    System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
                }

                System.out.println("----------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    // Report 3: All cities in a region organized by largest to smallest population
    public void reportCitiesByRegion(Connection connection, String region) {
        String query = "SELECT c.Name, c.CountryCode, c.District, c.Population FROM city c "
                + "JOIN country co ON c.CountryCode = co.Code WHERE co.Region = ? ORDER BY c.Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, region);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("---------------------------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    // Report 4: All cities in a country organized by largest to smallest population
    public void reportCitiesByCountry(Connection connection, String countryCode) {
        String query = "SELECT Name, District, Population FROM city WHERE CountryCode = ? ORDER BY Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, countryCode);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-30s %-20s %-15s%n", "City Name", "District", "Population");
                System.out.println("---------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-20s %-15d%n", cityName, district, population);
                }
                System.out.println("---------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    // Report 5: All cities in a district organized by largest to smallest population
    public void reportCitiesByDistrict(Connection connection, String district) {
        String query = "SELECT Name, CountryCode, Population FROM city WHERE District = ? ORDER BY Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, district);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-15s%n", "City Name", "Country Code", "Population");
                System.out.println("---------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-15d%n", cityName, countryCode, population);
                }
                System.out.println("---------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    // Report 6: The top N populated cities in the world where N is provided by the user
    public void reportTopNCitiesByPopulation(Connection connection, int N) {
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, N);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("---------------------------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    // Report 7: The top N populated cities in a continent where N is provided by the user
    public void reportTopNCitiesByContinent(Connection connection, String continent, int N) {
        String query = "SELECT c.Name, c.CountryCode, c.District, c.Population FROM city c "
                + "JOIN country co ON c.CountryCode = co.Code WHERE co.Continent = ? ORDER BY c.Population DESC LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, continent);
            stmt.setInt(2, N);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("---------------------------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    /// Report 8: The top N populated cities in a region where N is provided by the user
    public void reportTopNCitiesByRegion(Connection connection, String region, int N) {
        String query = "SELECT c.Name, c.CountryCode, c.District, c.Population FROM city c "
                + "JOIN country co ON c.CountryCode = co.Code WHERE co.Region = ? ORDER BY c.Population DESC LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, region);
            stmt.setInt(2, N);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("--------------------------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-20s %-15d%n", cityName, countryCode, district, population);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    /// Report 9: The top N populated cities in a country where N is provided by the user
    public void reportTopNCitiesByCountry(Connection connection, String countryCode, int N) {
        String query = "SELECT Name, District, Population FROM city WHERE CountryCode = ? ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, countryCode);
            stmt.setInt(2, N);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-30s %-20s %-15s%n", "City Name", "District", "Population");
                System.out.println("---------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-20s %-15d%n", cityName, district, population);
                }
                System.out.println("---------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    // Report 10: The top N populated cities in a district where N is provided by the user
    public void reportTopNCitiesByDistrict(Connection connection, String district, int N) {
        String query = "SELECT Name, CountryCode, Population FROM city WHERE District = ? ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, district);
            stmt.setInt(2, N);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-15s%n", "City Name", "Country Code", "Population");
                System.out.println("---------------------------------------------------------------");

                while (rs.next()) {
                    String cityName = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-15d%n", cityName, countryCode, population);
                }
                System.out.println("---------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    // Report 11: City Report (Name, Country, District, Population)
    public void reportCity(Connection connection, String cityName) {
        String query = "SELECT Name, CountryCode, District, Population FROM city WHERE Name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cityName);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.printf("%-30s %-10s %-20s %-15s%n", "City Name", "Country Code", "District", "Population");
                System.out.println("---------------------------------------------------------------------------------");

                if (rs.next()) {
                    String name = rs.getString("Name");
                    String countryCode = rs.getString("CountryCode");
                    String district = rs.getString("District");
                    int population = rs.getInt("Population");
                    System.out.printf("%-30s %-10s %-20s %-15d%n", name, countryCode, district, population);
                } else {
                    System.out.println("No data found for the city: " + cityName);
                }
                System.out.println("---------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error generating city report: " + e.getMessage());
        }
    }

}
