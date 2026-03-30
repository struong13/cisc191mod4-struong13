package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.JdbcCourseRepository;
import edu.sdccd.cisc191.repository.JdbcStudentRepository;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // initializing databases
        DatabaseInitializer.initialize();

        // creating student service and repositories
        JdbcStudentRepository studentRepo = new JdbcStudentRepository();
        JdbcCourseRepository courseRepo = new JdbcCourseRepository();

        StudentService studentService = new StudentService(studentRepo);

        // adding at least three students to List<Student>
        String[] names = {
                "Arnold", "Bernice", "Carl", "Darryl", "Erica",
                "Francis", "Gerald", "Harmonica", "Isle", "Jeffrey",
                "Karl", "Leonardo", "Marilyn", "Nutella", "Oracle",
                "Pomona", "Quilt", "Ricardo", "Susan", "Tofu",
                "Umbrella", "Victoria", "Wicardo", "Yakult", "Zeppelin"
        };

        Random rand = new Random();

        List<Student> roster = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int nameIndex = rand.nextInt(names.length);
            String randomName = names[nameIndex];

            double randomGpa = Math.round(rand.nextDouble() * 4.0 * 10.0) / 10.0;

            int randomID = rand.nextInt(90000) + 10000;

            Student currentStudent = new Student(randomID, randomName, randomGpa);

            roster.add(currentStudent);
            studentRepo.save(currentStudent);
        }

        // adding at least 3 courses linked to students
        String[] courseNames = {
                "English", "Psychology", "Art", "Calculus", "Physics",
                "Biology", "History", "Trigonometry", "Chemistry", "Sociology"
        };

        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int courseNameIndex = rand.nextInt(courseNames.length);
            String randomCourseName = courseNames[courseNameIndex];

            int randomCourseID = rand.nextInt(900) + 100;

            int currentStudent = roster.get(i).getId();

            Course currentCourse = new Course(randomCourseID, randomCourseName, currentStudent);

            courseList.add(currentCourse);
            courseRepo.save(currentCourse);
        }

        // printing all students
        System.out.println("Roster: ");
        List<Student> allStudents = studentService.getAllStudents();
        for (Student s : allStudents) {
            System.out.println(s);
        }

        // finding one student by ID
        int randomIndex = rand.nextInt(roster.size());
        int selectedId = roster.get(randomIndex).getId();

        System.out.println("\nFinding Student ID: " + selectedId);
        System.out.println(studentService.getStudent(selectedId));

        // printing a student's courses
        System.out.println("\nCourses for Student ID: " + selectedId);
        List<Course> studentCourses = courseRepo.findByStudentId(selectedId);
        for (Course c : studentCourses) {
            System.out.println(c);
        }

        // updating GPA of one student by ID
        double generateRandomGpa = Math.round(rand.nextDouble() * 4.0 * 10.0) / 10.0;

        System.out.println("\nUpdating " + roster.get(randomIndex).getName() + "'s GPA");
        System.out.println("- Former GPA: " + roster.get(randomIndex).getGpa());

        studentService.changeGpa(selectedId, generateRandomGpa);

        System.out.println("- Updated GPA: " + studentService.getStudent(selectedId).getGpa());

        // deleting a student
        System.out.println("\nDeleting Student: " + selectedId);
        studentService.removeStudent(selectedId);

        // printing remaining students
        System.out.println("\nUpdated Roster: ");
        for (Student s : studentService.getAllStudents()) {
            System.out.println(s);
        }
    }
}