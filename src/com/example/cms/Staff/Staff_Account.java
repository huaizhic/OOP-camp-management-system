package com.example.cms.Staff;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Staff_Account{

    public void start(Staff staff){
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3; // Maximum allowed password attempts
        int remainingAttempts = 3; // Counter for password attempts
        int roleChoice = 0; // Initialize to an invalid value

        System.out.println("Welcome," + staff.getName());

        while (remainingAttempts > 0) {

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();

            // Consume the newline character
            scanner.nextLine();
            if(enteredPassword.equals(staff.getPassword())){
                System.out.println("Verification completed");
                remainingAttempts = 3;
                break;
            }else{
                System.out.println("Verification failed, remaining attempts: " + remainingAttempts);
            }
            remainingAttempts --;
            }

            // Debugging information
            //System.out.println("Debugging - Before passwordManager.checkPassword");

            // Use the existingStudents map to retrieve the student information


           // Password_Manager passwordManager = new Password_Manager(scanner, existingStudent);

            // Debugging information
            // System.out.println("Debugging - Before calling passwordManager.checkPassword");

            // Check the password and get the student's name
            //System.out.println("Your password is, " + enteredPassword);
           /* if (passwordManager.checkPassword(userId, role, enteredPassword)) {
                System.out.println("This is check password manager");
                String studentName = existingStudent.getName();

                if ("COMMITTEE".equals(role.name())) {
                    System.out.println("Welcome, Committee Member " + studentName);
                    Committee_Account committee_Account = new Committee_Account(userId, existingStudent.getExistingStudents());
                    committee_Account.start();
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
*/
            Scanner input = new Scanner(System.in);
        boolean exit = false;

        do{
            System.out.println("Staff Home Page");
            System.out.println("1. Manage Camp");
            System.out.println("2. Manage Enquiries");
            System.out.println("3. Manage Suggestions");
            System.out.println("4. Logout");

            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

            switch (choice) {
                case 1:
                    manageCamp(staff);

                    break;
                case 2:
                    manageEnquiry(staff);
                    // Use the Attendee class method to manage camps

                    break;
                case 3:
                    // Use the Attendee class method to manage enquiries
                    manageSuggestion(staff);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        }while(!exit);
    }

    private void manageCamp(Staff staff){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Camp Management Menu:");
            System.out.println("1. Create New Camp");
            System.out.println("2. Edit Exiting Camp");
            System.out.println("3. Delete Existing Camp");
            System.out.println("4. View All Camp");
            System.out.println("5. View Camp Created");
            System.out.println("6. Back To the Main Page");

            System.out.print("Enter your choice: ");
            Scanner scanner = null;
            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);

            switch (choice) {
                case (1):
                    // Delegate to specific functionality in the Attendee class
                    staff.createCamp();
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    staff.editCamp(staff);
                    break;
                case (3):
                    staff.deleteCamp(staff);
                    break;
                case (4):
                    staff.viewAllCamps();
                    break;
                case (5):
                    staff.viewCampCreated(staff);
                case(6):
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }while(!exit);
    }

    private void manageEnquiry(Staff staff){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Enquiry Management Menu:");
            System.out.println("1. View enquiry");
            System.out.println("2. Reply enquiry");
            System.out.println("3. Back To the Main Page");
            System.out.print("Enter your choice: ");
            Scanner scanner = null;
            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);

            switch (choice) {
                case (1):
                    // Delegate to specific functionality in the Attendee class
                    staff.viewEnquiry(staff);
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    staff.replyEnquiry(staff);
                    break;
                case(3):
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }while(!exit);
    }

    private void manageSuggestion(Staff staff) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Suggestion Management Menu:");
            System.out.println("1. View Suggestions");
            System.out.println("2. Approve Suggestions");
            System.out.println("3. Back To the Main Page");
            System.out.print("Enter your choice: ");
            Scanner scanner = null;
            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    input.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);

            switch (choice) {
                case (1):
                    // Delegate to specific functionality in the Attendee class
                    staff.viewSuggestion(staff);
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    staff.approveSuggestion(staff);
                    break;
                case (3):
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (!exit);
    }
}
