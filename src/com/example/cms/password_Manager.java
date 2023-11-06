package com.example.cms;

import java.util.ArrayList;
import java.util.Scanner;

public class password_Manager {

    public static boolean checkPassword(String studentID, String roleChoice, String enteredPassword) {
        // Retrieve the student based on studentID
        StudentUser student_Information = StudentUser.getStudentById(studentID);

        // Check if the student exists and validate the password and roleChoice.
        if (student_Information != null) {
            // Assuming the roleChoice is stored in the user group.
            String studentRoleChoice = student_Information.getUserGroup();
            String storedPassword = student_Information.getPassword();

            if (enteredPassword.equals(storedPassword) && roleChoice.equals(studentRoleChoice)) {
                return true;
            }
        }

        return false;
    }




    public boolean updatePassword(String userID, String updatedPassword) {
        // Find the student based on the userID (you need to implement this part)
        StudentUser Student = StudentUser.getStudentById(userID);

        if (Student != null) {
            String oldPassword = Student.getPassword();

            // Check if the updatedPassword is different from the old password
            if (!updatedPassword.equals(oldPassword)) {
                // Check if the updatedPassword meets criteria (8 characters, alphanumeric, and mixed case)
                if (isValidPassword(updatedPassword)) {
                    // Assuming a successful password update
                    Student.setPassword(updatedPassword);

                    System.out.println("Password reset successful.");
                    // Return true to indicate success
                    return true;
                } else {
                    System.out.println("Password does not meet the criteria. Please make sure it has 8 characters, includes both upper and lower case letters, and is alphanumeric.");
                    // Return false to indicate that the password doesn't meet criteria
                    return false;
                }
            } else {
                System.out.println("New password is the same as the old one. Please choose a different password.");
                // Return false to indicate that the new password is the same as the old one
                return false;
            }
        } else {
            System.out.println("User not found. Password reset failed.");
            // Return false to indicate failure (user not found)
            return false;
        }
    }




    public void forgotPassword(String userID) {
        // First, retrieve the student based on the userID
        Scanner scanner = null;
        StudentUser Student = StudentUser.getStudentById(userID);

        if (Student != null) {
            System.out.println("Password recovery for " + StudentUser.getName(userID));

            // Get security questions and answers from the student
            ArrayList<String> securityQuestions = Student.getSecurityQuestion();
            ArrayList<String> securityAnswers = Student.getSecurityAns();

            // Maximum wrong attempts allowed per question
            int maxWrongAttempts = 2;
            int correct = 0;


            for (int i = 0; i < securityQuestions.size(); i++) {
                String securityQuestion = securityQuestions.get(i);
                System.out.println("Security Question: " + securityQuestion);
                System.out.print("Enter your answer: ");
                String userAnswer = scanner.next();

                int wrongAttempts = 0;

                // Allow a limited number of wrong attempts
                while (!userAnswer.equals(securityAnswers.get(i)) && wrongAttempts < maxWrongAttempts) {
                    wrongAttempts++;
                    System.out.println("Wrong answer. Attempts left: " + (maxWrongAttempts - wrongAttempts));
                    System.out.print("Try again: ");
                    userAnswer = scanner.next();
                }

                if (userAnswer.equals(securityAnswers.get(i))) {
                    System.out.println("Security question answered correctly.");
                    correct += 1;
                } else {
                    System.out.println("Max wrong attempts reached. Please wait before trying again.");
                    // Implement a waiting period (e.g., using Thread.sleep) before allowing another attempt.
                }
            }

            // If all security questions are answered correctly, allow the user to set a new password
            if (correct == 2) {
                boolean passwordResetSuccessful = false;

                while (!passwordResetSuccessful) {
                    System.out.print("Enter your new password or press '1' to cancel: ");
                    String newPassword = scanner.next();

                    if (newPassword.equals("1")) {
                        System.out.println("Password reset canceled.");
                        break; // Exit the loop if the user chooses to cancel
                    }

                    if (updatePassword(userID, newPassword)) {
                        System.out.println("Password reset successful. You can now log in with your new password.");
                        passwordResetSuccessful = true; // Exit the loop when the password meets the criteria
                    } else {
                        System.out.println("Password does not meet the criteria. Please try again.");
                    }
                }
            } else {
                System.out.println("User not found. Password reset failed.");
            }
        }

    }

    public static boolean isValidPassword(String password) {
        // Check if the password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains at least one digit
        boolean containsDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                containsDigit = true;
                break;
            }
        }

        // Check if the password contains both uppercase and lowercase characters
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                containsLowerCase = true;
            }
        }

        // Return true if all criteria are met
        return containsDigit && containsUpperCase && containsLowerCase;
    }


}
