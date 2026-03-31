package edu.sdccd.cisc191.model;

public class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid name");
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("Invalid GPA");

        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid name");
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("Invalid GPA");
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + gpa;
    }
}
