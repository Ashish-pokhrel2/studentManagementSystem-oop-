package reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class report extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea outputArea;
    private ReportDAO reportDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                report frame = new report();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public report() {
        // Sample data
        Course math = new Course(101, "Mathematics");
        Course science = new Course(102, "Science");
        Course english = new Course(103, "English");

        Student s1 = new Student(1, "Alice",
                Arrays.asList(math, science),
                Arrays.asList(85.0, 90.0),
                95.0);

        Student s2 = new Student(2, "Bob",
                Arrays.asList(math, english),
                Arrays.asList(70.0, 75.0),
                60.0);

        Student s3 = new Student(3, "Charlie",
                Arrays.asList(science, english),
                Arrays.asList(80.0, 65.0),
                50.0);

        reportDAO = new ReportDAO(Arrays.asList(s1, s2, s3));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Top panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 5, 5));

        JLabel studentIdLabel = new JLabel("Student ID:");
        JTextField studentIdField = new JTextField();
        JButton studentReportBtn = new JButton("Student Report");

        JLabel courseIdLabel = new JLabel("Course ID:");
        JTextField courseIdField = new JTextField();
        JButton courseReportBtn = new JButton("Course Attendance");

        JLabel minAttendanceLabel = new JLabel("Min Attendance:");
        JTextField minAttendanceField = new JTextField();
        JButton defaulterBtn = new JButton("Defaulter List");

        inputPanel.add(studentIdLabel);
        inputPanel.add(studentIdField);
        inputPanel.add(studentReportBtn);

        inputPanel.add(courseIdLabel);
        inputPanel.add(courseIdField);
        inputPanel.add(courseReportBtn);

        inputPanel.add(minAttendanceLabel);
        inputPanel.add(minAttendanceField);
        inputPanel.add(defaulterBtn);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        studentReportBtn.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                StudentReport report = reportDAO.generateStudentReport(studentId);
                if (report != null) {
                    outputArea.setText(formatStudentReport(report));
                } else {
                    outputArea.setText("Student not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Student ID!");
            }
        });

        courseReportBtn.addActionListener(e -> {
            try {
                int courseId = Integer.parseInt(courseIdField.getText());
                Map<Course, Double> attendanceMap = reportDAO.generateCourseAttendanceReport(courseId);
                if (!attendanceMap.isEmpty()) {
                    outputArea.setText(formatCourseAttendance(attendanceMap));
                } else {
                    outputArea.setText("No students enrolled in this course.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Course ID!");
            }
        });

        defaulterBtn.addActionListener(e -> {
            try {
                double minAttendance = Double.parseDouble(minAttendanceField.getText());
                List<Student> defaulters = reportDAO.getDefaulterList(minAttendance);
                if (!defaulters.isEmpty()) {
                    outputArea.setText(formatDefaulters(defaulters));
                } else {
                    outputArea.setText("No defaulters found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid attendance value!");
            }
        });
    }

    private String formatStudentReport(StudentReport report) {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(report.getStudentId()).append("\n");
        sb.append("Attendance: ").append(report.getAttendancePercentage()).append("%\n");
        sb.append("Courses & Marks:\n");
        List<Course> courses = report.getCourses();
        List<Double> marks = report.getMarks();
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i).getCourseName())
              .append(" (ID: ").append(courses.get(i).getCourseId())
              .append(") - Marks: ").append(marks.get(i)).append("\n");
        }
        return sb.toString();
    }

    private String formatCourseAttendance(Map<Course, Double> attendanceMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("Course Attendance:\n");
        for (Map.Entry<Course, Double> entry : attendanceMap.entrySet()) {
            sb.append(entry.getKey().getCourseName())
              .append(" (ID: ").append(entry.getKey().getCourseId())
              .append(") - Attendance: ").append(entry.getValue()).append("%\n");
        }
        return sb.toString();
    }

    private String formatDefaulters(List<Student> defaulters) {
        StringBuilder sb = new StringBuilder();
        sb.append("Defaulter List:\n");
        for (Student s : defaulters) {
            sb.append("Student ID: ").append(s.getStudentId())
              .append(" - Attendance: ").append(s.getAttendancePercentage()).append("%\n");
        }
        return sb.toString();
    }
}
