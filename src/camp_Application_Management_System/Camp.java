package camp_Application_Management_System;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Camp {
    private camp_Information campInformation;
    private Staff staffInCharge;
    private List<Student> attendees;
    private boolean isVisible;

    public Camp(camp_Information campInformation, Staff staffInCharge) {
        this.campInformation = campInformation;
        this.staffInCharge = staffInCharge;
        this.isVisible = true;
        this.attendees = new ArrayList<>();
    }

    public camp_Information getCampInformation() {
        return campInformation;
    }

    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    // Additional methods for camp functionality, e.g., report generation, attendance management, etc.

    // Generate a report of camp attendees with filters
    public void generateCampReport(String filter, String format) {
        // Implement report generation logic based on the filter and format
    }

    // Check if a student is part of the camp committee
    public boolean isCampCommittee(Student student) {
        return attendees.contains(student) && student.getRoleInCamp(this).equals("Camp Committee");
    }

    // Add a student as an attendee
    public void addAttendee(Student student) {
        if (!attendees.contains(student)) {
            attendees.add(student);
        }
    }

    // Remove a student from the attendees list
    public void removeAttendee(Student student) {
        attendees.remove(student);
    }
}
