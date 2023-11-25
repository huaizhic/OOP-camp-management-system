package com.example.cms.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cms.Faculty;
import com.example.cms.Student_Role;
import com.example.cms.CSVConverter.CSVDataManager;
import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Password.Password_Hasher;
import com.example.cms.Suggestions.Suggestion;

public class Student_User {

    protected String studentID;
    private String password = "password";
    public String salt; // Store the salt
    private String name;
    private Faculty faculty;
    private Student_Role userGroup;
    private int points;
    protected static List<Camp> registeredCamps;
    public static Map<String, Student_User> existingStudents = new HashMap<>(); // Use a HashMap instead of a List
    protected List<String> campAccessibility; // This indicates which camp the attendee can see
    private boolean campCommittee;
    private ArrayList<String> securityQuestion;
    private ArrayList<String> securityAnswers;
    private List<Enquiry> enquirySubmitted;
    private List<Suggestion> suggestionSubmitted;

    public Student_User() {
    	
    	 // Initialize the map only if it's null
        if (existingStudents == null) {
            existingStudents = new HashMap<>();
        }
        
        this.studentID = null; // You can set an initial value or leave it empty
        this.password = "password";
        this.salt = null;        // Generate a random salt and store it
        this.name = "";
        this.faculty = Faculty.NIL; // Set a default faculty or choose based on your requirements
        this.userGroup = Student_Role.NIL; // Set a default role or choose based on your requirements
        this.registeredCamps = new ArrayList<>();
        this.campAccessibility = new ArrayList<>();
        this.campCommittee = false; // Set to true if the student is initially part of the camp committee
        this.securityQuestion = new ArrayList<>();
        this.securityAnswers = new ArrayList<>();
        this.enquirySubmitted = new ArrayList<>();
  
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
    	//this.salt = Password_Hasher.generateSalt();
    	//System.out.println("The salt, salt in setPassword " + Password_Hasher.generateSalt() );
       // this.password = Password_Hasher.hashPassword(newPassword, this.salt);
    	this.password = newPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Student_Role getUserGroup() {
        return this.userGroup;
    }

    public void setUserGroup(Student_Role userGroup) {
        this.userGroup = userGroup;
    }

    public void setRegisteredCamps(List<Camp> registeredCamps2) {
        this.registeredCamps = registeredCamps2;
    }

    public List<Camp> getRegisteredCamps() {
        return this.registeredCamps ;
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

    public void setCampCommittee(boolean campCommittee) {
        this.campCommittee = campCommittee;
    }

    public ArrayList<String> getSecurityQuestion() {
        return this.securityQuestion;
    }

    public void setSecurityQuestions(List<String> securityQuestions) {
        this.securityQuestion = new ArrayList<>(securityQuestions);
    }

    public ArrayList<String> getSecurityAnswers() {
        return this.securityAnswers;
    }

    public void setSecurityAnswers(List<String> securityAnswers) {
        this.securityAnswers = new ArrayList<>(securityAnswers);
    }

    public List<Enquiry> getEnquirySubmitted() {
        return this.enquirySubmitted;
    }

    public void setEnquirySubmitted(List<Enquiry> enquirySubmitted2) {
        this.enquirySubmitted = enquirySubmitted2;
    }
    
    public List<Suggestion> getSuggestionSubmitted() {
        return this.suggestionSubmitted;
    }

    public void setSuggestionSubmitted(List<Suggestion> suggestionSubmitted2) {
        this.suggestionSubmitted = suggestionSubmitted2;
    }

    public  Student_User getStudentById(String StudentId) {
    	 for (Student_User student : existingStudents.values()) {
    	        if (student.getStudentID().equals(studentID)) {
    	            return student;
    	        }
    	    }
    	    return null; // If no student with the given ID is found
    }
    
    public Map<String, Student_User> getExistingStudents() {
        return existingStudents;
    }
    
    public boolean isStudentExist(String userID) {
        System.out.println("Checking for student existence. Given ID: " + userID);
        
     // Load students from CSV before checking existence
        CSVDataManager.loadStudentsFromCSV(this);

        // Use the instance-specific existingStudents map
        for (String existingID : this.existingStudents.keySet()) {
            System.out.println("Comparing with existing ID: " + existingID);
        }

        return this.existingStudents.containsKey(userID);
    }

    public  void addStudent(Student_User newStudent) {
        existingStudents.put(newStudent.getStudentID(), newStudent);
        //saveStudentsToFile(existingStudents.values());
    }

    public  String getName(String userID) {
        Student_User student = existingStudents.get(userID);
        return (student != null) ? student.name : null;
    }
    
    
   

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

   

}