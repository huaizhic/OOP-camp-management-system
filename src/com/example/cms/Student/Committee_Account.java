package com.example.cms.Student;

import java.util.Scanner;

public class Committee_Account extends Student_Account {

    public Committee_Account(String userId) {
        super(userId);
    }

    public static void Start(Committee committee) {
        boolean exit = false;

        while (!exit) {
            // Display menu options for camp committee members
            System.out.println("Camp Committee Member Options:");
            System.out.println("1. Manage Camps including generating reports");
            System.out.println("2. Manage Suggestions");
            System.out.println("3. Manage Enquiries");
            System.out.println("4. Logout");

            // Read user's choice
            int choice = getUserChoice();

            // Perform actions based on user's choice
            switch (choice) {
                case 1:
                    Committee.manageCamp(committee);
                    break;
                case 2:
                    Committee.manageSuggestions(committee);
                    break;
                case 3:
                    Committee.manageEnquiries(committee);
                    break;
                case 4:
                	exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

 // Utility method to get the user's choice
    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        return choice;
    }
}
