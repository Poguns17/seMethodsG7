package com.napier.sem;

import java.sql.*;

public class LanguageReports {

    // Report 1: Number of speakers globally for specified languages
    public void reportSpeakersForLanguage(Connection connection, String language) {
        String query = "SELECT SUM(Population) AS TotalPopulation FROM countrylanguage "
                + "JOIN country ON country.Code = countrylanguage.CountryCode "
                + "WHERE countrylanguage.Language = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, language);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long population = rs.getLong("TotalPopulation");
                    System.out.println(language + " speakers globally: " + population);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    // Report 2: Percentage of the world population for each language
    public void reportPercentageOfWorldForLanguage(Connection connection, String language) {
        String query = "SELECT SUM(Population) AS TotalPopulation FROM countrylanguage "
                + "JOIN country ON country.Code = countrylanguage.CountryCode "
                + "WHERE countrylanguage.Language = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, language);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long languagePopulation = rs.getLong("TotalPopulation");

                    // Query for total world population
                    String worldQuery = "SELECT SUM(Population) AS WorldPopulation FROM country";
                    try (Statement worldStmt = connection.createStatement(); ResultSet worldRs = worldStmt.executeQuery(worldQuery)) {
                        if (worldRs.next()) {
                            long worldPopulation = worldRs.getLong("WorldPopulation");
                            double percentage = (double) languagePopulation / worldPopulation * 100;
                            System.out.printf("Language: %s, Population: %d, Percentage of World: %.2f%%\n", language, languagePopulation, percentage);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}
