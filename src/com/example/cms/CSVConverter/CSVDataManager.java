package com.example.cms.CSVConverter;

import com.example.cms.Faculty;
import com.example.cms.Student_Role;
import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Student.Attendee;
import com.example.cms.Student.Committee;
import com.example.cms.Student.Student_User;
import com.example.cms.Suggestions.Suggestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataManager {

	// Load students from CSV
    public static void loadStudentsFromCSV(Student_User studentUser) {
        String csvFilePath = "student.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 13) {
                    // Create a new Student_User instance and set its attributes
                    Student_User student = new Student_User();
                    student.setStudentID(data[0]);
                    student.setName(data[1]);
                    student.setPassword(data[2]);
                    student.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
                    student.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV
                    student.setPoints(Integer.parseInt(data[5]));
                    
                    // Parse CampAccessibility from the CSV
                    List<String> campAccessibility = new ArrayList<>();
                    String[] campAccessibilityArray = data[6].split("\\|");
                    for (String camp : campAccessibilityArray) {
                        campAccessibility.add(camp);
                    }
                    student.setCampAccessibility(campAccessibility);

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[7]);
                    student.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    String[] registeredCampsArray = data[8].split("\\|");
                    for (String camp : registeredCampsArray) {
                        Camp registeredCamp = Camp.getCampByName(camp);
                        if (registeredCamp != null) {
                            registeredCamps.add(registeredCamp);
                        }
                    }
                    student.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    String[] securityQuestionsArray = data[9].split("\\|");
                    for (String question : securityQuestionsArray) {
                        securityQuestions.add(question);
                    }
                    student.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    String[] securityAnswersArray = data[10].split("\\|");
                    for (String answer : securityAnswersArray) {
                        securityAnswers.add(answer);
                    }
                    student.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Enquiry> enquirySubmitted = new ArrayList<>();
                    String[] enquirySubmittedArray = data[11].split("\\|");
                    for (String enquiry : enquirySubmittedArray) {
                        Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
                        if (submittedEnquiry != null) {
                            enquirySubmitted.add(submittedEnquiry);
                        }
                    }
                    student.setEnquirySubmitted(enquirySubmitted);

                    // Parse SuggestionSubmitted from the CSV
                    List<Suggestion> suggestionSubmitted = new ArrayList<>();
                    String[] suggestionSubmittedArray = data[12].split("\\|");
                    for (String suggestion : suggestionSubmittedArray) {
                        Suggestion submittedSuggestion = Suggestion.getSuggestionBySubject(suggestion);
                        if (submittedSuggestion != null) {
                            suggestionSubmitted.add(submittedSuggestion);
                        }
                    }
                    student.setSuggestionSubmitted(suggestionSubmitted);

                    System.out.println("Student loaded successfully");

                    studentUser.addStudent(student);

                    // Print the loaded student details
                    System.out.println("Student ID: " + student.getStudentID());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Password: " + student.getPassword());
                    System.out.println("User Group: " + student.getUserGroup());
                    System.out.println("Faculty: " + student.getFaculty());
                    System.out.println("Point: " + student.getPoints());

                    // Print CampAccessibility
                    System.out.print("Camp Accessibility: ");
                    for (String camp : student.getCampAccessibility()) {
                        System.out.print(camp + "|");
                    }
                    System.out.println();

                    System.out.println("Camp Committee: " + student.getCampCommittee());

                    // Print RegisteredCamps
                    System.out.print("Registered Camps: ");
                    for (Camp camp : student.getRegisteredCamps()) {
                        System.out.print(camp.getCampName() + "|");
                    }
                    System.out.println();

                    // Print SecurityQuestions
                    System.out.print("Security Questions: ");
                    for (String question : student.getSecurityQuestion()) {
                        System.out.print(question + "|");
                    }
                    System.out.println();

                    // Print SecurityAnswers
                    System.out.print("Security Answers: ");
                    for (String answer : student.getSecurityAnswers()) {
                        System.out.print(answer + "|");
                    }
                    System.out.println();

                    // Print EnquirySubmitted
                    System.out.print("Enquiry Submitted: ");
                    for (Enquiry enquiry : student.getEnquirySubmitted()) {
                        System.out.print(enquiry.getEnquiry_Subject() + "|");
                    }
                    System.out.println();

                    // Print SuggestionSubmitted
                    System.out.print("Suggestion Submitted: ");
                    for (Suggestion suggestion : student.getSuggestionSubmitted()) {
                        System.out.print(suggestion.getSuggestion_Subject() + "|");
                    }
                    System.out.println();

                    System.out.println("Student details printed successfully.");

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }

    public static void updateStudentCSVFile(Student_User studentUser, String studentId) {
        // Get the path to the CSV file
        String csvFilePath = "student.csv";

        // Read the existing content of the CSV file
        List<String> existingLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the existing CSV file content.");
            e.printStackTrace();
            return;  // Exit the method if an error occurs while reading the existing content
        }

        // Create a StringBuilder to store the updated content
        StringBuilder updatedContent = new StringBuilder();

        // Find the student based on the provided studentId from studentUser
        Student_User student = studentUser.existingStudents.get(studentId);

        // Check if the student exists
        if (student != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                updatedContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            updatedContent.append(student.getStudentID()).append(",");
            updatedContent.append(student.getName()).append(",");
            updatedContent.append(student.getPassword()).append(",");
            updatedContent.append(student.getUserGroup()).append(",");
            updatedContent.append(student.getFaculty()).append(",");
            updatedContent.append(student.getPoints()).append(",");
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

/**********************************ATTENDEE********************************************/    
 // Load Attendee from CSV
    public static void loadAttendeeFromCSV(Attendee attendee) {
        String csvFilePath = "attendee.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 12) {
                    // Create a new Student_User instance and set its attributes
                    Attendee attendee1 = new Attendee();
                    attendee1.setStudentID(data[0]);
                    attendee1.setName(data[1]);
                    attendee1.setPassword(data[2]);
                    attendee1.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
                    attendee1.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV

                    // Parse CampAccessibility from the CSV
                    List<String> campAccessibility = new ArrayList<>();
                    String[] campAccessibilityArray = data[5].split("\\|");
                    for (String camp : campAccessibilityArray) {
                        campAccessibility.add(camp);
                    }
                    attendee1.setCampAccessibility(campAccessibility);

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[6]);
                    attendee1.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    String[] registeredCampsArray = data[7].split("\\|");
                    for (String camp : registeredCampsArray) {
                        Camp registeredCamp = Camp.getCampByName(camp);
                        if (registeredCamp != null) {
                            registeredCamps.add(registeredCamp);
                        }
                    }
                    attendee1.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    String[] securityQuestionsArray = data[8].split("\\|");
                    for (String question : securityQuestionsArray) {
                        securityQuestions.add(question);
                    }
                    attendee1.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    String[] securityAnswersArray = data[9].split("\\|");
                    for (String answer : securityAnswersArray) {
                        securityAnswers.add(answer);
                    }
                    attendee1.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Enquiry> enquirySubmitted = new ArrayList<>();
                    String[] enquirySubmittedArray = data[10].split("\\|");
                    for (String enquiry : enquirySubmittedArray) {
                        Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
                        if (submittedEnquiry != null) {
                            enquirySubmitted.add(submittedEnquiry);
                        }
                    }
                    attendee1.setEnquirySubmitted(enquirySubmitted);

                 // Add the current attendee to the attendeesMap
                    attendee.addStudent(attendee1);

                    // Print the loaded student details
                    System.out.println("Student ID: " + attendee1.getStudentID());
                    System.out.println("Name: " + attendee1.getName());
                    System.out.println("Password: " + attendee1.getPassword());
                    System.out.println("User Group: " + attendee1.getUserGroup());
                    System.out.println("Faculty: " + attendee1.getFaculty());

                    // Print CampAccessibility
                    System.out.print("Camp Accessibility: ");
                    for (String camp : attendee1.getCampAccessibility()) {
                        System.out.print(camp + "|");
                    }
                    System.out.println();

                    System.out.println("Camp Committee: " + attendee1.getCampCommittee());

                    // Print RegisteredCamps
                    System.out.print("Registered Camps: ");
                    for (Camp camp : attendee1.getRegisteredCamps()) {
                        System.out.print(camp.getCampName() + "|");
                    }
                    System.out.println();

                    // Print SecurityQuestions
                    System.out.print("Security Questions: ");
                    for (String question : attendee1.getSecurityQuestion()) {
                        System.out.print(question + "|");
                    }
                    System.out.println();

                    // Print SecurityAnswers
                    System.out.print("Security Answers: ");
                    for (String answer : attendee1.getSecurityAnswers()) {
                        System.out.print(answer + "|");
                    }
                    System.out.println();

                    // Print EnquirySubmitted
                    System.out.print("Enquiry Submitted: ");
                    for (Enquiry enquiry : attendee1.getEnquirySubmitted()) {
                        System.out.print(enquiry.getEnquiry_Subject() + "|");
                    }
                    System.out.println();

                    System.out.println("Student details printed successfully.");

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }

    public static void updateAttendeeCSVFile(Attendee attendees, String studentId) {
        // Get the path to the CSV file
        String csvFilePath = "attendee.csv";

        // Read the existing content of the CSV file
        List<String> existingLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the existing CSV file content.");
            e.printStackTrace();
            return;  // Exit the method if an error occurs while reading the existing content
        }

        // Create a StringBuilder to store the updated content
        StringBuilder updatedContent = new StringBuilder();

        // Find the student based on the provided studentId from studentUser
        Attendee attendee = Attendee.existingAttendees().get(studentId);

        // Check if the student exists
        if (attendee != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                updatedContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            updatedContent.append(attendee.getStudentID()).append(",");
            updatedContent.append(attendee.getName()).append(",");
            updatedContent.append(attendee.getPassword()).append(",");
            updatedContent.append(attendee.getUserGroup()).append(",");
            updatedContent.append(attendee.getFaculty()).append(",");
            updatedContent.append(String.join("|", attendee.getCampAccessibility())).append(",");
            updatedContent.append(attendee.getCampCommittee()).append(",");
            updatedContent.append(String.join("|", attendee.getRegisteredCamps().stream().map(Camp::getCampName).toArray(String[]::new))).append(",");
            updatedContent.append(String.join("|", attendee.getSecurityQuestion())).append(",");
            updatedContent.append(String.join("|", attendee.getSecurityAnswers())).append(",");
            updatedContent.append(String.join("|", attendee.getEnquirySubmitted().stream().map(Enquiry::getEnquiry_Subject).toArray(String[]::new))).append(",");

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

 
/********************************COMMITTEE*********************************************/
 // Load Attendee from CSV
    public static void loadCommitteeFromCSV(Committee committee) {
        String csvFilePath = "committee.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 12) {
                    // Create a new Student_User instance and set its attributes
                    Committee committee1 = new Committee();
                    committee1.setStudentID(data[0]);
                    committee1.setName(data[1]);
                    committee1.setPassword(data[2]);
                    committee1.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
                    committee1.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV
                    committee1.setPoints(Integer.parseInt(data[5]));
                    
                    // Parse CampAccessibility from the CSV
                    List<String> campAccessibility = new ArrayList<>();
                    String[] campAccessibilityArray = data[6].split("\\|");
                    for (String camp : campAccessibilityArray) {
                        campAccessibility.add(camp);
                    }
                    committee1.setCampAccessibility(campAccessibility);

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[7]);
                    committee1.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    String[] registeredCampsArray = data[8].split("\\|");
                    for (String camp : registeredCampsArray) {
                        Camp registeredCamp = Camp.getCampByName(camp);
                        if (registeredCamp != null) {
                            registeredCamps.add(registeredCamp);
                        }
                    }
                    committee1.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    String[] securityQuestionsArray = data[9].split("\\|");
                    for (String question : securityQuestionsArray) {
                        securityQuestions.add(question);
                    }
                    committee1.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    String[] securityAnswersArray = data[10].split("\\|");
                    for (String answer : securityAnswersArray) {
                        securityAnswers.add(answer);
                    }
                    committee1.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Suggestion> suggestionSubmitted = new ArrayList<>();
                    String[] suggestionSubmittedArray = data[11].split("\\|");
                    for (String suggestion: suggestionSubmittedArray) {
                        Suggestion submittedSuggestion = Suggestion.getSuggestionBySubject(suggestion);
                        if (submittedSuggestion != null) {
                            suggestionSubmitted.add(submittedSuggestion);
                        }
                    }
                    committee1.setSuggestionSubmitted(suggestionSubmitted);
                    
                    // Check if committee is null and instantiate it if needed
                    if (committee == null) {
                        committee = new Committee();
                    }
                    
                 // Add the current attendee to the attendeesMap
                    committee.addCommittee(committee1);

                    // Print the loaded student details
                    System.out.println("Student ID: " + committee1.getStudentID());
                    System.out.println("Name: " + committee1.getName());
                    System.out.println("Password: " + committee1.getPassword());
                    System.out.println("User Group: " + committee1.getUserGroup());
                    System.out.println("Faculty: " + committee1.getFaculty());
                    System.out.println("Point: " + committee1.getPoints());
                    
                    // Print CampAccessibility
                    System.out.print("Camp Accessibility: ");
                    for (String camp : committee1.getCampAccessibility()) {
                        System.out.print(camp + "|");
                    }
                    System.out.println();

                    System.out.println("Camp Committee: " + committee1.getCampCommittee());

                    // Print RegisteredCamps
                    System.out.print("Registered Camps: ");
                    for (Camp camp : committee1.getRegisteredCamps()) {
                        System.out.print(camp.getCampName() + "|");
                    }
                    System.out.println();

                    // Print SecurityQuestions
                    System.out.print("Security Questions: ");
                    for (String question : committee1.getSecurityQuestion()) {
                        System.out.print(question + "|");
                    }
                    System.out.println();

                    // Print SecurityAnswers
                    System.out.print("Security Answers: ");
                    for (String answer : committee1.getSecurityAnswers()) {
                        System.out.print(answer + "|");
                    }
                    System.out.println();

                    // Print EnquirySubmitted
                    System.out.print("Suggestion Submitted: ");
                    for (Suggestion suggestion : committee1.getSuggestionSubmitted()) {
                        System.out.print(suggestion.getSuggestion_Subject() + "|");
                    }
                    System.out.println();

                    System.out.println("Student details printed successfully.");

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }

    public static void updateCommitteeCSVFile(Committee committees, String studentId) {
        // Get the path to the CSV file
        String csvFilePath = "committee.csv";

        // Read the existing content of the CSV file
        List<String> existingLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                existingLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the existing CSV file content.");
            e.printStackTrace();
            return;  // Exit the method if an error occurs while reading the existing content
        }

        // Create a StringBuilder to store the updated content
        StringBuilder updatedContent = new StringBuilder();

        // Find the student based on the provided studentId from studentUser
        Committee committee = Committee.existingCommittee().get(studentId);

        // Check if the student exists
        if (committee != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                updatedContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            updatedContent.append(committee.getStudentID()).append(",");
            updatedContent.append(committee.getName()).append(",");
            updatedContent.append(committee.getPassword()).append(",");
            updatedContent.append(committee.getUserGroup()).append(",");
            updatedContent.append(committee.getFaculty()).append(",");
            updatedContent.append(committee.getPoints()).append(",");
            updatedContent.append(String.join("|", committee.getCampAccessibility())).append(",");
            updatedContent.append(committee.getCampCommittee()).append(",");
            updatedContent.append(String.join("|", committee.getRegisteredCamps().stream().map(Camp::getCampName).toArray(String[]::new))).append(",");
            updatedContent.append(String.join("|", committee.getSecurityQuestion())).append(",");
            updatedContent.append(String.join("|", committee.getSecurityAnswers())).append(",");
            updatedContent.append(String.join("|", committee.getSuggestionSubmitted().stream().map(Suggestion::getSuggestion_Subject).toArray(String[]::new))).append("\n");

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
