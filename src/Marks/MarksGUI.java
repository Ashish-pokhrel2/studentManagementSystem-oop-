package Marks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MarksGUI extends JFrame {
    private JTextField studentIdField, courseIdField, marksField;
    private JTextArea outputArea;
    private MarksDAO marksDAO = new MarksDAO();
    private GradeService gradeService = new GradeService();

    public MarksGUI() {
        setTitle("Marks & Grade Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Course ID:"));
        courseIdField = new JTextField();
        inputPanel.add(courseIdField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Marks");
        JButton fetchButton = new JButton("Fetch Marks");
        inputPanel.add(addButton);
        inputPanel.add(fetchButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentId = Integer.parseInt(studentIdField.getText());
                    int courseId = Integer.parseInt(courseIdField.getText());
                    double marks = Double.parseDouble(marksField.getText());

                    marks m = new marks((int)(Math.random()*1000), studentId, courseId, marks);
                    gradeService.assignGrade(m);
                    marksDAO.addMarks(m);

                    outputArea.append("Added: Student " + studentId + 
                                      ", Course " + courseId + 
                                      ", Marks " + marks + 
                                      ", Grade " + m.getGrade() + "\n");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentId = Integer.parseInt(studentIdField.getText());
                    List<marks> list = marksDAO.getMarksByStudent(studentId);

                    outputArea.append("Results for Student " + studentId + ":\n");
                    for (marks m : list) {
                        outputArea.append("Course " + m.getCourseId() + 
                                          " → Marks: " + m.getMarks() + 
                                          ", Grade: " + m.getGrade() + "\n");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MarksGUI().setVisible(true);
        });
    }
}
