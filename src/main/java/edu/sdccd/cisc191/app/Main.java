package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.*;
import edu.sdccd.cisc191.repository.*;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        StudentRepository studentRepo = new JdbcStudentRepository();
        CourseRepository courseRepo = new JdbcCourseRepository();
        StudentService service = new StudentService(studentRepo);

        service.addStudent(new Student(1, "Lexie", 3.5));
        service.addStudent(new Student(2, "Emily", 3.2));
        service.addStudent(new Student(3, "Natalie", 3.8));

        courseRepo.save(new Course(1, "Art History", 1));
        courseRepo.save(new Course(2, "French", 1));
        courseRepo.save(new Course(3, "Biology", 2));

        service.getAllStudents().forEach(System.out::println);

        System.out.println(service.getStudent(1));

        courseRepo.findByStudentId(1).forEach(System.out::println);

        service.changeGpa(1, 4.0);

        service.removeStudent(3);

        service.getAllStudents().forEach(System.out::println);
        courseRepo.findAll().forEach(System.out::println);
    }
}