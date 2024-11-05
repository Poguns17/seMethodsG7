import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationReports {

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
            e.printStackTrace();
        }
    }

    // Method 2: All countries in a specific continent ordered by largest population
    public void getCountriesByPopulationInContinent(String continent) {
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 3: All countries in a specific region ordered by largest population
    public void getCountriesByPopulationInRegion(String region) {
        String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY Population DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 4: Top N populated countries in the world
    public void getTopNCountriesByPopulation(int n) {
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 5: Top N populated countries in a continent
    public void getTopNCountriesByPopulationInContinent(int n, String continent) {
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 6: Top N populated countries in a region
    public void getTopNCountriesByPopulationInRegion(int n, String region) {
        String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY Population DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 7: All cities in the world ordered by largest population
    public void getCitiesByPopulation() {
        String query = "SELECT Name, Population FROM city ORDER BY Population DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("City: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 8: All cities in a specific continent ordered by largest population
    public void getCitiesByPopulationInContinent(String continent) {
        String query = """
            SELECT city.Name, city.Population
            FROM city
            JOIN country ON city.CountryCode = country.Code
            WHERE country.Continent = ?
            ORDER BY city.Population DESC
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("City: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 9: All cities in a specific region ordered by largest population
    public void getCitiesByPopulationInRegion(String region) {
        String query = """
            SELECT city.Name, city.Population
            FROM city
            JOIN country ON city.CountryCode = country.Code
            WHERE country.Region = ?
            ORDER BY city.Population DESC
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("City: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method 20: Top N populated capital cities in a region
    public void getTopNCapitalCitiesByPopulationInRegion(int n, String region) {
        String query = """
            SELECT city.Name, city.Population
            FROM city
            JOIN country ON city.ID = country.Capital
            WHERE country.Region = ?
            ORDER BY city.Population DESC LIMIT ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Capital City: " + rs.getString("Name") + ", Population: " + rs.getInt("Population"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


