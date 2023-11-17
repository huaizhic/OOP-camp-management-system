package com.example.cms.CSVConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Student.Student_User;
import com.example.cms.Suggestions.Suggestion;

public class CSVWriter {

    // Method to write user information to a CSV file
    public static void writeUserToCSV(Student_User student, boolean appendHeader) {
        String csvFilePath = "student.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
        	// If appendHeader is true, add the CSV header
            if (appendHeader) {
                writer.write("StudentID,Name,Password,UserGroup,Faculty,CampAccessibility,CampCommittee,RegisteredCamps,"
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
                    + student.getUserGroup() + ","
                    + student.getFaculty() + ","
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

    // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckCamp(ArrayList<Camp> arrayList) {
        if (arrayList != null) {
            return arrayList.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
    // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckEnquiry(ArrayList<Enquiry> arrayList) {
        if (arrayList != null) {
            return arrayList.toArray(new CharSequence[0]);
        } else {
            return new CharSequence[0];
        }
    }
    
 // Helper method to handle null check and conversion to array
    private static CharSequence[] toArrayWithNullCheckSuggestion(ArrayList<Suggestion> arrayList) {
        if (arrayList != null) {
            return arrayList.toArray(new CharSequence[0]);
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
    
    
}
