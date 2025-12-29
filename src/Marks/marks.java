package Marks;

public class marks {
    private int marksId;
    private int studentId;
    private int courseId;
    private double marks;
    private String grade;

    // Constructor
    public marks(int marksId, int studentId, int courseId, double marks) {
        this.marksId = marksId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
    }

    // Getters & Setters
    public int getMarksId() { return marksId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public double getMarks() { return marks; }
    public String getGrade() { return grade; }

    public void setMarks(double marks) { this.marks = marks; }
    public void setGrade(String grade) { this.grade = grade; }
}

