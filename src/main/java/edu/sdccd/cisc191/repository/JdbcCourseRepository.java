package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.util.DatabaseConfig;
import java.sql.*;
import java.util.*;

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, course.getId());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getStudentId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert course error");
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE student_id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Course(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("student_id")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Fetch course error");
        }

        return list;
    }

    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM courses")) {

            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("student_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Fetch all courses error");
        }

        return list;
    }
}