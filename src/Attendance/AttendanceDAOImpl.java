package Attendance;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public boolean markAttendance(Attendance attendance) {
        String query = "INSERT INTO attendance (student_id, course_id, attendance_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, attendance.getStudentId());
            ps.setInt(2, attendance.getCourseId());
            ps.setDate(3, Date.valueOf(attendance.getDate()));
            ps.setString(4, attendance.getStatus());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean attendanceExists(int studentId, int courseId, LocalDate date) {
        String query = "SELECT count(*) FROM attendance WHERE student_id=? AND course_id=? AND attendance_date=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, Date.valueOf(date));
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        List<Attendance> list = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Attendance> getAttendanceByCourse(int courseId) {
        List<Attendance> list = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE course_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public double calculateAttendancePercentage(int studentId, int courseId) {
        String totalQuery = "SELECT count(*) FROM attendance WHERE student_id=? AND course_id=?";
        String presentQuery = "SELECT count(*) FROM attendance WHERE student_id=? AND course_id=? AND status='Present'";
        
        try (Connection conn = DBConnection.getConnection()) {
            
            // Get Total Classes
            PreparedStatement psTotal = conn.prepareStatement(totalQuery);
            psTotal.setInt(1, studentId);
            psTotal.setInt(2, courseId);
            ResultSet rsTotal = psTotal.executeQuery();
            int total = 0;
            if (rsTotal.next()) total = rsTotal.getInt(1);
            
            if (total == 0) return 0.0; // Avoid division by zero

            // Get Present Classes
            PreparedStatement psPresent = conn.prepareStatement(presentQuery);
            psPresent.setInt(1, studentId);
            psPresent.setInt(2, courseId);
            ResultSet rsPresent = psPresent.executeQuery();
            int present = 0;
            if (rsPresent.next()) present = rsPresent.getInt(1);

            return ((double) present / total) * 100;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Helper method to map SQL row to Java Object
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance att = new Attendance();
        att.setAttendanceId(rs.getInt("attendance_id"));
        att.setStudentId(rs.getInt("student_id"));
        att.setCourseId(rs.getInt("course_id"));
        att.setDate(rs.getDate("attendance_date").toLocalDate());
        att.setStatus(rs.getString("status"));
        return att;
    }
}
