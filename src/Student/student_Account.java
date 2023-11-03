package Student;

import java.util.Scanner;
import user_Login.account_Manager;

public class student_Account extends account_Manager {
    private Student student; // Add a field to hold the Student object
    private Scanner scanner;
    private PasswordManager passwordManager; // Add a PasswordManager field

    public student_Account(Scanner scanner, Student student, PasswordManager passwordManager) {
        super(scanner);
        this.student = student;
        this.scanner = scanner; // Initialize the scanner
        this.passwordManager = passwordManager; // Initialize the PasswordManager
    }


	public void start() {
        System.out.println("Welcome, Student " + student.getName());
        System.out.print("Enter your role (1 for Attendee or 2 for Committee): ");
        int roleChoice = scanner.nextInt();

        if (roleChoice == 1 && !student.getCampCommittee()) {
            // Check if it's an attendee and they have the correct password
            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();
            if (enteredPassword.equals(student.getPassword())) {
                System.out.println("You are an Attendee. You can register for camps.");
                // Redirect to the attendee class here
            } else {
                System.out.println("Invalid password.");
            }
        } else if (roleChoice == 2 && student.getCampCommittee()) {
            // Check if it's a committee member and they have the correct password
            System.out.print("Enter your password: ");
            String enteredPassword = scanner.next();
            if (enteredPassword.equals(student.getPassword())) {
                System.out.println("You are a Committee member. You can help manage camps.");
                // Redirect to the committee class here
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("Invalid role or you do not have the required role. Please enter '1' for Attendee or '2' for Committee.");
        }
    }
}
