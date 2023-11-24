package com.example.cms.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.cms.CSVConverter.CSVDataManager;

public class Attendee_Account extends Student_Account {

    private String studentID;
    private Map<String, Attendee> existingAttendees;

    public Attendee_Account(String studentID, Map<String, Student_User> existingAttendees) {
        super(studentID, existingAttendees);
        this.studentID = studentID;
        
        // Create a new map and copy elements from existingAttendees
        this.existingAttendees = new HashMap<>();
        for (Map.Entry<String, Student_User> entry : existingAttendees.entrySet()) {
            if (entry.getValue() instanceof Attendee) {
                this.existingAttendees.put(entry.getKey(), (Attendee) entry.getValue());
            }
            // If you want to handle the case where an element is not an Attendee, add appropriate logic here
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        Attendee attendee = (Attendee) existingAttendees.get(studentID);
        CSVDataManager.loadAttendeeFromCSV(attendee);

        while (running) {
            System.out.println("Student Home Page");
            System.out.println("1. Display Registered Camps");
            System.out.println("2. Manage Camp");
            System.out.println("3. Manage Enquiries");
            System.out.println("4. Logout");

            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    // Use the Attendee class method to display registered camps
                    Attendee.displayRegisteredCamps(attendee);
                    break;
                case 2:
                    // Use the Attendee class method to manage camps
                    Attendee.manageCamp(attendee);
                    break;
                case 3:
                    // Use the Attendee class method to manage enquiries
                    Attendee.manageEnquiries(attendee);
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
    
    public static int getUserChoice(Scanner scanner) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                String inputLine = scanner.nextLine().trim();

                if (inputLine.isEmpty()) {
                    System.out.println("Invalid input. Please enter a number.");
                } else {
                    choice = Integer.parseInt(inputLine);
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

}
