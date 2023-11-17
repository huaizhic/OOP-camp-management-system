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

    protected static String studentID;
    private String password = "password";
    private String name;
    private Faculty faculty;
    private Student_Role userGroup;
    protected static ArrayList<Camp> registeredCamps;
    public static Map<String, Student_User> existingStudents = new HashMap<>(); // Use a HashMap instead of a List
    protected static List<String> campAccessibility; // This indicates which camp the attendee can see
    private boolean campCommittee;
    private ArrayList<String> securityQuestion;
    private ArrayList<String> securityAnswers;
    private ArrayList<Enquiry> enquirySubmitted;
    private ArrayList<Suggestion> suggestionSubmitted;

    public Student_User() {
        this.studentID = ""; // You can set an initial value or leave it empty
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

    public ArrayList<Camp> getRegisteredCamps() {
        return this.registeredCamps;
    }

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

    public ArrayList<Enquiry> getEnquirySubmitted() {
        return this.enquirySubmitted;
    }

    public void setEnquirySubmitted(ArrayList<Enquiry> enquirySubmitted) {
        this.enquirySubmitted = enquirySubmitted;
    }
    
    public ArrayList<Suggestion> getSuggestionSubmitted() {
        return this.suggestionSubmitted;
    }

    public void setSuggestionSubmitted(ArrayList<Suggestion> suggestionSubmitted) {
        this.suggestionSubmitted = suggestionSubmitted;
    }

    public static Student_User getStudentById(String StudentId) {
        return existingStudents.get(StudentId);
    }
    
    public static boolean isStudentExist(String userID) {
        return existingStudents.containsKey(userID);
    }

    public static void addStudent(Student_User newStudent) {
        existingStudents.put(newStudent.getStudentID(), newStudent);
        //saveStudentsToFile(existingStudents.values());
    }

    public static String getName(String userID) {
        Student_User student = existingStudents.get(userID);
        return (student != null) ? student.name : null;
    }
    
    public static void loadStudentsFromCSV() {
        String csvFilePath = "student.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the CSV line into an array
                String[] data = line.split(",");

                // Create a new Student_User instance and set its attributes
                Student_User student = new Student_User();
                student.setStudentID(data[0]);
                student.setName(data[1]);
                student.setUserGroup(Student_Role.valueOf(data[2])); // Assuming Student_Role values are in the CSV
                student.setFaculty(Faculty.valueOf(data[3])); // Assuming Faculty values are in the CSV

                // Parse CampAccessibility from the CSV
                List<String> campAccessibility = new ArrayList<>();
                String[] campAccessibilityArray = data[4].split("\\|");
                for (String camp : campAccessibilityArray) {
                    campAccessibility.add(camp);
                }
                student.setCampAccessibility(campAccessibility);

                // Parse CampCommittee from the CSV
                boolean campCommittee = Boolean.parseBoolean(data[5]);
                student.setCampCommittee(campCommittee);

                // Parse RegisteredCamps from the CSV
                List<Camp> registeredCamps = new ArrayList<>();
                String[] registeredCampsArray = data[6].split("\\|");
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
                String[] securityQuestionsArray = data[7].split("\\|");
                for (String question : securityQuestionsArray) {
                    securityQuestions.add(question);
                }
                student.setSecurityQuestions(securityQuestions);

                // Parse SecurityAnswers from the CSV
                List<String> securityAnswers = new ArrayList<>();
                String[] securityAnswersArray = data[8].split("\\|");
                for (String answer : securityAnswersArray) {
                    securityAnswers.add(answer);
                }
                student.setSecurityAnswers(securityAnswers);

                // Parse EnquirySubmitted from the CSV
                List<Enquiry> enquirySubmitted = new ArrayList<>();
                String[] enquirySubmittedArray = data[9].split("\\|");
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
                String[] suggestionSubmittedArray = data[10].split("\\|");
                for (String suggestion : suggestionSubmittedArray) {
                    // Assuming Suggestion class has a static method to get a Suggestion instance by its subject
                    Suggestion submittedSuggestion = Suggestion.getSuggestionBySubject(suggestion);
                    if (submittedSuggestion != null) {
                        suggestionSubmitted.add(submittedSuggestion);
                    }
                }
                student.setSuggestionSubmitted(suggestionSubmitted);

                existingStudents.put(student.getStudentID(), student);
            }

            System.out.println("Students loaded from " + csvFilePath + " successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }

   

}