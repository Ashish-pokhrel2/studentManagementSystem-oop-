import student.*;
import course.*;
import enrollment.*;

public class Main {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        Student s1 = new Student(1, "Ram", "A01", "ram@gmail.com");
        Course c1 = new Course(101, "Java", 4);

        studentDAO.addStudent(s1);
        courseDAO.addCourse(c1);

        enrollmentDAO.enrollStudent(1, 101);

        System.out.println("Students: " + studentDAO.getAllStudents().size());
        System.out.println("Courses: " + courseDAO.getAllCourses().size());
    }
}
