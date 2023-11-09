package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Suggestion;
import com.example.cms.UserGroup;

import java.util.ArrayList;

public class Committee_Member {
    private String userID;
    private String name;
    private UserGroup userGroup;
    private String securityQuestion;
    private String securityAns;
    private ArrayList<Suggestion> suggestions;
    private Camp registeredCamps;
    private int points;

    public Committee_Member(String name, String userID, UserGroup userGroup, String securityQuestion, String securityAns, Camp camp) {
        this.name = name;
        this.userID = userID;
        this.userGroup = userGroup;
        this.securityQuestion = securityQuestion;
        this.securityAns = securityAns;
        this.suggestions = new ArrayList<>();
        registeredCamps = camp;
        this.points = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public Camp getRegisteredCamps() {
        return registeredCamps;
    }

    public void setRegisteredCamps(Camp registeredCamps) {
        this.registeredCamps = registeredCamps;
    }
}



    /*
    // View camp details for camps the committee member is overseeing
    public void viewCampDetails(Camp camp) {
        // Implement viewing camp details logic
    }

    // Submit a suggestion for changes to camp details
    public void submitSuggestion(Camp camp, Suggestion suggestion) {
        // Implement suggestion submission logic
    }

    // View and reply to enquiries from students for the camps the committee member oversees
    public void viewAndReplyToEnquiries(Enquiry enquiry) {
        // Implement viewing and replying to enquiries logic
    }

    // View, edit, and delete suggestions before being processed
    public void viewAndEditSuggestions(Suggestion suggestion) {
        // Implement viewing, editing, and deleting suggestions logic
    }

    // Generate a report of camp attendees with filters
    public void generateCampReport(Camp camp, String filter, String format) {
        // Implement report generation logic
    }

    // Get points for actions (replies, suggestions, and accepted suggestions)
    public void earnPoints() {
        // Implement earning points logic
    }

    // Other methods and getters/setters as needed
}
*/

