package student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private List<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean updateStudent(Student student) {
        for (Student s : students) {
            if (s.getStudentId() == student.getStudentId()) {
                s.setName(student.getName());
                s.setRollNo(student.getRollNo());
                s.setEmail(student.getEmail());
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int studentId) {
        return students.removeIf(s -> s.getStudentId() == studentId);
    }

    public Student getStudentById(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
