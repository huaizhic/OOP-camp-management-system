package com.example.cms.user_Login;

import com.example.cms.CSVConverter.CSVDataManager;
import com.example.cms.Staff.Staff;
import com.example.cms.Staff.Staff_Account;
import com.example.cms.Student.Student_Account;
import com.example.cms.Student.Student_User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class account_Manager {
    private Scanner scanner;
    String userId = null;
    
    public account_Manager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to the Account Manager");

        int userTypeChoice = getUserTypeChoice();
        user_Registration user_Registration = new user_Registration(scanner);

        if (userTypeChoice == 2) {

            Staff currentStaff;
            CSVDataManager.loadStaffsFromCSV();

            boolean userFound = false;
            String staffID;

            do {
                staffID = getStaffId();
                System.out.println("This is the staff id entered: " + staffID);
                Staff_Account staffAccount = new Staff_Account();
                currentStaff = Staff.getStaffByID(staffID);

                if (currentStaff != null) {
                    staffAccount.start(currentStaff);
                    userFound = true;
                } else {
                    System.out.println("Staff not found.");
                }
            }while(!userFound);
        }

        else if (userTypeChoice == 1) {
                int accountChoice = getAccountChoice();

                if (accountChoice == 1) {
                    Student_User student_User = new Student_User();
                    CSVDataManager.loadStudentsFromCSV(student_User);

                    boolean userFound = false;

                    while (!userFound) {
                        userId = getStudentId();

                        System.out.println("This is the student id entered: " + userId);

                        Student_Account studentAccount = new Student_Account(userId, student_User.getExistingStudents());

                        if (studentAccount.getStudentAccount(userId)) {
                            studentAccount.start();
                            userFound = true;
                        } else {

                            System.out.println("Student not found.");

                            int retryChoice;
                            do {
                                retryChoice = getRetryChoice();

                                switch (retryChoice) {
                                    case 1:
                                        // Continue the loop
                                        break;
                                    case 2:

                                        user_Registration.start();
                                        userFound = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please enter 1 or 2.");
                                        break;
                                }
                            } while (retryChoice != 1 && retryChoice != 2);
                        }
                    }
                } else if (accountChoice == 0) {

                    user_Registration.start();
                } else {
                    System.out.println("Invalid choice. Please enter '1' for Yes or '0' for No.");
                }
            } else {
                System.out.println("Invalid user type. Please enter '1' for student or '2' for staff.");
            }
        }

    private int getUserTypeChoice() {
        int userTypeChoice = 0;

        while (true) {
            try {
                System.out.print("Are you a staff or a student? (Enter '1' for student or '2' for staff): ");
                String input = scanner.nextLine().trim(); // Read the input as a string and trim leading/trailing spaces
                
                // Check if the input is empty
                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    continue; // Restart the loop if the input is empty
                }

                userTypeChoice = Integer.parseInt(input); // Parse the input as an integer
                
                // Check if the input is within the valid range
                if (userTypeChoice == 1 || userTypeChoice == 2) {
                    break; // Exit the loop only if the input is valid
                } else {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return userTypeChoice;
    }

    private String getStaffId() {
        String specialCharacters = "[!@#$%^&*(),.?\":{}|<>]"; // Define a regex for special characters
        String staffId = null;
        
        while (true) {
            try {
                System.out.print("Enter your staff ID: ");
                staffId = scanner.nextLine();

                // Check if the input is empty
                if (staffId.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid staff ID.");
                    continue; // Restart the loop if the input is empty
                }

                // Check if the input contains special characters
                if (staffId.matches(".*" + specialCharacters + ".*")) {
                    System.out.println("Invalid input. Please enter a staff ID without special characters.");
                    continue; // Restart the loop if the input contains special characters
                }
                return staffId;
                 // Exit the loop if the input is not empty and doesn't contain special characters
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid staff ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }


    private int getAccountChoice() {
        int accountChoice = 0;

        while (true) {
            try {
                System.out.print("Do you have an existing account? (1 for Yes, 0 for No): ");
                String input = scanner.nextLine().trim(); // Read the input as a string and trim leading/trailing spaces

                // Check if the input is empty
                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please enter '1' or '0'.");
                    continue; // Restart the loop if the input is empty
                }

                accountChoice = Integer.parseInt(input); // Parse the input as an integer

                // Check if the input is within the valid range
                if (accountChoice == 0 || accountChoice == 1) {
                    break; // Exit the loop only if the input is valid
                } else {
                    System.out.println("Invalid input. Please enter '1' or '0'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return accountChoice;
    }


    private String getStudentId() {
        String studentId = null;
        String specialCharacters = "[!@#$%^&*(),.?\":{}|<>]"; // Define a regex for special characters

        while (true) {
            try {
                System.out.print("Enter your student ID: ");
                studentId = scanner.nextLine().toUpperCase().trim();

                // Check if the input is empty
                if (studentId.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid student ID.");
                    continue; // Restart the loop if the input is empty
                }

                // Check if the input contains special characters
                if (studentId.matches(".*" + specialCharacters + ".*")) {
                    System.out.println("Invalid input. Please enter a student ID without special characters.");
                    continue; // Restart the loop if the input contains special characters
                }

                break; // Exit the loop if the input is not empty and doesn't contain special characters
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid student ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return studentId;
    }


    private int getRetryChoice() {
        int retryChoice = 0;

        while (true) {
            try {
                System.out.println("1. Try again");
                System.out.println("2. Go to student registration");
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim(); // Read the input as a string and trim leading/trailing spaces

                // Check if the input is empty
                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                    continue; // Restart the loop if the input is empty
                }

                retryChoice = Integer.parseInt(input); // Parse the input as an integer

                // Check if the input is within the valid range
                if (retryChoice == 1 || retryChoice == 2) {
                    break; // Exit the loop only if the input is valid
                } else {
                    System.out.println("Invalid input. Please enter '1' or '2'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return retryChoice;
    }


    public static void main(String[] args) {
    	  Scanner scanner = new Scanner(System.in);
          account_Manager accountManager = new account_Manager(scanner);
          accountManager.start();
          scanner.close();
    }
}
