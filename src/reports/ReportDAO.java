package reports;

import java.util.*;

public class ReportDAO {

    private List<Student> students = new ArrayList<>();

    public ReportDAO(List<Student> students) {
        this.students = students;
    }

    public StudentReport generateStudentReport(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return new StudentReport(
                        s.getStudentId(),
                        s.getCourses(),
                        s.getMarks(),
                        s.getAttendancePercentage()
                );
            }
        }
        return null;
    }

    public Map<Course, Double> generateCourseAttendanceReport(int courseId) {
        Map<Course, Double> attendanceMap = new HashMap<>();

        for (Student s : students) {
            for (Course c : s.getCourses()) {
                if (c.getCourseId() == courseId) {
                    attendanceMap.put(c, s.getAttendancePercentage());
                }
            }
        }
        return attendanceMap;
    }

    public List<Student> getDefaulterList(double minAttendance) {
        List<Student> defaulters = new ArrayList<>();

        for (Student s : students) {
            if (s.getAttendancePercentage() < minAttendance) {
                defaulters.add(s);
            }
        }
        return defaulters;
    }
}
