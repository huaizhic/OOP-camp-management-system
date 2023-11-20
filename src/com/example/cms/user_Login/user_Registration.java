package com.example.cms.user_Login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.cms.Faculty;
import com.example.cms.Student_Role;
import com.example.cms.CSVConverter.CSVWriter;
import com.example.cms.Student.Password_Manager;
import com.example.cms.Student.Student_User;

public class user_Registration {
	public static void registerNewUser(Scanner scanner) {
	    // Collect user information for registration
	    System.out.println("Student Registration:");

	    // Collect basic information
	    System.out.print("Enter your student ID: ");
	    String studentID = scanner.next().toUpperCase().trim();

	    // Check if the student already exists
	    Student_User existingStudent = new Student_User();
	    if (existingStudent.isStudentExist(studentID)) {
	        System.out.println("This student already has an account.");
	        return;
	    }

	    System.out.print("Enter your name: ");
	    String name = scanner.next().toUpperCase().trim();

	    // Collect user group
	    System.out.print("Enter your user group (1 for Attendee, 2 for Committee): ");
	    Student_Role userGroup  = null;
	    boolean campCommittee = false;
	    boolean confirmed = false;

	    while (!confirmed) {
	        int userGroupChoice = scanner.nextInt();

	        if (userGroupChoice == 1) {
	            userGroup = Student_Role.ATTENDEE;
	            System.out.println("You have selected 'Attendee'. Is this correct? (1 for Yes, 2 for No): ");
	            int confirmationChoice = scanner.nextInt();

	            if (confirmationChoice == 1) {
	                confirmed = true; // User confirmed the choice, exit the loop.
	                campCommittee = false;
	            } else if (confirmationChoice == 2) {
	                System.out.print("Please re-select your user group (1 for Attendee, 2 for Committee): ");
	            } else {
	                System.out.println("Invalid confirmation choice. Please enter '1' for Yes or '2' for No.");
	            }
	        } else if (userGroupChoice == 2) {
	            userGroup = Student_Role.COMMITTEE;
	            System.out.println("You have selected 'Committee'. Is this correct? (1 for Yes, 2 for No): ");
	            int confirmationChoice = scanner.nextInt();

	            if (confirmationChoice == 1) {
	                confirmed = true; // User confirmed the choice, exit the loop.
	                campCommittee = true;
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

	        if (Password_Manager.isValidPassword(password)) {
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
	        	//password = confirmPassword;
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
	                    if (Password_Manager.isValidPassword(password)) {
	                        break; // Exit the loop if the new password is valid
	                    } else {
	                        System.out.println("Password does not meet the criteria. Please make sure it has 8 characters, includes both upper and lower case letters, and is alphanumeric.");
	                    }
	                }
	            }
	        }
	    }

	 // Add faculty information
	    List<String> campAccessibility = new ArrayList<>();
	    System.out.println("Select your faculty:");
	    System.out.println("1. ADM");
	    System.out.println("2. ASE");
	    System.out.println("3. CCEB");
	    System.out.println("4. CEE");
	    System.out.println("5. EEE");
	    System.out.println("6. IGS");
	    System.out.println("7. NBS");
	    System.out.println("8. MAE");
	    System.out.println("9. MSE");
	    System.out.println("10. NIE");
	    System.out.println("11. RSIS");
	    System.out.println("12. SBS");
	    System.out.println("13. SCSE");
	    System.out.println("14. SPMS");
	    System.out.println("15. SSS");
	    System.out.println("16. SOH");
	    System.out.println("17. WKWSCI");
	    System.out.println("18. ALL");

	    int facultyChoice;

	    do {
	        System.out.print("Enter the number corresponding to your faculty: ");
	        while (!scanner.hasNextInt()) {
	            System.out.println("Invalid input. Please enter a number.");
	            scanner.next(); // consume the invalid input
	        }
	        facultyChoice = scanner.nextInt();

	        // Consume the newline character
	        scanner.nextLine();
	    } while (facultyChoice < 1 || facultyChoice > 18);
	    // Map the faculty choice to the actual faculty
	    Faculty facultyInput = null ;
	    switch (facultyChoice) {
	        case 1:
	            facultyInput = Faculty.ADM;
	            campAccessibility.add("ADM");
	            break;
	        case 2:
	        	facultyInput = Faculty.ASE;
	            campAccessibility.add("ASE");
	            break;
	        case 3:
	        	facultyInput = Faculty.CCEB;
	            campAccessibility.add("CCEB");
	            break;
	        case 4:
	        	facultyInput = Faculty.CEE;
	            campAccessibility.add("CEE");
	            break;
	        case 5:
	        	facultyInput = Faculty.EEE;
	            campAccessibility.add("EEE");
	            break;
	        case 6:
	        	facultyInput = Faculty.IGS;
	            campAccessibility.add("IGS");
	            break;
	        case 7:
	        	facultyInput = Faculty.NBS;
	            campAccessibility.add("NBS");
	            break;
	        case 8:
	        	facultyInput = Faculty.MAE;
	            campAccessibility.add("MAE");
	            break;
	        case 9:
	        	facultyInput = Faculty.MSE;
	            campAccessibility.add("MSE");
	            break;
	        case 10:
	        	facultyInput = Faculty.NIE;
	            campAccessibility.add("NIE");
	            break;
	        case 11:
	        	facultyInput = Faculty.RSIS;
	            campAccessibility.add("RSIS");
	            break;
	        case 12:
	        	facultyInput = Faculty.SBS;
	            campAccessibility.add("SBS");
	            break;
	        case 13:
	        	facultyInput = Faculty.SCSE;
	            campAccessibility.add("SCSE");
	            break;
	        case 14:
	        	facultyInput = Faculty.SPMS;
	            campAccessibility.add("SPMS");
	            break;
	        case 15:
	        	facultyInput = Faculty.SSS;
	            campAccessibility.add("SSS");
	            break;
	        case 16:
	        	facultyInput = Faculty.SOH;
	            campAccessibility.add("SOH");
	            break;
	        case 17:
	        	facultyInput = Faculty.WKWSCI;
	            campAccessibility.add("WKWSCI");
	            break;
	        case 18:
	        	facultyInput = Faculty.ALL;
	            campAccessibility.add("ALL");
	            break;
	        default:
	            facultyInput = Faculty.NIL; // Handle invalid choices
	            campAccessibility.add("NIL");
	            break;
	    }


	    int numSecurityQuestions = 3;

	    List<String> securityQuestions = new ArrayList<>();
	    List<String> securityAnswers = new ArrayList<>();

	    confirmed = false;

	    for (int i = 1; i <= numSecurityQuestions; i++) {
            System.out.print("Enter your security question " + i + ": ");
            String question = getUserInput(scanner);
            securityQuestions.add(question);

            System.out.print("Enter your answer to the question '" + question + "': ");
            String answer = getUserInput(scanner);
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
	    Student_User newStudent = new Student_User();
        newStudent.setStudentID(studentID);
        newStudent.setName(name);
        newStudent.setUserGroup(userGroup);
        newStudent.setPassword(password);
        newStudent.setFaculty(facultyInput);
        newStudent.setCampAccessibility(campAccessibility);
        newStudent.setRegisteredCamps(null);
        newStudent.setCampCommittee(campCommittee);
        newStudent.setSecurityQuestions(securityQuestions);
        newStudent.setSecurityAnswers(securityAnswers);
        newStudent.setEnquirySubmitted(null);
        newStudent.setSuggestionSubmitted(null);

	 // Add the new student to the list of existing students
	  //  Student_User.addStudent(newStudent);
	    
	    CSVWriter.writeUserToCSV(newStudent, true);
	    
	    System.out.println("Registration successful!");
	    
	    account_Manager accountManager = new account_Manager(scanner);
	    accountManager.start();
	}
	
	  private static String getUserInput(Scanner scanner) {
	        String input = scanner.nextLine().trim().toUpperCase();
	        while (input.isEmpty()) {
	            System.out.println("Input cannot be empty. Please try again.");
	            input = scanner.nextLine().trim().toUpperCase();
	        }
	        return input;
	    }
	

}
