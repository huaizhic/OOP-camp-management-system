package user_Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Student.password_Manager;
import Student.student;

public class user_Registration {
	public static void registerNewUser(Scanner scanner) {
	    // Collect user information for registration
	    System.out.println("Student Registration:");

	    // Collect basic information
	    System.out.print("Enter your student ID: ");
	    String studentID = scanner.next();

	    // Check if the student already exists
	    student existingStudent = student.getStudentById(studentID);
	    if (existingStudent != null) {
	        System.out.println("This student already has an account.");
	        return;
	    }

	    System.out.print("Enter your name: ");
	    String name = scanner.next();

	    // Collect user group
	    System.out.print("Enter your user group (1 for Attendee, 2 for Committee): ");
	    String userGroup = null;
	    boolean confirmed = false;

	    while (!confirmed) {
	        int userGroupChoice = scanner.nextInt();

	        if (userGroupChoice == 1) {
	            userGroup = "Attendee";
	            System.out.println("You have selected 'Attendee'. Is this correct? (1 for Yes, 2 for No): ");
	            int confirmationChoice = scanner.nextInt();

	            if (confirmationChoice == 1) {
	                confirmed = true; // User confirmed the choice, exit the loop.
	            } else if (confirmationChoice == 2) {
	                System.out.print("Please re-select your user group (1 for Attendee, 2 for Committee): ");
	            } else {
	                System.out.println("Invalid confirmation choice. Please enter '1' for Yes or '2' for No.");
	            }
	        } else if (userGroupChoice == 2) {
	            userGroup = "Committee";
	            System.out.println("You have selected 'Committee'. Is this correct? (1 for Yes, 2 for No): ");
	            int confirmationChoice = scanner.nextInt();

	            if (confirmationChoice == 1) {
	                confirmed = true; // User confirmed the choice, exit the loop.
	            } else if (confirmationChoice == 2) {
	                System.out.print("Please re-select your user group (1 for Attendee, 2 for Committee): ");
	            } else {
	                System.out.println("Invalid confirmation choice. Please enter '1' for Yes or '2' for No.");
	            }
	        } else {
	            System.out.println("Invalid user group choice. Please enter '1' for Attendee or '2' for Committee.");
	        }
	    }

	    String password = null;

	    while (true) {
	        System.out.print("Create a password: ");
	        password = scanner.next();

	        if (password_Manager.isValidPassword(password)) {
	            break; // Exit the loop if the password is valid
	        } else {
	            System.out.println("Password does not meet the criteria. Please make sure it has 8 characters, includes both upper and lower case letters, and is alphanumeric.");
	        }
	    }

	    while (true) {
	        // Confirm the password
	        System.out.print("Confirm your password: ");
	        String confirmPassword = scanner.next();

	        if (password.equals(confirmPassword)) {
	            break; // Exit the confirmation loop if the passwords match
	        } else {
	            System.out.println("Password confirmation doesn't match. Please try again.");
	            System.out.println("Enter '1' to create a new password or any other key to try again: ");
	            String choice = scanner.next();
	            if (!choice.equals("1")) {
	                continue; // Re-enter the confirmation password
	            } else {
	                // User wants to create a new password
	                while (true) {
	                    System.out.print("Create a new password: ");
	                    password = scanner.next();
	                    if (password_Manager.isValidPassword(password)) {
	                        break; // Exit the loop if the new password is valid
	                    } else {
	                        System.out.println("Password does not meet the criteria. Please make sure it has 8 characters, includes both upper and lower case letters, and is alphanumeric.");
	                    }
	                }
	            }
	        }
	    }

	    // Proceed with the rest of the registration process
	    // ...

	    int numSecurityQuestions = 3;

	    List<String> securityQuestions = new ArrayList<>();
	    List<String> securityAnswers = new ArrayList<>();

	    confirmed = false;

	    for (int i = 1; i <= numSecurityQuestions; i++) {
	        System.out.print("Enter your security question " + i + ": ");
	        String question = scanner.next();
	        securityQuestions.add(question);

	        System.out.print("Enter your answer to the question '" + question + "': ");
	        String answer = scanner.next();
	        securityAnswers.add(answer);
	    }

	    while (!confirmed) {
	        // Display the collected security questions and answers for confirmation
	        System.out.println("Please confirm your security questions and answers:");
	        for (int i = 0; i < numSecurityQuestions; i++) {
	            System.out.println("Question " + (i + 1) + ": " + securityQuestions.get(i));
	            System.out.println("Answer " + (i + 1) + ": " + securityAnswers.get(i));
	        }

	        System.out.print("Is everything correct? (1 for Yes, 2 for No): ");
	        int confirmationChoice = scanner.nextInt();
	        if (confirmationChoice == 1) {
	            confirmed = true; // User confirmed the choices, exit the loop.
	        } else if (confirmationChoice == 2) {
	            System.out.print("Enter the number of the security question you want to update (1 to " + numSecurityQuestions + ", 0 to finish): ");
	            int questionNumber = scanner.nextInt();
	            if (questionNumber == 0) {
	                break; // Exit the loop to confirm or finish updating.
	            }
	            if (questionNumber >= 1 && questionNumber <= numSecurityQuestions) {
	                System.out.print("Enter your updated security question " + questionNumber + ": ");
	                securityQuestions.set(questionNumber - 1, scanner.next());

	                System.out.print("Enter your updated answer to the question '" + securityQuestions.get(questionNumber - 1) + "': ");
	                securityAnswers.set(questionNumber - 1, scanner.next());
	            } else {
	                System.out.println("Invalid question number. Please enter a number between 1 and " + numSecurityQuestions + " or 0 to finish.");
	            }
	        }
	    }

	    // Store the confirmed information in the student instance
	    student newStudent = new student();
	    newStudent.setStudentID(studentID);
	    newStudent.setName(name);
	    newStudent.setUserGroup(userGroup);
	    newStudent.setPassword(password);
	    newStudent.setSecurityQuestions(securityQuestions);
	    newStudent.setSecurityAnswers(securityAnswers);

	 // Add the new student to the list of existing students
	    student.addStudent(newStudent);

	    System.out.println("Registration successful!");
	}

}
