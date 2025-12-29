package student;

public class Student {
    private int studentId;
    private String name;
    private String rollNo;
    private String email;

    public Student(int studentId, String name, String rollNo, String email) {
        this.studentId = studentId;
        this.name = name;
        this.rollNo = rollNo;
        this.email = email;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setEmail(String email) { this.email = email; }
}
