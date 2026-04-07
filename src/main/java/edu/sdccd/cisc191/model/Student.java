package edu.sdccd.cisc191.model;

public class Student {
    // initializing private fields
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        // validating fields
        if (id <= 0 || name == null || name.trim().isEmpty() || gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("There is an invaid input for Student.");
        }

        // assigning fields
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // public field getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    // making name and gpa changeable / mutable objects
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name is invalid.");
        }
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("Student GPA is invalid.");
        }
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "; Student Name: " + name + "; GPA: " + gpa;
    }
}