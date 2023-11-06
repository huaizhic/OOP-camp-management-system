package com.example.cms.Student;

import com.example.cms.Enquiry;
import com.example.cms.Suggestion;
import com.example.cms.Camp.Camp;

import java.util.ArrayList;
import java.util.List;

public class committee_Member {
    private String userID; // User ID for the committee member
    private List<Camp> registeredCamps; // List of camps that the committee member is overseeing
    private List<Suggestion> suggestions; // List of suggestions submitted by the committee member
    private int points; // Points earned by the committee member

    public committee_Member(String userID) {
        this.userID = userID; // Hello
        this.registeredCamps = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.points = 0;
    }

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


