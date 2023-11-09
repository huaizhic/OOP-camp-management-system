import java.util.Scanner;
import student.StudentAccount;
import staff.StaffAccount;

public class AccountManager {
    private Scanner scanner;

    public AccountManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to the Account Manager");
        System.out.print("Are you a staff or a student? (Enter 'staff' or 'student'): ");
        String userType = scanner.next();

        if (userType.equalsIgnoreCase("staff")) {
            System.out.print("Enter your staff ID: ");
            String userId = scanner.next();
            StaffAccount staffAccount = new StaffAccount(scanner, userId);
            staffAccount.start();
        } else if (userType.equalsIgnoreCase("student")) {
            System.out.print("Enter your student ID: ");
            String userId = scanner.next();
            StudentAccount studentAccount = new StudentAccount(scanner, userId);
            studentAccount.start();
        } else {
            System.out.println("Invalid user type. Please enter 'staff' or 'student'.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager(scanner);
        accountManager.start();
        scanner.close();
    }
}
