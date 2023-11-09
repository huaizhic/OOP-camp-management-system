package com.example.cms.user_Login;

import java.util.Scanner;
import com.example.cms.Staff.staff_Account;
import com.example.cms.Student.student_Account;

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
            staff_Account staffAccount = new staff_Account(scanner, userId);
            staffAccount.start();
        } else if (userTypeChoice == 1) {
            System.out.print("Enter your student ID: ");
            String userId = scanner.next();
            student_Account studentAccount = new student_Account(scanner, userId);
            studentAccount.start();
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
