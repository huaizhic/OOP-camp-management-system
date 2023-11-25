package com.example.cms.CSVConverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Student.Attendee;
import com.example.cms.Student.Student_User;
import com.example.cms.Suggestions.Suggestion;

/**
 * Controller class to write new information for the first time to CSV (data) of several classes, such as Student User, Attendee, Committee, etc.
 */
public class CSVWriter {

    // Method to write user information to a CSV file
    public static void writeStudentUserToCSV(Student_User student, boolean appendHeader) {
        String csvFilePath = "student.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            // Check if the CSV file is empty
            if (isFileEmpty(csvFilePath)) {
                // Add the CSV header
                writer.write("StudentID,Name,Password,Salt,UserGroup,Faculty,Points,CampAccessibility,CampCommittee,RegisteredCamps,"
                        + "SecurityQuestion,SecurityAnswers,EnquirySubmitted,SuggestionSubmitted");
                writer.newLine();
            }
        	
        	// Convert ArrayList<Camp> to an array of CharSequence with null check
            CharSequence[] campAccessibilityArray = toArrayWithNullCheckList(student.getCampAccessibility());
            CharSequence[] registeredCampsArray = toArrayWithNullCheckCamp(student.getRegisteredCamps());
            CharSequence[] enquirySubmittedArray = toArrayWithNullCheckEnquiry(student.getEnquirySubmitted());
            CharSequence[] suggestionSubmittedArray = toArrayWithNullCheckSuggestion(student.getSuggestionSubmitted());

            // Append the user information to the CSV file
            writer.write(student.getStudentID() + ","
                    + student.getName() + ","
                    + student.getPassword() + ","
                    + student.getSalt() + ","        
                    + student.getUserGroup() + ","
                    + student.getFaculty() + ","
                    + student.getPoints() + ","
                    + String.join("|", campAccessibilityArray) + ","
                    + student.getCampCommittee() + ","
                    + String.join("|", registeredCampsArray) + ","
                    + String.join("|", student.getSecurityQuestion()) + ","
                    + String.join("|", student.getSecurityAnswers()) + ","
                    + String.join("|", enquirySubmittedArray) + ","
                    + String.join("|", suggestionSubmittedArray));
            writer.newLine();

            System.out.println("User information written to " + csvFilePath + " successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
    
    public static void writeAttendeeToCSV(Student_User student, boolean appendHeader) {
        String csvFilePath = "attendee.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            // Check if the CSV file is empty
            if (isFileEmpty(csvFilePath)) {
                // Add the CSV header
                writer.write("StudentID,Name,Password,UserGroup,Faculty,CampAccessibility,CampCommittee,RegisteredCamps,"
                        + "SecurityQuestion,SecurityAnswers,EnquirySubmitted");
                writer.newLine();
            }

            // Convert ArrayList<Camp> to an array of CharSequence with null check
            CharSequence[] campAccessibilityArray = toArrayWithNullCheckList(student.getCampAccessibility());
            CharSequence[] registeredCampsArray = toArrayWithNullCheckCamp(student.getRegisteredCamps());
            CharSequence[] enquirySubmittedArray = toArrayWithNullCheckEnquiry(student.getEnquirySubmitted());

            // Append the user information to the CSV file
            writer.write(student.getStudentID() + ","
                    + student.getName() + ","
                    + student.getPassword() + ","
                    + student.getUserGroup() + ","
                    + student.getFaculty() + ","
                    + String.join("|", campAccessibilityArray) + ","
                    + student.getCampCommittee() + ","
                    + String.join("|", registeredCampsArray) + ","
                    + String.join("|", student.getSecurityQuestion()) + ","
                    + String.join("|", student.getSecurityAnswers()) + ","
                    + String.join("|", enquirySubmittedArray));
            writer.newLine();

            System.out.println("Attendee information written to " + csvFilePath + " successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }

    public static void writeCommitteeToCSV(Student_User student, boolean appendHeader) {
        String csvFilePath = "committee.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            // Check if the CSV file is empty
            if (isFileEmpty(csvFilePath)) {
                // Add the CSV header
                writer.write("StudentID,Name,Password,UserGroup,Faculty,Points,CampAccessibility,CampCommittee,RegisteredCamps,"
                        + "SecurityQuestion,SecurityAnswers,SuggestionSubmitted");
                writer.newLine();
            }

            // Convert ArrayList<Camp> to an array of CharSequence with null check
            CharSequence[] campAccessibilityArray = toArrayWithNullCheckList(student.getCampAccessibility());
            CharSequence[] registeredCampsArray = toArrayWithNullCheckCamp(student.getRegisteredCamps());
            CharSequence[] suggestionSubmittedArray = toArrayWithNullCheckSuggestion(student.getSuggestionSubmitted());

            // Append the user information to the CSV file
            writer.write(student.getStudentID() + ","
                    + student.getName() + ","
                    + student.getPassword() + ","
                    + student.getUserGroup() + ","
                    + student.getFaculty() + ","
                    + student.getPoints() + ","
                    + String.join("|", campAccessibilityArray) + ","
                    + student.getCampCommittee() + ","
                    + String.join("|", registeredCampsArray) + ","
                    + String.join("|", student.getSecurityQuestion()) + ","
                    + String.join("|", student.getSecurityAnswers()) + ","
                    + String.join("|", suggestionSubmittedArray));
            writer.newLine();

            System.out.println("Attendee information written to " + csvFilePath + " successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }

    // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckCamp(List<Camp> list) {
        if (list != null) {
            return list.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
    // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckEnquiry(List<Enquiry> list) {
        if (list != null) {
            return list.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
 // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckSuggestion(List<Suggestion> list) {
        if (list != null) {
            return list.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
    // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckList(List<String> list) {
        if (list != null) {
            return list.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
    public static boolean isFileEmpty(String filePath) {
        File file = new File(filePath);
        return file.length() == 0;
    }
    
    
 
    
}
