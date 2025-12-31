package Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceDAO {
    boolean markAttendance(Attendance attendance);
    boolean attendanceExists(int studentId, int courseId, LocalDate date);
    List<Attendance> getAttendanceByStudent(int studentId);
    List<Attendance> getAttendanceByCourse(int courseId);
    double calculateAttendancePercentage(int studentId, int courseId);
    boolean updateAttendanceStatus(int studentId, int courseId, LocalDate date, String newStatus);
    boolean deleteAttendance(int studentId, int courseId, LocalDate date);
}