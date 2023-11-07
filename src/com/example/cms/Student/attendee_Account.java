package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;

import java.util.ArrayList;
import java.util.Scanner;

public class attendee_Account extends student_Account {

	 // Fields to store registered camps and other necessary data
    private ArrayList<Camp> registeredCamps;
    private ArrayList<Enquiry> enquiries;
    private String studentID; // Add an instance variable to store the studentID

    public attendee_Account(String studentID) {
        super(studentID);
        this.studentID = studentID; // Initialize the studentID instance variable
        this.registeredCamps = new ArrayList<>();
        this.enquiries = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Student Home Page");
            System.out.println("1. Display Registered Camps");
            System.out.println("2. Camp Enquiries");
            System.out.println("3. Manage Enquiries");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    displayRegisteredCamps();
                    break;
                case 2:
                    campEnquiries();
                    break;
                case 3:
                    manageEnquiries(studentID); // Call manageEnquiries and pass the student ID
                    break;
                case 4:
                    running = false;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public void displayRegisteredCamps() {
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

    public void campEnquiries() {
        // Implement camp information retrieval and display here
    	// I want this to be able to book, and withdraw from camps
    	// I also want this function to be able to see available open camps 
    	// However by default, student are only able to see their camps based on faculty
    	// However, to see the rest of the camps outside of the faculty the visibility would need to be toggled 
    }

    public void manageEnquiries(String studentID) {
        // Create an instance of the Enquiry class and pass the student ID
        Enquiry enquiry = new Enquiry(studentID, "", "", "", "", false);
        enquiry.setStudentName(studentID);
        enquiry.start();
    }

    public void bookCamps(Scanner scanner) {
        // Implement camp booking process here
    }

    // Additional helper methods, e.g., to get the role for a specific camp
}
