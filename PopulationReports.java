import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PopulationReports {

    private static final Logger LOGGER = Logger.getLogger(PopulationReports.class.getName());

    // Method 1: All countries in the world ordered by largest population
    public void getCountriesByPopulation() {
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching countries by population", e);
        }
    }

    // Method 2: All countries in a specific continent ordered by largest population
    public void getCountriesByPopulationInContinent(String continent) {
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, continent);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching countries by population in continent: " + continent, e);
        }
    }

    // Method 20: Top N populated capital cities in a region
    public void getTopNCapitalCitiesByPopulationInRegion(int n, String region) {
        // Revert to traditional string if text block causes warnings
        String query = "SELECT city.Name, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, region);
            stmt.setInt(2, n);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Capital City: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching top " + n + " capital cities by population in region: " + region, e);
        }
    }
}


