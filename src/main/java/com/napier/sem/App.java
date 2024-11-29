package com.napier.sem;
import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=true", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        System.out.println("Application started...");

        try
        {
            CountryReports reportGenerator = new CountryReports();
            reportGenerator.reportAllCountriesByPopulation(con);
        }
        catch (Exception e)
        {
            System.out.println("An error occurred: " + e.getMessage());
        }


        System.out.println("City Reports...");

        // Create an instance of CityReports to execute reports
        try
        {
            CityReports cityReports = new CityReports();

            // Call various methods to generate reports
            cityReports.reportAllCitiesByPopulation(con); // Report 1
            cityReports.reportCitiesByContinent(con, "Europe"); // Report 2
            cityReports.reportCitiesByRegion(con, "Western Europe"); // Report 3
            cityReports.reportTopNCitiesByPopulation(con, 10); // Report 6
            cityReports.reportTopNCitiesByContinent(con, "Europe", 10); // Report 7
            cityReports.reportCity(con, "Amsterdam"); // Report 11
        }
        catch (Exception e)
        {
            System.out.println("An error occurred while generating reports: " + e.getMessage());
        }

        // Close the connection to the database
        if (con != null)
        {
            try
            {
                con.close();
                System.out.println("Database connection closed");
            }
            catch (SQLException e)
            {
                System.out.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }
}
