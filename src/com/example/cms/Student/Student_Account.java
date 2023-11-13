package com.example.cms.Student;

import java.util.Scanner;

//import camp_Application_Management_System.Student;


public class Student_Account {
  private String userId; // Add a field to hold the student ID
  private Scanner scanner;

  public student_Account(String userId) {
      this.userId = userId;
  }

  public void start() {
      int maxAttempts = 3; // Maximum allowed password attempts
      int attempts = 0; // Counter for password attempts

      while (attempts < maxAttempts) {
          System.out.print("Enter your role (1 for Attendee or 2 for Committee): ");
          int roleChoice = scanner.nextInt();

          String role;
          if (roleChoice == 1) {
              role = "Attendee";
          } else if (roleChoice == 2) {
              role = "Committee";
          } else {
              System.out.println("Invalid role. Please enter '1' for Attendee or '2' for Committee.");
              continue; // Retry if the role is invalid.
          }

          System.out.print("Enter your password: ");
          String enteredPassword = scanner.next();

          password_Manager passwordManager = new password_Manager(); // Create an instance

          // Check the password and get the student's name
          if (passwordManager.checkPassword(userId, role, enteredPassword)) {
              String studentName = student_User.getName(userId);

              if ("Committee".equals(role)) {
                  System.out.println("Welcome, Committee Member " + studentName);

                  // Redirect to the committee class here
              } else if ("Attendee".equals(role)) {
                  System.out.println("Welcome, Attendee " + studentName);
                  // Redirect to the attendee class here
              } else {
                  System.out.println("Invalid role. Please enter 'committee' or 'attendee'.");
              }

              // Successful login, reset the attempts counter
              attempts = 0;
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
      }
  }




}
