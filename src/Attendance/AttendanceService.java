package Attendance;

import java.time.LocalDate;

public class AttendanceService {
    private AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    public boolean markPresent(int studentId, int courseId, LocalDate date) {
        if (attendanceDAO.attendanceExists(studentId, courseId, date)) {
            System.out.println("Error: Attendance already marked for Student " + studentId + " on " + date);
            return false;
        }
        return attendanceDAO.markAttendance(new Attendance(studentId, courseId, date, "Present"));
    }

    public boolean markAbsent(int studentId, int courseId, LocalDate date) {
        if (attendanceDAO.attendanceExists(studentId, courseId, date)) {
            System.out.println("Error: Attendance already marked for Student " + studentId + " on " + date);
            return false;
        }
        return attendanceDAO.markAttendance(new Attendance(studentId, courseId, date, "Absent"));
    }

    public boolean updateStatus(int studentId, int courseId, LocalDate date, String status) {
        return attendanceDAO.updateAttendanceStatus(studentId, courseId, date, status);
    }

    public boolean removeRecord(int studentId, int courseId, LocalDate date) {
        return attendanceDAO.deleteAttendance(studentId, courseId, date);
    }

    public double getPercentage(int studentId, int courseId) {
        return attendanceDAO.calculateAttendancePercentage(studentId, courseId);
    }
}