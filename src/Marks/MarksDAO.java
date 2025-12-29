package Marks;

import java.util.*;

public class MarksDAO {
    private List<marks> marksList = new ArrayList<>();

    public boolean addMarks(marks marks) {
        return marksList.add(marks);
    }

    public boolean updateMarks(marks marks) {
        for (marks m : marksList) {
            if (m.getMarksId() == marks.getMarksId()) {
                m.setMarks(marks.getMarks());
                m.setGrade(marks.getGrade());
                return true;
            }
        }
        return false;
    }

    public marks getMarks(int studentId, int courseId) {
        for (marks m : marksList) {
            if (m.getStudentId() == studentId && m.getCourseId() == courseId) {
                return m;
            }
        }
        return null;
    }

    public List<marks> getMarksByStudent(int studentId) {
        List<marks> result = new ArrayList<>();
        for (marks m : marksList) {
            if (m.getStudentId() == studentId) {
                result.add(m);
            }
        }
        return result;
    }
}
