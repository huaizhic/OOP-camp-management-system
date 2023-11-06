package com.example.cms;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;
import java.util.Scanner;

public class attendee_Account extends student_Account {

    // Fields to store registered camps and other necessary data
    private ArrayList<Camp> registeredCamps;
    private ArrayList<Enquiry> inquiries;

    public attendee_Account(String studentID) {
        super(studentID);
        this.registeredCamps = new ArrayList<>();
        this.inquiries = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Student Home Page");
            System.out.println("1. Display Registered Camps");
            System.out.println("2. com.example.cms.Camp Information");
            System.out.println("3. Manage Enquiries");
            System.out.println("4. Book Camps");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    displayRegisteredCamps();
                    break;
                case 2:
                    viewCampInformation(scanner);
                    break;
                case 3:
                    manageEnquiries(scanner);
                    break;
                case 4:
                    bookCamps(scanner);
                    break;
                case 5:
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
                System.out.println("com.example.cms.Camp Name: " + camp.getCampName());
                System.out.println("Dates: " + camp.getDates());
            }
        }
    }

    public void viewCampInformation(Scanner scanner) {
        // Implement camp information retrieval and display here
    }

    public void manageEnquiries(Scanner scanner) {
        // Implement inquiry management (view, edit, delete) here
    }

    public void bookCamps(Scanner scanner) {
        // Implement camp booking process here
    }

    // Additional helper methods, e.g., to get the role for a specific camp
}
