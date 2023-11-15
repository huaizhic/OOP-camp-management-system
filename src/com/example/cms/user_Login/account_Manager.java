package com.example.cms.user_Login;

import java.util.Scanner;
import com.example.cms.staff_Account;
import com.example.cms.Student.Student_Account;

public class account_Manager {
    private Scanner scanner;
    String userId = null;
    
    public account_Manager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to the Account Manager");
        System.out.print("Are you a staff or a student? (Enter '1' for student or '2' for staff): ");
        int userTypeChoice = scanner.nextInt();

        if (userTypeChoice == 2) {
            System.out.print("Enter your staff ID: ");
            userId = scanner.next();
           // staff_Account staffAccount = new staff_Account(scanner, userId);
           // staffAccount.start();
        } else if (userTypeChoice == 1) {
            System.out.print("Do you have an existing account? (1 for Yes, 0 for No): ");
            int accountChoice = scanner.nextInt();

            if (accountChoice == 1) {
            	
            	boolean userFound = false;

            	while (!userFound) {
            	    System.out.print("Enter your student ID: ");
            	    userId = scanner.next();

            	    // Check if the entered ID belongs to an existing student
            	    Student_Account studentAccount = new Student_Account(userId);

            	    if (Student_Account.getStudentAccount(userId)!= null) {
            	        // Existing student found, start the student account
            	        studentAccount.start();
            	        userFound = true; // Set the flag to exit the loop
            	    } else {
            	        // Student not found, give options to try again or go to student registration
            	        System.out.println("Student not found.");

            	        int retryChoice;
            	        do {
            	            System.out.println("1. Try again");
            	            System.out.println("2. Go to student registration");
            	            System.out.print("Enter your choice: ");
            	            retryChoice = scanner.nextInt();

            	            switch (retryChoice) {
            	                case 1:
            	                    // Continue the loop
            	                    break;
            	                case 2:
            	                    // Go to student registration (implement this)
            	                    user_Registration.registerNewUser(scanner);
            	                    userFound = true; // Set the flag to exit the loop
            	                    break;
            	                default:
            	                    System.out.println("Invalid choice. Please enter 1 or 2.");
            	                    break;
            	            }
            	        } while (retryChoice != 1 && retryChoice != 2);
            	    }
            	}
            }
            
 else if (accountChoice == 0) {
                // Redirect to the registration process (you need to implement this part)
                user_Registration.registerNewUser(scanner);
            } else {
                System.out.println("Invalid choice. Please enter '1' for Yes or '0' for No.");
            }
        } else {
            System.out.println("Invalid user type. Please enter '1' for student or '2' for staff.");
        }
    }

    public static void main(String[] args) {
    	  Scanner scanner = new Scanner(System.in);
          account_Manager accountManager = new account_Manager(scanner);
          accountManager.start();
          scanner.close();
    }
}
