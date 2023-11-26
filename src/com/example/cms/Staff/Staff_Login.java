package com.example.cms.Staff;

import com.example.cms.CSVConverter.CSVDataManager;
import com.example.cms.Password.Password_Manager;
import com.example.cms.Password.Password_Manager_Staff;
import com.example.cms.Faculty;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Staff_Login {
    private String staffID;
    private Scanner scanner;
    private Staff staff;
    private Map<String, Staff_User> existingStaff;

    public Staff_Login(String staffID, Map<String, Staff_User> existingStaff) {
        this.staffID = staffID;
        this.existingStaff = existingStaff;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int maxAttempts = 3;
        int attempts = 0;
        CSVDataManager.loadStaffFromCSV();   
        while (attempts < maxAttempts) {
            try {
                System.out.print("Enter your password: ");
                String enteredPassword = scanner.next();

                // Consume the newline character
                scanner.nextLine();

                if (enteredPassword.isEmpty()) {
                    throw new InputMismatchException("Password cannot be empty.");
                }
                Staff_User existingStaffMember = Staff_User.getExistingStaff().get(staffID);
                
                // Debugging information
                System.out.println("Debugging - Before passwordManager.checkPassword");

                if (existingStaffMember != null) {
                    Password_Manager_Staff passwordManager = new Password_Manager_Staff(scanner, existingStaffMember);

                    // Debugging information
                    // System.out.println("Debugging - Before calling passwordManager.checkPassword");

                    // Check the password
                    System.out.println("Your password is, " + enteredPassword);
                    if (passwordManager.checkPassword(staffID, enteredPassword)) {
                        System.out.println("Successful login. Welcome, " + existingStaffMember.getName());
                        
                        Staff_Account staff_Account = new Staff_Account();
						staff_Account.start(staff);

                        // Reset the attempts counter
                        attempts = 0;
                        // You can add logic here to redirect to staff-specific functionality
                        // For example, creating an instance of Staff_Account and calling its start() method.
                        // Staff_Account staffAccount = new Staff_Account(staffID, existingStaff);
                        // staffAccount.start();
                        break;
                    } else {
                        attempts++;

                        if (attempts == maxAttempts) {
                            System.out.print("Forgot password (1 for Yes, 0 for No): ");
                            int forgotPasswordChoice = scanner.nextInt();

                            if (forgotPasswordChoice == 1) {
                                // Implement the logic for password reset here
                                passwordManager.forgotPassword(staffID);
                            }

                            break;
                        }
                    }
                } else {
                    System.out.println("Staff member not found. Please enter a valid staff ID.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. " + e.getMessage());
                // Consume the invalid input
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please enter a password.");
                // Consume the invalid input
                scanner.nextLine();
            }
        }
    }
}
