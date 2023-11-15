package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;

import java.util.ArrayList;
import java.util.Scanner;

public class attendee_Account extends Student_Account {

    private ArrayList<Camp> registeredCamps;
    private ArrayList<Enquiry> enquiries;
    private String studentID;

    public attendee_Account(String studentID) {
        super(studentID);
        this.studentID = studentID;
        this.registeredCamps = new ArrayList<>();
        this.enquiries = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Student Home Page");
            System.out.println("1. Display Registered Camps");
            System.out.println("2. Manage Camp");
            System.out.println("3. Manage Enquiries");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                	Attendee.displayRegisteredCamps(studentID);
                    break;
                case 2:
                    Attendee.manageCamp(studentID);
                    break;
                case 3:
                    manageEnquiries(studentID);
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
}