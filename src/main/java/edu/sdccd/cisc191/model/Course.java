package edu.sdccd.cisc191.model;

public class Course {
    private int id;
    private String title;
    private int studentId;

    public Course(int id, String title, int studentId) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Invalid title");
        if (studentId <= 0) throw new IllegalArgumentException("Invalid studentId");

        this.id = id;
        this.title = title;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return id + " " + title + " studentId=" + studentId;
    }
}
