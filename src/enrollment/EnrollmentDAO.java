package enrollment;

import course.Course;
import java.util.*;

public class EnrollmentDAO {

    // studentId -> list of courses
    private Map<Integer, List<Course>> enrollments = new HashMap<>();

    public boolean enrollStudent(int studentId, int courseId) {
        enrollments.putIfAbsent(studentId, new ArrayList<>());
        return true;
    }

    public boolean removeEnrollment(int studentId, int courseId) {
        List<Course> courses = enrollments.get(studentId);
        if (courses == null) return false;
        return courses.removeIf(c -> c.getCourseId() == courseId);
    }

    public List<Course> getCoursesByStudent(int studentId) {
        return enrollments.getOrDefault(studentId, new ArrayList<>());
    }
}
