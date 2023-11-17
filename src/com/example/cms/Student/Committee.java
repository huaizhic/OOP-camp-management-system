package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.camp_Test_Data.camp_Test_Data;
import com.example.cms.Enquiries.Enquiry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Committee extends Student_User {

    private static Map<String, Committee_Member> committeeMembersMap = new HashMap<>();

    public Committee(String studentID) {
        super();
        committeeMembersMap.put(studentID, (Committee_Member) this);
    }

    public static Map<String, Committee_Member> getCommitteeMembersMap() {
        return committeeMembersMap;
    }

    public static void manageCamp(String studentId) {
        System.out.println("Camp Management Menu:");
        System.out.println("1. View Camps");
        System.out.println("2. Register for a Camp");
        System.out.println("3. Generate List from a Camp");
        System.out.println("4. Back to Main Menu");

        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewCamp(campAccessibility);
                break;
            case 2:
                registerForCamp(studentId);
                break;
            case 3:
                camp_Test_Data.generate_List(studentId);
                break;
            case 4:
                Committee_Account.Start();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    public static void viewCamp(List<String> campAccessibility) {
        System.out.println("Available Camps:");
        for (String campName : campAccessibility) {
            camp_Test_Data.viewCamp(campName);
        }
    }

    public static void registerForCamp(String studentID) {
        // TODO - Implement camp registration logic 
        // This function will see the viewOpenCamp, so that students can still see which camp is still open or vacant
        // Then, they will have the register option, if the camp clashes with the current date of their registered camps then it won't be able to bookk it 
        camp_Test_Data registeredCamp = camp_Test_Data.registerForCamp(registeredCamps, studentID);
        
        // Assuming you have a method to convert camp_Test_Data to Camp
        Camp convertedCamp = convertToCamp(registeredCamp);
        
        registeredCamps.add(convertedCamp);
    }
    
    // Example conversion function
    private static Camp convertToCamp(camp_Test_Data campData) {
        // Create a new Camp object and set its properties based on camp_Test_Data
        Camp camp = new Camp();
        camp.setCampName(campData.getcamp_Test_DataName());
        // Set other properties...

        return camp;
    }



    
    
/*************************FOR MANAGE ENQUIRIES**********************************/

    public static void manageEnquiries(String studentID) {
        System.out.println("Enquiries Management Menu:");
        System.out.println("1. View Enquiries");
        System.out.println("2. Make Enquiries");
        System.out.println("3. Back to Main Menu");

        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Enquiry.viewRelatedEnquiries(studentID);
                break;
            case 2:
                Enquiry.answerEnquiries(studentID);
                break;
            case 3:
            	// Back to the com.example.cms.main menu
            	Committee_Account.Start();
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    
 /*************************************FOR SUGGESTIONS****************************************/

    public static void manageSuggestions(String studentID) {
        System.out.println("Suggestions Management Menu:");
        System.out.println("1. View Suggestions");
        System.out.println("2. Submit Suggestions");
        System.out.println("3. Edit Suggestions");
        System.out.println("4. Delete Suggestions");
        System.out.println("5. Back to Main Menu");

        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //viewSuggestions();
                break;
            case 2:
                //submitSuggestion();
                break;
            case 3:
                //editSuggestion();
                break;
            case 4:
                //deleteSuggestion();
                break;
            case 5:
                // Back to the com.example.cms.main menu
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    public void viewSuggestions() {
        // Implement code to view suggestions
    }

    public void submitSuggestion() {
        // Implement code for submitting suggestions
    }

    public void editSuggestion() {
        // Implement code for editing suggestions
    }

    public void deleteSuggestion() {
        // Implement code for deleting suggestions
    }

    public void generateReport() {
        System.out.println("Generate Report Menu:");
        System.out.println("1. View Camp Reports");
        System.out.println("2. View Student Reports");
        System.out.println("3. Back to Main Menu");

        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewCampReports();
                break;
            case 2:
                viewStudentReports();
                break;
            case 3:
                // Back to the com.example.cms.main menu
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }

    public void viewCampReports() {
        // Implement code to view camp reports
    }

    public void viewStudentReports() {
        // Implement code to view student reports
    }
}
