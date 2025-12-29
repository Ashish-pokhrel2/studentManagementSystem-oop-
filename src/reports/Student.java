package reports;

import java.util.List;

public class Student {

    private int studentId;
    private String name;
    private List<Course> courses;
    private List<Double> marks;
    private double attendancePercentage;

    public Student(int studentId, String name, List<Course> courses, List<Double> marks, double attendancePercentage) {
        this.studentId = studentId;
        this.name = name;
        this.courses = courses;
        this.marks = marks;
        this.attendancePercentage = attendancePercentage;
    }

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
