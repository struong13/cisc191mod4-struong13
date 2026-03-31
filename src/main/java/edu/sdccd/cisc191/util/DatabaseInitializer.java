package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            // RESET EVERYTHING
            stmt.execute("DROP TABLE IF EXISTS courses");
            stmt.execute("DROP TABLE IF EXISTS students");

            // RECREATE TABLES
            stmt.execute("""
                CREATE TABLE students (
                    id INT PRIMARY KEY,
                    name VARCHAR(100),
                    gpa DOUBLE
                )
            """);

            stmt.execute("""
                CREATE TABLE courses (
                    id INT PRIMARY KEY,
                    title VARCHAR(100),
                    student_id INT,
                    FOREIGN KEY (student_id) REFERENCES students(id)
                )
            """);

        } catch (Exception e) {
            System.out.println("DB init error: " + e.getMessage());
        }
    }
}