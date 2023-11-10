package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.RegisterRole;

import java.util.ArrayList;
import java.util.Scanner;

public class Attendee extends student_User {

    // Fields to store registered camps and other necessary data
    private static ArrayList<Camp> registeredCamps;
    private static ArrayList<Camp> campAccessibility;
    private ArrayList<Enquiry> enquiries;

    /**
     * Constructor for the Attendee class.
     */
    public Attendee() {
        // Call the constructor of the superclass (student_User)
        super();
        this.registeredCamps = new ArrayList<>();
        this.campAccessibility =  new ArrayList<>();
        this.enquiries = new ArrayList<>();
    }

    /**
     * View registered camps for the attendee.
     */
    public static void viewRegisteredCamps() {
        System.out.println("Registered Camps:");
        if (registeredCamps.isEmpty()) {
            System.out.println("You are not registered for any camps.");
        } else {
            for (Camp camp : registeredCamps) {
                System.out.println("Camp Name: " + camp.getCampName());
                System.out.println("Dates: " + camp.getCampDates());
            }
        }
    }
    
    public static void viewCampEnquiries() {
        System.out.println("Camp Enquiries Menu:");
        System.out.println("1. View Camps");
        System.out.println("2. Register for a Camp");
        System.out.println("3. Withdraw from a Camp");
        System.out.println("4. Back to Main Menu");
        
        System.out.print("Enter your choice: ");
        Scanner scanner;
		int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Delegate to specific functionality in the Attendee class
                viewCamps();
                break;
            case 2:
                // Delegate to specific functionality in the Attendee class
                registerForCamp();
                break;
            case 3:
                // Delegate to specific functionality in the Attendee class
                withdrawFromCamp();
                break;
            case 4:
                // Back to the main menu
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
    
    public static void viewCamps() {
        System.out.println("Available Camps:");
        for (Camp camp : campAccessibility) {
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Dates: " + camp.getCampDates());
            // This must show the number of vacancies as well
        }
    }

    /**
     * View open camps.
     */
    public ArrayList<Camp> viewOpenCamps() {
        // TODO - Implement logic to retrieve and display open camps
        throw new UnsupportedOperationException();
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
    public void registerForCamp(Camp camp, RegisterRole role) {
        // TODO - Implement camp registration logic 
    	// This function will see the viewOpenCamp, so that students can still see which camp is still open or vacant
    	// Then, they will have the register option, if the camp clashes with the current date of their registered camps then it won't be able to bookk it 
        throw new UnsupportedOperationException();
    }

    /**
     * Withdraw from a registered camp.
     *
     * @param camp The camp to withdraw from.
     */
    public void withdrawFromCamp(Camp camp) {
        // TODO - Implement camp withdrawal logic
    	// Show the lists of registeredCamps, have the option to withdraw from it 
        throw new UnsupportedOperationException();
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
