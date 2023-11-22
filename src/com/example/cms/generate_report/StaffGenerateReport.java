package com.example.cms.generate_report;

import com.example.cms.Camp.Camp;
import com.example.cms.Format;
import com.example.cms.Student.Attendee;
import com.example.cms.Student.Committee;

import java.io.FileWriter;
import java.io.IOException;

public class StaffGenerateReport implements GenerateReport{

    public void generateAttendeeList(Camp camp, Format format) {

        // Use a StringBuilder to build the content
        StringBuilder attendeeContent = new StringBuilder();

        //generate camp info by appending all info
        Camp.generateCampInfo(attendeeContent, camp);

        // Append header for the attendee information
        attendeeContent.append("Role, Name, ID, Faculty\n");

        // Append attendee information
        for (Attendee attendee : camp.getAttendeesRegistered()) {
            attendeeContent.append("Attendee").append(",");
            attendeeContent.append(attendee.getName()).append(",");
            attendeeContent.append(attendee.getStudentID()).append(",");
            attendeeContent.append(attendee.getFaculty()).append("\n");
        }

        // Specify the file path
        String filePath; // Change the file extension to .txt if you prefer a TXT file
        if ("csv".equalsIgnoreCase(format.toString())) {
            filePath = "attendee_report.csv";
        } else if ("txt".equalsIgnoreCase(format.toString())) {
            filePath = "attendee_report.txt";
        } else {
            System.out.println("Invalid file format specified. Please use 'csv' or 'txt'.");
            return;
        }

        // Write the content to the CSV file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(attendeeContent.toString());
            System.out.println("Attendee list saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the attendee list.");
            e.printStackTrace();
        }
    }

    public void generateCommitteeList(Camp camp, Format format) {
        // Use a StringBuilder to build the content
        StringBuilder committeeContent = new StringBuilder();

        Camp.generateCampInfo(committeeContent, camp);

        // Append header for the attendee information
        committeeContent.append("Role, Name, ID, Faculty, points\n");

        // Append attendee information
        for (Committee committeeReg : camp.getCommitteeRegistered()) {
            committeeContent.append("Committee").append(",");
            committeeContent.append(committeeReg.getName()).append(",");
            committeeContent.append(committeeReg.getStudentID()).append(",");
            committeeContent.append(committeeReg.getFaculty()).append(",");
            committeeContent.append(committeeReg.getPoints()).append("\n");
        }

        // Specify the file path
        String filePath;
        if ("csv".equalsIgnoreCase(format.toString())) {
            filePath = "committee_report.csv";
        } else if ("txt".equalsIgnoreCase(format.toString())) {
            filePath = "committee_report.txt";
        } else {
            System.out.println("Invalid file format specified. Please use 'csv' or 'txt'.");
            return;
        }

        // Write the content to the CSV file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(committeeContent.toString());
            System.out.println("Attendee list saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the committee list.");
            e.printStackTrace();
        }
    }

}
