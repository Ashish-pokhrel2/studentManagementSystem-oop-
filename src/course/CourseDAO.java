package course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private List<Course> courses = new ArrayList<>();

    public boolean addCourse(Course course) {
        return courses.add(course);
    }

    public boolean updateCourse(Course course) {
        for (Course c : courses) {
            if (c.getCourseId() == course.getCourseId()) {
                c.setCourseName(course.getCourseName());
                c.setCredits(course.getCredits());
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int courseId) {
        return courses.removeIf(c -> c.getCourseId() == courseId);
    }

    public List<Course> getAllCourses() {
        return courses;
    }
}
