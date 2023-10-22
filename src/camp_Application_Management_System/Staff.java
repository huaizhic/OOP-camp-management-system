package camp_Application_Management_System;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staff extends User {
    private List<Camp> createdCamps;
    private List<Enquiry> enquiries;
    private List<Suggestion> suggestions;

    public Staff(String userID, String password, String faculty) {
        super(userID, password, faculty);
        createdCamps = new ArrayList<>();
        enquiries = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    public void createCamp(String campName, Date startDate, Date registrationClosingDate,
                           String userGroup, String location, int totalSlots, int committeeSlots, String description) {
        camp_Information campInfo = new camp_Information(campName, startDate, registrationClosingDate,
                userGroup, location, totalSlots, committeeSlots, description, this);
        Camp newCamp = new Camp(campInfo, this);
        createdCamps.add(newCamp);
    }

    public void editCamp(Camp camp, String newName, String newDescription) {
        camp.getCampInformation().setCampName(newName);
        camp.getCampInformation().setDescription(newDescription);
    }

    public void deleteCamp(Camp camp) {
        createdCamps.remove(camp);
    }

    public void toggleCampVisibility(Camp camp, boolean isVisible) {
        camp.setVisible(isVisible);
    }

    public List<Camp> viewAllCamps() {
        return createdCamps;
    }

    public List<Camp> viewCreatedCamps() {
        return createdCamps;
    }

    public List<Enquiry> viewEnquiries() {
        return enquiries;
    }

    public void replyToEnquiry(Enquiry enquiry, String reply) {
        enquiry.setReply(reply);
    }

    public void approveCampSuggestions(Suggestion suggestion) {
        suggestions.add(suggestion);
        // Implement approval logic
    }

    public void generateCampReport(Camp camp, String filter, String format) {
        // Implement report generation logic
    }

    public void generatePerformanceReport(List<camp_Committee_Member> committeeMembers, String format) {
        // Implement performance report generation logic
    }
}
