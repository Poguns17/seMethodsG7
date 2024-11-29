package com.napier.sem;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageReportsUnitTest {

    private Connection connection;
    private LanguageReports languageReports;

    @BeforeEach
    public void setUp() throws SQLException {
        // Setup database connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=true", "root", "example");
        languageReports = new LanguageReports();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Close the database connection after each test
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testReportSpeakersForLanguage() {
        try {
            languageReports.reportSpeakersForLanguage(connection, "Chinese");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportPercentageOfWorldForLanguage() {
        try {
            languageReports.reportPercentageOfWorldForLanguage(connection, "English");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }
}

