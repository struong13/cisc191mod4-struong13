package edu.sdccd.cisc191.model;

    public class Course {
        // initializing private fields
        private int id;
        private String title;
        private int studentId;

        public Course(int id, String title, int studentId) {
            // validating fields
            if (id <= 0 || title == null || title.trim().isEmpty() || studentId <= 0) {
                throw new IllegalArgumentException("There is an invalid input for Course.");
            }

            // assigning fields
            this.id = id;
            this.title = title;
            this.studentId = studentId;
        }

        // public field getters
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
            return "Student ID: " + studentId + "; Course Name: " + title + "; Course ID: " + id;
        }
    }
