package com.example.cms.Student;

import java.util.Scanner;

public class attendee_Account extends student_Account {

    public attendee_Account(String studentID) {
        super(studentID);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    // Delegate to specific functionality in the Attendee class
                    Attendee.viewRegisteredCamps();
                    break;
                case 2:
                    // Delegate to specific functionality in the Attendee class
                    Attendee.viewCampEnquiries();
                    break;
                case 3:
                    // Delegate to specific functionality in the Attendee class
                    manageEnquiries();
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

    private void displayMenu() {
        System.out.println("Student Home Page");
        System.out.println("1. Display Registered Camps");
        System.out.println("2. Camp Enquiries");
        System.out.println("3. Manage Enquiries");
        System.out.println("4. Logout");
    }

    private int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

   
    // Additional methods for specific functionalities
    private void viewCamps() {
        // Implement camp information retrieval and display here
    }

    private void registerForCamp() {
        // Implement camp registration process here
    }

    private void withdrawFromCamp() {
        // Implement camp withdrawal process here
    }
}
