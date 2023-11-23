package com.example.cms.Student;
import java.util.Map;
import java.util.Scanner;

public class Attendee_Account extends Student_Account {

    private String studentID;

    public Attendee_Account(String studentID, Map<String, Student_User> existingStudents) {
        super(studentID, existingStudents);
        this.studentID = studentID;
    }

    public void start(Attendee attendee) {
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
                	Attendee.displayRegisteredCamps(attendee);
                    break;
                case 2:
                    Attendee.manageCamp(attendee);
                    break;
                case 3:
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


}