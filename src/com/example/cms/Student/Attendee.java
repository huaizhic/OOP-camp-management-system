package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.camp_Test_Data.camp_Test_Data;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Student_Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Attendee extends Student_User {

	 private static Map<String, Attendee> attendeesMap = new HashMap<>();
	    private static ArrayList<Camp> registeredCamps;
	    private static List<String> campAccessibility;
	    private ArrayList<Enquiry> enquiries;

	    public Attendee() {
	        super();
	        this.registeredCamps = new ArrayList<>();
	        this.campAccessibility = new ArrayList<>();
	        this.enquiries = new ArrayList<>();
	        attendeesMap.put(this.getStudentID(), this);
	    }

	    public static Map<String, Attendee> getAttendeesMap() {
	        return attendeesMap;
	    }

	 // New method to display registered camps for a specific student
	    public static void displayRegisteredCamps(String studentId) {
	        if (attendeesMap.containsKey(studentId)) {
	            Attendee attendee = attendeesMap.get(studentId);
	            System.out.println("Registered Camps for Student ID " + studentId + ":");
	            if (attendee.registeredCamps.isEmpty()) {
	                System.out.println("This student is not registered for any camps.");
	            } else {
	            	for (Camp camp : attendee.registeredCamps) {
	            	    // Call the viewRegisteredCamp method for each registered camp
	            	    camp_Test_Data.viewRegisteredCamp(camp.getCampName());
	            	}

	            }
	        } else {
	            System.out.println("Student with ID " + studentId + " not found.");
	        }
	    }
	    

    public static void manageCamp(String studentID) {
        System.out.println("Camp Enquiries Menu:");
        System.out.println("1. View Camps");
        System.out.println("2. Register for a Camp");
        System.out.println("3. Withdraw from a Camp");
        System.out.println("4. Back to Main Menu");
        
        System.out.print("Enter your choice: ");
        Scanner scanner = null;
		int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Delegate to specific functionality in the Attendee class
                viewCamp(campAccessibility);
                break;
            case 2:
                // Delegate to specific functionality in the Attendee class
                registerForCamp(studentID);
                break;
            case 3:
                // Delegate to specific functionality in the Attendee class
                withdrawFromCamp(studentID);
                break;
            case 4:
                // Back to the main menu
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
    
   
    /**
     * View camps.
     */
    public static void viewCamp(List<String> campAccessibility) {
        System.out.println("Available Camps:");
        for (String campName : campAccessibility) {
            camp_Test_Data.viewCamp(campName);
        }
    }


    /**
     * View remaining slots for a specific camp.
     *
     * @param camp The camp for which to view remaining slots.
     * @return The number of remaining slots.
     */
    public int viewRemainingSlots(Camp camp) {
        // TODO - Implement logic to calculate and return remaining slots for the specified camp
        throw new UnsupportedOperationException();
    }

    /**
     * Register for a camp.
     *
     * @param camp The camp to register for.
     * @param role The role for registration.
     */
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
    /**
     * Withdraw from a registered camp.
     *
     * @param camp The camp to withdraw from.
     */
    public static void withdrawFromCamp(String studentID) {
        // Retrieve the withdrawn camp name
        String withdrawnCampName = camp_Test_Data.withdrawFromCamp(studentID);

        // Find the corresponding Camp object by name
        Camp withdrawnCamp = findCampByName(withdrawnCampName);

        if (withdrawnCamp != null) {
            // Remove the camp from the registeredCamps list
            registeredCamps.remove(withdrawnCamp);

            // Perform additional logic as needed

            System.out.println("Withdrawal successful from " + withdrawnCampName);
        } else {
            System.out.println("Camp not found with name: " + withdrawnCampName);
        }
    }

    // Method to find a Camp by name in the registeredCamps list
    private static Camp findCampByName(String campName) {
        for (Camp camp : registeredCamps) {
            if (camp.getCampName().equals(campName)) {
                return camp;
            }
        }
        return null; // Camp not found
    }


    /**
     * Submit an enquiry for a camp.
     *
     * @param camp    The camp related to the enquiry.
     * @param content The content of the enquiry.
     */
    public void submitEnquiry(Camp camp, String content) {
        Enquiry newEnquiry = new Enquiry(this.getStudentID(), this.getName(), content, "datePlaceholder", null, false);
        newEnquiry.createEnquiry(this.getStudentID(), this.getName());
        enquiries.add(newEnquiry);
    }

    /**
     * View enquiries related to a specific camp.
     *
     * @param camp The camp for which to view enquiries.
     */
    public void viewEnquiriesForCamp(Camp camp) {
        // TODO - Implement logic to retrieve and display enquiries for the specified camp
        throw new UnsupportedOperationException();
    }

    /**
     * Edit an existing enquiry.
     *
     * @param enquiryNo  The index of the enquiry to edit.
     * @param newMessage The new message content for the enquiry.
     */
    public void editEnquiry(int enquiryNo, String newMessage) {
        if (enquiryNo >= 0 && enquiryNo < enquiries.size()) {
            enquiries.get(enquiryNo).setContent(newMessage);
        } else {
            System.out.println("Invalid enquiry number.");
        }
    }

    /**
     * Delete an existing enquiry.
     *
     * @param enquiryNo The index of the enquiry to delete.
     */
    public void deleteEnquiry(int enquiryNo) {
        if (enquiryNo >= 0 && enquiryNo < enquiries.size()) {
            enquiries.remove(enquiryNo);
        } else {
            System.out.println("Invalid enquiry number.");
        }
    }
}
