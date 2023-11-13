package com.example.cms.user_Login;

import java.util.Scanner;
import com.example.cms.staff_Account;
import com.example.cms.Student.Student_Account;

public class account_Manager {
    private Scanner scanner;

    public account_Manager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to the Account Manager");
        System.out.print("Are you a staff or a student? (Enter '1' for student or '2' for staff): ");
        int userTypeChoice = scanner.nextInt();

        if (userTypeChoice == 2) {
            System.out.print("Enter your staff ID: ");
            String userId = scanner.next();
           // staff_Account staffAccount = new staff_Account(scanner, userId);
           // staffAccount.start();
        } else if (userTypeChoice == 1) {
            System.out.print("Do you have an existing account? (1 for Yes, 0 for No): ");
            int accountChoice = scanner.nextInt();

            if (accountChoice == 1) {
                System.out.print("Enter your student ID: ");
                String userId = scanner.next();
                Student_Account studentAccount = new Student_Account(userId);
                studentAccount.start();
            } else if (accountChoice == 0) {
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
