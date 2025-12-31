package Attendance;

import java.time.LocalDate;

public class AttendanceService {
    
    private AttendanceDAO attendanceDAO;

    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAOImpl();
    }

    public boolean markPresent(int studentId, int courseId, LocalDate date) {
        return markStatus(studentId, courseId, date, "Present");
    }

    public boolean markAbsent(int studentId, int courseId, LocalDate date) {
        return markStatus(studentId, courseId, date, "Absent");
    }
    
    private boolean markStatus(int studentId, int courseId, LocalDate date, String status) {
        if (attendanceDAO.attendanceExists(studentId, courseId, date)) {
            System.out.println("Error: Attendance already marked for Student " + studentId + " on " + date);
            return false;
        }

        Attendance attendance = new Attendance(studentId, courseId, date, status);

        return attendanceDAO.markAttendance(attendance);
    }

    public double getPercentage(int studentId, int courseId) {
        return attendanceDAO.calculateAttendancePercentage(studentId, courseId);
    }
}
