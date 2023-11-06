package com.example.cms;

import com.example.cms.Camp.Camp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentUser {

	protected String studentID;
	private String password = "password";
	private String name;
	private String userGroup;
	private ArrayList<Camp> registeredCamps;
	private boolean campCommittee;
	private ArrayList<Camp> notRegisterable;
	private ArrayList<String> securityQuestion;
	private ArrayList<String> securityAns;
	private ArrayList<Enquiry> enquirySubmitted;

	// Initialize the ArrayList
	private static List<StudentUser> existingStudents = new ArrayList<>();

	public StudentUser() {
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

	public static StudentUser getStudentById(String studentID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<StudentUser> existingStudents = getExistingStudents(); // You need to implement this method.

		// Search for the student with the provided studentID.
		for (StudentUser student : existingStudents) {
			if (studentID.equals(student.getStudentID())) {
				return student; // Return the found student.
			}
		}

		// Return null if the student is not found.
		return null;
	}

	public static List<StudentUser> getExistingStudents() {
		return existingStudents;
	}

	public static void addStudent(StudentUser newStudent) {
		List<StudentUser> existingStudents = getExistingStudents(); // Retrieve the list of existing students
		existingStudents.add(newStudent); // Add the new student to the list

		// Now, save the updated list of students to a text file (you can choose a different data source as needed).
		saveStudentsToFile(existingStudents);
	}


	public static String getName(String userID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<StudentUser> existingStudents = getExistingStudents(); // You need to implement this method.

		for (StudentUser Student : existingStudents) { // assuming existingStudents is the list of students
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

	public ArrayList<Camp> getNotRegisterable() {
		return this.notRegisterable;
	}

	/**
	 *
	 * @param notRegisterable
	 */
	public void setNotRegisterable(ArrayList<Camp> notRegisterable) {
		this.notRegisterable = notRegisterable;
	}

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

	public ArrayList<String> getSecurityAns() {
		return this.securityAns;
	}

	/**
	 *
	 * @param securityAns
	 */
	public void setSecurityAnswers(List<String> securityAnswers) {
		this.securityAns = (ArrayList<String>) securityAnswers;
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
	private static void saveStudentsToFile(List<StudentUser> students) {
		try {
			FileWriter writer = new FileWriter("students.txt"); // You can choose a different file name or location
			for (StudentUser student : students) {
				writer.write(student.getStudentID() + "," + student.getName() + "," + student.getUserGroup() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}