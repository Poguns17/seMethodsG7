package org.example;

public class PopulationReports {

    public static void main(String[] args) {
        // Create an instance of PopulationReports
        PopulationReports reports = new PopulationReports();

        // Display countries by population
        System.out.println("Countries by population:");
        reports.getCountriesByPopulation();

        // Display top 5 populated countries in the world
        System.out.println("\nTop 5 populated countries in the world:");
        reports.getTopNCountriesByPopulation(5);
    }

    // Example methods to be implemented
    public void getCountriesByPopulation() {
        // Implementation goes here (e.g., fetching and displaying data from the database)
        System.out.println("Fetching all countries by population...");
    }

    public void getTopNCountriesByPopulation(int n) {
        // Implementation goes here (e.g., fetching and displaying data from the database)
        System.out.println("Fetching top " + n + " populated countries...");
    }
}

