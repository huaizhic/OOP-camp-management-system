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
import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Suggestions.Suggestion;

public class Student_User {

    protected String studentID;
    private String password = "password";
    private String name;
    private Faculty faculty;
    private Student_Role userGroup;
    protected static List<Camp> registeredCamps;
    public Map<String, Student_User> existingStudents = new HashMap<>(); // Use a HashMap instead of a List
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
        this.name = "";
        this.faculty = Faculty.NIL; // Set a default faculty or choose based on your requirements
        this.userGroup = Student_Role.NIL; // Set a default role or choose based on your requirements
        this.registeredCamps = new ArrayList<>();
        this.campAccessibility = new ArrayList<>();
        this.campCommittee = false; // Set to true if the student is initially part of the camp committee
        this.securityQuestion = new ArrayList<>();
        this.securityAnswers = new ArrayList<>();
        this.enquirySubmitted = new ArrayList<>();
        this.existingStudents = new HashMap<>(); // Initialize the map in the constructor
        
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
     //   loadStudentsFromCSV();

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
    
    public void loadStudentsFromCSV() {
        String csvFilePath = "student.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag  for header
            
            while ((line = reader.readLine()) != null) {
            	if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }
            	
                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 12) {
                    // Create a new Student_User instance and set its attributes
                    Student_User student = new Student_User();
                    student.setStudentID(data[0]);
                    student.setName(data[1]);
                    student.setPassword(data[2]);
                    student.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
                    student.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV

                    // Parse CampAccessibility from the CSV
                    /*
                    List<String> campAccessibility = new ArrayList<>();
                    String[] campAccessibilityArray = data[5].split("\\|");
                    for (String camp : campAccessibilityArray) {
                        campAccessibility.add(camp);
                    }
                    student.setCampAccessibility(campAccessibility);*/

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[6]);
                    student.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    String[] registeredCampsArray = data[7].split("\\|");
                    for (String camp : registeredCampsArray) {
                        // Assuming Camp class has a static method to get a Camp instance by its name
                        Camp registeredCamp = Camp.getCampByName(camp);
                        if (registeredCamp != null) {
                            registeredCamps.add(registeredCamp);
                        }
                    }
                    student.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    String[] securityQuestionsArray = data[8].split("\\|");
                    for (String question : securityQuestionsArray) {
                        securityQuestions.add(question);
                    }
                    student.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    String[] securityAnswersArray = data[9].split("\\|");
                    for (String answer : securityAnswersArray) {
                        securityAnswers.add(answer);
                    }
                    student.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Enquiry> enquirySubmitted = new ArrayList<>();
                    String[] enquirySubmittedArray = data[10].split("\\|");
                    for (String enquiry : enquirySubmittedArray) {
                        // Assuming Enquiry class has a static method to get an Enquiry instance by its subject
                        Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
                        if (submittedEnquiry != null) {
                            enquirySubmitted.add(submittedEnquiry);
                        }
                    }
                    student.setEnquirySubmitted(enquirySubmitted);

                    // Parse SuggestionSubmitted from the CSV
                    List<Suggestion> suggestionSubmitted = new ArrayList<>();
                    String[] suggestionSubmittedArray = data[11].split("\\|");
                    for (String suggestion : suggestionSubmittedArray) {
                        // Assuming Suggestion class has a static method to get a Suggestion instance by its subject
                        Suggestion submittedSuggestion = Suggestion.getSuggestionBySubject(suggestion);
                        if (submittedSuggestion != null) {
                            suggestionSubmitted.add(submittedSuggestion);
                        }
                    }
                    student.setSuggestionSubmitted(suggestionSubmitted);

                    System.out.println("Student loaded successfully");

                    existingStudents.put(student.getStudentID(), student);
                    
                    for (Student_User Student : existingStudents.values()) {
                        System.out.println("Student ID: " + Student.getStudentID());
                        System.out.println("Name: " + Student.getName());
                        System.out.println("Password: " + Student.getPassword());
                        System.out.println("User Group: " + Student.getUserGroup());
                        System.out.println("Faculty: " + Student.getFaculty());

                        // Print CampAccessibility
                        /*
                        System.out.print("Camp Accessibility: ");
                        for (String camp : Student.getCampAccessibility()) {
                            System.out.print(camp + "|");
                        }
                        System.out.println(); */

                        System.out.println("Camp Committee: " + Student.getCampCommittee());

                        // Print RegisteredCamps
                        System.out.print("Registered Camps: ");
                        for (Camp camp : Student.getRegisteredCamps) {
                            System.out.print(camp.getCampName() + "|");
                        }
                        System.out.println();

                        // Print SecurityQuestions
                        System.out.print("Security Questions: ");
                        for (String question : Student.getSecurityQuestion()) {
                            System.out.print(question + "|");
                        }
                        System.out.println();

                        // Print SecurityAnswers
                        System.out.print("Security Answers: ");
                        for (String answer : Student.getSecurityAnswers()) {
                            System.out.print(answer + "|");
                        }
                        System.out.println();

                        // Print EnquirySubmitted
                        System.out.print("Enquiry Submitted: ");
                        for (Enquiry enquiry : Student.getEnquirySubmitted()) {
                            System.out.print(enquiry.getEnquiry_Subject() + "|");
                        }
                        System.out.println();

                        // Print SuggestionSubmitted
                        System.out.print("Suggestion Submitted: ");
                        for (Suggestion suggestion : student.getSuggestionSubmitted()) {
                            System.out.print(suggestion.getSuggestion_Subject() + "|");
                        }
                        System.out.println();

                        System.out.println();
                    }
                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
            System.out.println("Students loaded from " + csvFilePath + " successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }
    
    public void updateCSVFile(String studentId) {
        // Get the path to the CSV file
        String csvFilePath = "student.csv";

        // Create a StringBuilder to store the updated content
        StringBuilder updatedContent = new StringBuilder();

        // Find the student based on the provided studentId
        Student_User student = existingStudents.get(studentId);

        // Check if the student exists
        if (student != null) {
            // Append the information for the specific student to the StringBuilder
            updatedContent.append(student.getStudentID()).append(",");
            updatedContent.append(student.getName()).append(",");
            updatedContent.append(student.getPassword()).append(",");
            updatedContent.append(student.getUserGroup()).append(",");
            updatedContent.append(student.getFaculty()).append(",");
            updatedContent.append(String.join("|", student.getCampAccessibility())).append(",");
            updatedContent.append(student.getCampCommittee()).append(",");
            updatedContent.append(String.join("|", student.getRegisteredCamps().stream().map(Camp::getCampName).toArray(String[]::new))).append(",");
            updatedContent.append(String.join("|", student.getSecurityQuestion())).append(",");
            updatedContent.append(String.join("|", student.getSecurityAnswers())).append(",");
            updatedContent.append(String.join("|", student.getEnquirySubmitted().stream().map(Enquiry::getEnquiry_Subject).toArray(String[]::new))).append(",");
            updatedContent.append(String.join("|", student.getSuggestionSubmitted().stream().map(Suggestion::getSuggestion_Subject).toArray(String[]::new))).append("\n");

            // Write the updated content to the CSV file
            try (FileWriter writer = new FileWriter(csvFilePath)) {
                writer.write(updatedContent.toString());
                System.out.println("CSV file updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the CSV file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found. CSV file not updated.");
        }
    }

   

}