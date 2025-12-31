package Attendance;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AttendanceService service = new AttendanceService();
        int sID = 101;
        int cID = 505;
        LocalDate today = LocalDate.now();

        System.out.println("--- Attendance Management System ---");

        boolean updateSuccess = service.updateStatus(sID, cID, today, "Present");
        System.out.println("Record Updated to Present: " + updateSuccess);

        System.out.println("Attendance Percentage: " + service.getPercentage(sID, cID) + "%");
    }
}