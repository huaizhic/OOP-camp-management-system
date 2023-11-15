package com.example.cms.Student;

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

public class Student_User {

    protected String studentID;
    private String password = "password";
    private String name;
    private Faculty faculty;
    private Student_Role userGroup;
    private ArrayList<Camp> registeredCamps;
    public static Map<String, Student_User> existingStudents = new HashMap<>(); // Use a HashMap instead of a List
    protected static List<String> campAccessibility; // This indicates which camp the attendee can see
    private boolean campCommittee;
    private ArrayList<String> securityQuestion;
    private ArrayList<String> securityAnswers;
    private ArrayList<Enquiry> enquirySubmitted;

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

    public static Student_User getStudentById(String StudentId) {
        return existingStudents.get(StudentId);
    }
    
    public static boolean isStudentExist(String userID) {
        return existingStudents.containsKey(userID);
    }

    public static void addStudent(Student_User newStudent) {
        existingStudents.put(newStudent.getStudentID(), newStudent);
        saveStudentsToFile(existingStudents.values());
    }

    public static String getName(String userID) {
        Student_User student = existingStudents.get(userID);
        return (student != null) ? student.name : null;
    }

    private static void saveStudentsToFile(Iterable<Student_User> students) {
        try {
            FileWriter writer = new FileWriter("students.txt");
            for (Student_User student : students) {
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getUserGroup() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}