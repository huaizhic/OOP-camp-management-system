package camp_Application_Management_System;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends User {
    private List<Camp> registeredCamps;
    private List<Enquiry> enquiries;
    private boolean isCampCommittee;

    public Student(String userID, String password, String faculty) {
        super(userID, password, faculty);
        registeredCamps = new ArrayList<>();
        enquiries = new ArrayList<>();
        isCampCommittee = false;
    }

    // Register for a camp as an attendee
    public boolean registerForCamp(Camp camp) {
        if (isRegistrationOpen(camp) && !isRegisteredForCamp(camp)) {
            registeredCamps.add(camp);
            camp.addAttendee(this);
            return true;
        }
        return false;
    }

    // Check if registration is open for a camp
    private boolean isRegistrationOpen(Camp camp) {
        Date currentDate = new Date();
        return currentDate.before(camp.getCampInformation().getRegistrationClosingDate());
    }

    // Check if the student is already registered for a camp
    private boolean isRegisteredForCamp(Camp camp) {
        return registeredCamps.contains(camp);
    }

    // Withdraw from a camp
    public boolean withdrawFromCamp(Camp camp) {
        if (isRegisteredForCamp(camp)) {
            registeredCamps.remove(camp);
            camp.removeAttendee(this);
            return true;
        }
        return false;
    }

    // Submit an enquiry regarding a camp
    public void submitEnquiry(Camp camp, String subject, String message) {
        Enquiry enquiry = new Enquiry(this, camp, subject, message);
        enquiries.add(enquiry);
    }

    // Edit an enquiry before it is processed
    public void editEnquiry(Enquiry enquiry, String subject, String message) {
        if (enquiries.contains(enquiry)) {
            enquiry.setSubject(subject);
            enquiry.setMessage(message);
        }
    }

    // Delete an enquiry before it is processed
    public void deleteEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    // View the list of registered camps and roles
    public List<Camp> viewRegisteredCamps() {
        return registeredCamps;
    }

    // Get the role (attendee or camp committee) for a specific camp
    public String getRoleInCamp(Camp camp) {
        if (registeredCamps.contains(camp)) {
            if (isCampCommittee && camp.isCampCommittee(this)) {
                return "Camp Committee";
            } else {
                return "Attendee";
            }
        }
        return "Not Registered";
    }

    // Toggle the camp committee status
    public void setCampCommitteeStatus(boolean isCampCommittee) {
        this.isCampCommittee = isCampCommittee;
    }
}
