package edu.sdccd.cisc191.util;

import java.sql.*;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            // creating a students table if it does not exist
            String studentTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "gpa DOUBLE NOT NULL" +
                    ")";

            // creating a courses table if it does not exist
            String courseTableSQL = "CREATE TABLE IF NOT EXISTS courses (" +
                    "id INT PRIMARY KEY," +
                    "title VARCHAR(100) NOT NULL," +
                    "student_id INT," +
                    "FOREIGN KEY (student_id) REFERENCES students(id)" +
                    ")";
            stmt.executeUpdate(studentTableSQL);
            stmt.executeUpdate(courseTableSQL);

            // deleting tables from previous runs
            stmt.executeUpdate("DELETE FROM courses");
            stmt.executeUpdate("DELETE FROM students");
        }
        catch (SQLException e) {
            System.err.println("Exception caught in initialize() method in DatabaseInitializer.");
            e.printStackTrace();
        }
    }
}