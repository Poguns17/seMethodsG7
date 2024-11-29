package com.napier.sem;
import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.fail;

public class LanguageReportsIntTest {

    private static Connection connection;
    private static LanguageReports languageReports;

    @BeforeAll
    public static void setUpClass() throws SQLException {
        // Setup database connection for all tests
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=true", "root", "example");
        languageReports = new LanguageReports();
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        // Close the database connection after all tests
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testReportSpeakersForLanguageIntegration() {
        try {
            languageReports.reportSpeakersForLanguage(connection, "Spanish");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPercentageOfWorldForLanguageIntegration() {
        try {
            languageReports.reportPercentageOfWorldForLanguage(connection, "Hindi");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }
}

