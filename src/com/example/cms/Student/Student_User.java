package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.UserGroup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class student_User {

	protected String studentID;
	private String password = "password";
	private String name;
	private UserGroup faculty;
	private String userGroup;
	private ArrayList<Camp> registeredCamps;
	protected static List<String> campAccessibility; // This indicates which camp the attendee can see
	private boolean campCommittee;
	//private ArrayList<Camp> notRegisterable; // what is this for? 
	private ArrayList<String> securityQuestion;
	private ArrayList<String> securityAnswers;
	private ArrayList<Enquiry> enquirySubmitted;

	// Initialize the ArrayList
	private static List<student_User> existingStudents = new ArrayList<>();

	public student_User() {
		// TODO - implement Student.Student
		throw new UnsupportedOperationException();
	}

	public String getStudentID() {
		return this.studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	// Getter for password
	public String getPassword() {
		return password;
	}

	// Setter for password
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	 public UserGroup getFaculty() {
	        return this.faculty;
	    }

	 public void setFaculty(String faculty) {

	            this.faculty = UserGroup.valueOf(faculty.toUpperCase()); // Convert to uppercase for case-insensitive comparison
	       
	    }

	    // Method to check if the provided faculty is valid
	 public static boolean isValidFaculty(String faculty) {
		    // Capitalize and remove spaces from the user input
		    String formattedFaculty = faculty.trim().toUpperCase();

		    for (UserGroup validFaculty : UserGroup.values()) {
		        if (validFaculty.name().equalsIgnoreCase(formattedFaculty)) {
		            return true;
		        }
		    }
		    return false;
		}


	    

	public static student_User getStudentById(String studentID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<student_User> existingStudents = getExistingStudents(); // You need to implement this method.

		// Search for the student with the provided studentID.
		for (student_User student : existingStudents) {
			if (studentID.equals(student.getStudentID())) {
				return student; // Return the found student.
			}
		}

		// Return null if the student is not found.
		return null;
	}

	public static List<student_User> getExistingStudents() {
		return existingStudents;
	}

	public static void addStudent(student_User newStudent) {
		List<student_User> existingStudents = getExistingStudents(); // Retrieve the list of existing students
		existingStudents.add(newStudent); // Add the new student to the list

		// Now, save the updated list of students to a text file (you can choose a different data source as needed).
		saveStudentsToFile(existingStudents);
	}


	public static String getName(String userID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<student_User> existingStudents = getExistingStudents(); // You need to implement this method.

		for (student_User Student : existingStudents) { // assuming existingStudents is the list of students
			if (userID.equals(Student.getStudentID())) {
				return Student.name;
			}
		}
		return null; // Return null if the userID is not found in the list.
	}

	public String getUserGroup() {
		return this.userGroup;
	}

	/**
	 *
	 * @param userGroup
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public ArrayList<Camp> getRegisteredCamps() {
		return this.registeredCamps;
	}

	/**
	 *
	 * @param registeredCamps
	 */
	public void setRegisteredCamps(ArrayList<Camp> registeredCamps) {
		this.registeredCamps = registeredCamps;
	}
	
	public List<String> getCampAccessibility() {
        return this.campAccessibility;
    }

    public void setCampAccessibility(List<String> campAccessibility) {
        this.campAccessibility = campAccessibility;
    }
	public boolean getCampCommittee() {
		return this.campCommittee;
	}

	/**
	 *
	 * @param campCommittee
	 */
	public void setCampCommittee(boolean campCommittee) {
		this.campCommittee = campCommittee;
	}

//	public ArrayList<Camp> getNotRegisterable() {
//		return this.notRegisterable;
//	}
//
//	/**
//	 *
//	 * @param notRegisterable
//	 */
//	public void setNotRegisterable(ArrayList<Camp> notRegisterable) {
//		this.notRegisterable = notRegisterable;
//	}

	public ArrayList<String> getSecurityQuestion() {
		return this.securityQuestion;
	}

	/**
	 *
	 * @param securityQuestion
	 */
	public void setSecurityQuestions(List<String> securityQuestions) {
		this.securityQuestion = (ArrayList<String>) securityQuestions;
	}

	public ArrayList<String> getSecurityAnswers() {
		return this.securityAnswers;
	}

	/**
	 *
	 * @param securityAnswers
	 */
	public void setSecurityAnswers(List<String> securityAnswers) {
		this.securityAnswers = (ArrayList<String>) securityAnswers;
	}

	public ArrayList<Enquiry> getEnquirySubmitted() {
		return this.enquirySubmitted;
	}

	/**
	 *
	 * @param enquirySubmitted
	 */
	public void setEnquirySubmitted(Enquiry enquirySubmitted) {
		// TODO - implement Student.setEnquirySubmitted
		throw new UnsupportedOperationException();
	}

	// Method to save the list of students to a text file
	private static void saveStudentsToFile(List<student_User> students) {
		try {
			FileWriter writer = new FileWriter("students.txt"); // You can choose a different file name or location
			for (student_User student : students) {
				writer.write(student.getStudentID() + "," + student.getName() + "," + student.getUserGroup() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}