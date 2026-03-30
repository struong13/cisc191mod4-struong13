package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.util.DatabaseConfig;
import org.h2.command.Prepared;

import java.sql.*;
import java.util.*;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void save(Student student) {
        // saving student into Student via PreparedStatement INSERT
        String sql = "INSERT INTO students (ID, NAME, GPA) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setDouble(3, student.getGpa());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Exception caught in save() method in JdbcStudentRepository.");
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {
        // selecting a student by id from Student via PreparedStatement SELECT
        String sql = "SELECT ID, NAME, GPA FROM students where ID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int studentId = rs.getInt("ID");
                    String studentName = rs.getString("NAME");
                    double studentGpa = rs.getDouble("GPA");

                    return new Student(studentId, studentName, studentGpa);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("Exception caught in findById() method in JdbcStudentRepository.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        // querying all rows and mapping to List<Student>
        List<Student> studentList = new ArrayList<>();

        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                studentList.add(new Student(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("GPA")));
            }
        }
        catch (SQLException e) {
            System.err.println("Exception caught in findAll() method in JdbcStudentRepository.");
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public void updateGpa(int id, double newGpa) {
        // updating a student's gpa by id from Student via PreparedStatement
        String sql = "UPDATE students SET GPA = ? WHERE ID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newGpa);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Exception caught in updateGpa() method in JdbcStudentRepository.");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        // deleting a student by id from Student via PreparedStatement DELETE
        String studentSQL = "DELETE FROM students WHERE ID = ?";
        String courseSQL = "DELETE FROM courses WHERE STUDENT_ID = ?";
        try (Connection conn = DatabaseConfig.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(courseSQL)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(studentSQL)) {
                pstmt.setInt(1, id);
                pstmt.execute();
            }
        }
        catch (SQLException e) {
            System.err.println("Exception caught in deleteById() method in JdbcStudentRepository.");
            e.printStackTrace();
        }
    }
}