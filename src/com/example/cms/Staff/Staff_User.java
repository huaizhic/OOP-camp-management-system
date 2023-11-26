package com.example.cms.Staff;

import com.example.cms.Camp.Camp;
import com.example.cms.Faculty;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Entity class, provides basic functionality to get and set information about the Staff
 * Routed from account_Manager
 * As part of Open-Closed Principle, we close this class to modification,
 * but open it to extension to Staff class
 */
public class Staff_User {
    private String staffID;

    private String password = "password";
    private String name;
    private Faculty userGroup;
    private ArrayList<Camp> campsCreated;
    private String securityQuestion;
    private String securityAns;

    protected static HashMap<String, Staff> existingStaff;

    public Staff_User(String staffID, String name, Faculty userGroup, String securityQuestion, String securityAns){
        this.staffID = staffID;
        this.name = name;
        this.userGroup = userGroup;
        campsCreated = new ArrayList<>();
        this.securityQuestion = securityQuestion;
        this.securityAns = securityAns;
    }

    public String getStaffID() {
        return this.staffID;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Faculty getUserGroup() {
        return this.userGroup;
    }

    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    public ArrayList<Camp> getCampsCreated() {
        return this.campsCreated;
    }

    public void setCampsCreated(Camp campsCreated) {this.campsCreated.add(campsCreated);}


    public String getSecurityQuestion() {
        return this.securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAns() {
        return this.securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    public static HashMap<String, Staff> getExistingStaff() {
        return existingStaff;
    }

    protected Staff getStaffByID(String staffID){
        for(Staff staff : existingStaff.values()){
            if(staff.getStaffID().equals(staffID)){
                return staff;
            }
        }
        System.out.println("No staff has been found");
        return null;
    }

    public void updateCSVFile(String staffID) {
        // Get the path to the CSV file
        String csvFilePath = "staff.csv";

        // Create a StringBuilder to store the updated content
        StringBuilder updatedContent = new StringBuilder();

        // Find the student based on the provided studentId
        Staff staff = getStaffByID(staffID);

        // Check if the student exists
        if (staff != null) {
            // Append the information for the specific student to the StringBuilder
            updatedContent.append(staff.getStaffID()).append(",");
            updatedContent.append(staff.getName()).append(",");
            updatedContent.append(staff.getPassword()).append(",");
            updatedContent.append(staff.getUserGroup()).append(",");
            updatedContent.append(String.join("|", staff.getCampsCreated().stream().map(Camp::getCampName).toArray(String[]::new))).append(",");
            updatedContent.append(String.join("|", staff.getSecurityQuestion())).append(",");
            updatedContent.append(String.join("|", staff.getSecurityAns())).append(",");

            // Write the updated content to the CSV file
            try (FileWriter writer = new FileWriter(csvFilePath)) {
                writer.write(updatedContent.toString());
                System.out.println("CSV file updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the CSV file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Staff with ID " + staffID + " not found. CSV file not updated.");
        }
    }

}
