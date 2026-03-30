package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.util.DatabaseConfig;

import java.util.*;
import java.sql.*;

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        // saving Course into courses via PreparedStatement INSERT
        String sql = "INSERT INTO courses (id, title, student_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getTitle());
            pstmt.setInt(3, course.getStudentId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Exception caught in save() method in JdbcCourseRepository.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        // querying courses by studentId from courses and mapping to List<Course>
        List<Course> courseList = new ArrayList<>();

        String sql = "SELECT ID, TITLE, STUDENT_ID FROM courses WHERE STUDENT_ID = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    courseList.add(new Course(
                            rs.getInt("ID"),
                            rs.getString("TITLE"),
                            rs.getInt("STUDENT_ID")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Exception caught in findById() method in JdbcStudentRepository.");
            e.printStackTrace();
        }

        return courseList;
    }
    @Override
    public List<Course> findAll() {
        // querying all rows and mapping to List<Course>
        List<Course> courseList = new ArrayList<>();

        String sql = "SELECT * FROM courses";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                courseList.add(new Course(
                        rs.getInt("ID"),
                        rs.getString("TITLE"),
                        rs.getInt("STUDENT_ID")));
            }
        } catch (SQLException e) {
            System.err.println("Exception caught in findAll()");
            e.printStackTrace();
        }
        return courseList;
    }
}