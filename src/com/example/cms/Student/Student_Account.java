package com.example.cms.Student;

import com.example.cms.Student_Role;

import java.util.Map;
import java.util.Scanner;

public class Student_Account {
    protected String userId; // Add a field to hold the student ID
    private Scanner scanner;
    private Map<String, Student_User> existingStudents;

    public Student_Account(Map<String, Student_User> existingStudents) {
        this.existingStudents = existingStudents;
    }

    public boolean getStudentAccount(String userId) {
        // Use the existingStudents map to check if the student exists
        return existingStudents.containsKey(userId);
    }

    // Constructor to initialize the scanner
 // Constructor to initialize the scanner
    public Student_Account(String userId, Map<String, Student_User> existingStudents) {
        this.userId = userId;
        this.existingStudents = existingStudents;
        this.scanner = new Scanner(System.in); // Initialize the scanner here
    }
    
   

    // Factory method to create a Student_Account instance
//    public static boolean getStudentAccount(String userId) {
//        Student_User existingStudent = new Student_User();
//        // Check if the entered ID belongs to an existing student
//        System.out.println("This is the student id passed: " + userId);
//        if (existingStudent.isStudentExist(userId)) {
//        	System.out.println("Student exists");
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void start() {
        int maxAttempts = 3; // Maximum allowed password attempts
        int attempts = 0; // Counter for password attempts

        while (attempts < maxAttempts) {
            System.out.print("Enter your role (1 for Attendee or 2 for Committee): ");
            int roleChoice = scanner.nextInt();

            // Consume the newline character
            scanner.nextLine();

            Student_Role role = null;
            if (roleChoice == 1) {
                role = Student_Role.ATTENDEE;
            } else if (roleChoice == 2) {
                role = Student_Role.COMMITTEE;
            } else {
                System.out.println("Invalid role. Please enter '1' for Attendee or '2' for Committee.");
                continue; // Retry if the role is invalid.
            }

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();

            // Consume the newline character
            scanner.nextLine();

            // Debugging information
            System.out.println("Debugging - Before passwordManager.checkPassword");

            // Use the existingStudents map to retrieve the student information
            Student_User existingStudent = existingStudents.get(userId);
            //
            existingStudent.loadStudentsFromCSV();

            if (existingStudent != null) {
                Password_Manager passwordManager = new Password_Manager(scanner, existingStudent);

                // Debugging information
                System.out.println("Debugging - Before calling passwordManager.checkPassword");

                // Check the password and get the student's name
                System.out.println("Your password is, " + enteredPassword);
                if (passwordManager.checkPassword(userId, role, enteredPassword)) {
                    System.out.println("This is check password manager");
                    String studentName = existingStudent.getName();

                    if ("COMMITTEE".equals(role.name())) {
                        System.out.println("Welcome, Committee Member " + studentName);

                        // Redirect to the committee class here
                    } else if ("ATTENDEE".equals(role.name())) {
                        System.out.println("Welcome, Attendee " + studentName);
                        // Redirect to the attendee class here
                        
                        Attendee_Account attendee_Account = new Attendee_Account(userId, existingStudent.getExistingStudents());
						attendee_Account.start();
                    } else {
                        System.out.println("Invalid role. Please enter 'committee' or 'attendee'.");
                    }

                    // Successful login, reset the attempts counter
                    attempts = 0;
                    break;
                } else {
                    attempts++;

                    if (attempts == maxAttempts) {
                        System.out.print("Forgot password (1 for Yes, 0 for No): ");
                        int forgotPasswordChoice = scanner.nextInt();

                        if (forgotPasswordChoice == 1) {
                            // Implement the logic for password reset here
                            passwordManager.forgotPassword(userId);
                        }

                        break;
                    }
                }
            } else {
                System.out.println("User not found. Please enter a valid user ID.");
                break;
            }
        }
    }
}
