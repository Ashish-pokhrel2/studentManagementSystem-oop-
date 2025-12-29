package reports;

import java.util.List;

public class StudentReport {

    private int studentId;
    private List<Course> courses;
    private List<Double> marks;
    private double attendancePercentage;

    // Constructor
    public StudentReport(int studentId, List<Course> courses, List<Double> marks, double attendancePercentage) {
        this.studentId = studentId;
        this.courses = courses;
        this.marks = marks;
        this.attendancePercentage = attendancePercentage;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Double> getMarks() {
        return marks;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }
}
