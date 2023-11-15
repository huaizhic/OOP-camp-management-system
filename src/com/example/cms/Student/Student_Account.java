package com.example.cms.Student;

import java.util.Scanner;

import com.example.cms.Student_Role;

public class Student_Account {
    private String userId; // Add a field to hold the student ID
    private Scanner scanner;

    // Constructor to initialize the scanner
    public Student_Account(String userId) {
        this.userId = userId;
        this.scanner = new Scanner(System.in);
    }

    // Factory method to create a Student_Account instance
    public static Student_Account getStudentAccount(String userId) {
        // Check if the entered ID belongs to an existing student
        if (Student_User.isStudentExist(userId)) {
            return new Student_Account(userId);
        } else {
            return null;
        }
    }

    public void start() {
        int maxAttempts = 3; // Maximum allowed password attempts
        int attempts = 0; // Counter for password attempts

        while (attempts < maxAttempts) {
            System.out.print("Enter your role (1 for Attendee or 2 for Committee): ");
            int roleChoice = scanner.nextInt();

            Student_Role role = null;
            if (roleChoice == 1) {
                role = Student_Role.Attendee;
            } else if (roleChoice == 2) {
                role = Student_Role.Committee;
            } else {
                System.out.println("Invalid role. Please enter '1' for Attendee or '2' for Committee.");
                continue; // Retry if the role is invalid.
            }

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();

            Password_Manager passwordManager = new Password_Manager(scanner); // Create an instance

            // Check the password and get the student's name
            if (passwordManager.checkPassword(userId, role, enteredPassword)) {
                String studentName = Student_User.getName(userId);

                if ("Committee".equals(role.name())) {
                    System.out.println("Welcome, Committee Member " + studentName);

                    // Redirect to the committee class here
                } else if ("Attendee".equals(role.name())) {
                    System.out.println("Welcome, Attendee " + studentName);
                    // Redirect to the attendee class here
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
        }
    }
}
