package com.example.cms.Staff;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * When a Staff logs in, they will first see the menu provided by this class.
 */
public class Staff_Account {
	
    protected void start(Staff existingStaffMember){
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
                    manageCamp(existingStaffMember);

                    break;
                case 2:
                    manageEnquiry(existingStaffMember);
                    // Use the Attendee class method to manage camps

                    break;
                case 3:
                    // Use the Attendee class method to manage enquiries
                    manageSuggestion(existingStaffMember);
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

    private void manageCamp(Staff existingStaffMember){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Camp Management Menu:");
            System.out.println("1. Create New Camp");
            System.out.println("2. Edit Exiting Camp");
            System.out.println("3. Delete Existing Camp");
            System.out.println("4. View All Camp");
            System.out.println("5. View Camp Created");
            System.out.println("6. Generate Camp Report");
            System.out.println("7. Back To the Main Page");

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
                    existingStaffMember.createCamp();
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    existingStaffMember.editCamp(existingStaffMember);
                    break;
                case (3):
                    existingStaffMember.deleteCamp(existingStaffMember);
                    break;
                case (4):
                    existingStaffMember.viewAllCamps();
                    break;
                case (5):
                    existingStaffMember.viewCampCreated(existingStaffMember);
                case(6):
                    existingStaffMember.generateReport(existingStaffMember);
                case(7):
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }while(!exit);
    }

    private void manageEnquiry(Staff existingStaffMember){
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
                    existingStaffMember.viewEnquiry(existingStaffMember);
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    existingStaffMember.replyEnquiry(existingStaffMember);
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

    private void manageSuggestion(Staff existingStaffMember) {
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
                    existingStaffMember.viewSuggestion(existingStaffMember);
                    break;
                case (2):
                    // Delegate to specific functionality in the Attendee class
                    existingStaffMember.approveSuggestion(existingStaffMember);
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
