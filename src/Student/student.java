package Student;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Group;

import camp_Application_Management_System.Camp;
import camp_Application_Management_System.Enquiry;

public class student extends attendee {

	private String studentID;
	private String password = "password";
	private String name;
	private String userGroup;
	private ArrayList<Camp> registeredCamps;
	private boolean campCommittee;
	private ArrayList<Camp> notRegisterable;
	private String securityQuestion;
	private String securityAns;
	private ArrayList<Enquiry> enquirySubmitted;

	public student() {
		// TODO - implement Student.Student
		throw new UnsupportedOperationException();
	}

	public String getStudentID() {
		return this.studentID;
	}


	// Getter for password
	public String getPassword() {
		return password;
	}

	// Setter for password
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}


	public static student getStudentById(String studentID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<student> existingStudents = getExistingStudents(); // You need to implement this method.

		// Search for the student with the provided studentID.
		for (student student : existingStudents) {
			if (studentID.equals(student.getStudentID())) {
				return student; // Return the found student.
			}
		}

		// Return null if the student is not found.
		return null;
	}

	public static List<student> getExistingStudents() {
		// Implement a method to retrieve a list of existing students from your data source.
		// For this example, we'll return an empty list initially.

		List<student> existingStudents = new ArrayList<>();

		// You can add logic to retrieve the list of existing students from your data source here.

		return existingStudents;
	}

	public static String getName(String userID) {
		// For demonstration purposes, let's assume you have a list of existing students.
		List<student> existingStudents = getExistingStudents(); // You need to implement this method.

		for (student Student : existingStudents) { // assuming existingStudents is the list of students
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

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	/**
	 *
	 * @param securityQuestion
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAns() {
		return this.securityAns;
	}

	/**
	 *
	 * @param securityAns
	 */
	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public ArrayList<Enquiry> getEnquirySubmitted() {
		return this.enquirySubmitted;
	}

	/**
	 *
	 * @param enquirySubmitted
	 */
	public void setEnquirySubmitted(Enquiry newEnquiry) {
		// TODO - implement Student.setEnquirySubmitted
		// throw new UnsupportedOperationException();

		// add new enquiry object to arraylist
		this.enquirySubmitted.add(newEnquiry);
	}

}