package com.example.cms.CSVConverter;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.campData;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Faculty;
import com.example.cms.Staff.Staff;
import com.example.cms.Status;
import com.example.cms.Student.Attendee;
import com.example.cms.Student.Committee;
import com.example.cms.Student.Student_User;
import com.example.cms.Student_Role;
import com.example.cms.Suggestions.Suggestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

                if (data.length >= 14) {
                    // Create a new Student_User instance and set its attributes
                    Student_User student = new Student_User();
                    
                    student.setStudentID(data[0]);
                    student.setName(data[1]);
                    student.setPassword(data[2]);
                    //student.setSalt(data[3]);
                    student.setUserGroup(Student_Role.valueOf(data[4])); // Assuming Student_Role values are in the CSV
                    student.setFaculty(Faculty.valueOf(data[5])); // Assuming Faculty values are in the CSV
                    student.setPoints(Integer.parseInt(data[6]));
                    
                    // Parse CampAccessibility from the CSV
                    List<String> campAccessibility = new ArrayList<>();
                    String[] campAccessibilityArray = data[7].split("\\|");
                    for (String camp : campAccessibilityArray) {
                        campAccessibility.add(camp);
                    }
                    student.setCampAccessibility(campAccessibility);

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[8]);
                    student.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    String[] registeredCampsArray = data[9].split("\\|");
                    for (String camp : registeredCampsArray) {
                        Camp registeredCamp = Camp.getCampByName(camp);
                        if (registeredCamp != null) {
                            registeredCamps.add(registeredCamp);
                        }
                    }
                    student.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    String[] securityQuestionsArray = data[10].split("\\|");
                    for (String question : securityQuestionsArray) {
                        securityQuestions.add(question);
                    }
                    student.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    String[] securityAnswersArray = data[11].split("\\|");
                    for (String answer : securityAnswersArray) {
                        securityAnswers.add(answer);
                    }
                    student.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Enquiry> enquirySubmitted = new ArrayList<>();
                    String[] enquirySubmittedArray = data[12].split("\\|");
                    for (String enquiry : enquirySubmittedArray) {
                        Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
                        if (submittedEnquiry != null) {
                            enquirySubmitted.add(submittedEnquiry);
                        }
                    }
                    student.setEnquirySubmitted(enquirySubmitted);

                    // Parse SuggestionSubmitted from the CSV
                    List<Suggestion> suggestionSubmitted = new ArrayList<>();
                    String[] suggestionSubmittedArray = data[13].split("\\|");
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
                    //System.out.println("Password: " + student.getSalt());
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
          //  updatedContent.append(student.getSalt()).append(",");
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
//    public static void loadAttendeeFromCSV(Attendee attendees) {
//        String csvFilePath = "attendee.csv";
//        
//        if (attendees == null) {
//          // If the attendee is null, create a new one
//          attendees = new Attendee();
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
//            String line;
//            boolean firstLine = true; // flag for header
//
//            while ((line = reader.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    continue; // Skip the first line (headers)
//                }
//
//                String[] data = line.split(",", -1); // Use -1 to keep empty fields
//
//                if (data.length >= 11) {
//                    // Create a new Attendee instance and set its attributes
//                    Attendee attendee = new Attendee();
//
//                    attendee.setStudentID(data[0]);
//                    attendee.setName(data[1]);
//                    attendee.setPassword(data[2]);
//                    attendee.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
//                    attendee.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV
//                    attendee.setPoints(0);
//
//                    // Parse CampAccessibility from the CSV
//                    List<String> campAccessibility = new ArrayList<>();
//                    if (!data[5].isEmpty()) {
//                        String[] campAccessibilityArray = data[5].split("\\|");
//                        campAccessibility.addAll(Arrays.asList(campAccessibilityArray));
//                    }
//                    attendee.setCampAccessibility(campAccessibility);
//
//                    // Parse CampCommittee from the CSV
//                    boolean campCommittee = Boolean.parseBoolean(data[6]);
//                    attendee.setCampCommittee(campCommittee);
//
//                    // Parse RegisteredCamps from the CSV
//                    List<Camp> registeredCamps = new ArrayList<>();
//                    if (!data[7].isEmpty()) {
//                        String[] registeredCampsArray = data[7].split("\\|");
//                        for (String camp : registeredCampsArray) {
//                            Camp registeredCamp = Camp.getCampByName(camp);
//                            if (registeredCamp != null) {
//                                registeredCamps.add(registeredCamp);
//                            }
//                        }
//                    }
//                    attendee.setRegisteredCamps(registeredCamps);
//
//                    // Parse SecurityQuestions from the CSV
//                    List<String> securityQuestions = new ArrayList<>();
//                    if (!data[8].isEmpty()) {
//                        String[] securityQuestionsArray = data[8].split("\\|");
//                        securityQuestions.addAll(Arrays.asList(securityQuestionsArray));
//                    }
//                    attendee.setSecurityQuestions(securityQuestions);
//
//                    // Parse SecurityAnswers from the CSV
//                    List<String> securityAnswers = new ArrayList<>();
//                    if (!data[9].isEmpty()) {
//                        String[] securityAnswersArray = data[9].split("\\|");
//                        securityAnswers.addAll(Arrays.asList(securityAnswersArray));
//                    }
//                    attendee.setSecurityAnswers(securityAnswers);
//
//                    // Parse EnquirySubmitted from the CSV
//                    List<Enquiry> enquirySubmitted = new ArrayList<>();
//                    if (!data[10].isEmpty()) {
//                        String[] enquirySubmittedArray = data[10].split("\\|");
//                        for (String enquiry : enquirySubmittedArray) {
//                            Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
//                            if (submittedEnquiry != null) {
//                                enquirySubmitted.add(submittedEnquiry);
//                            }
//                        }
//                    }
//                    attendee.setEnquirySubmitted(enquirySubmitted);
//
//                    // Set SuggestionSubmitted to null (assuming this field is not present in the CSV)
//                    attendee.setSuggestionSubmitted(null);
//
//                    System.out.println("Attendee.csv loaded successfully in loadcsvmethod:");
//
//                    // Print the loaded attendee details
//                    System.out.println("Student ID: " + attendee.getStudentID());
//                    System.out.println("Name: " + attendee.getName());
//                    // Add more details based on your Attendee class properties
//
//                    // Add the loaded attendee to the attendeesMap
//                    attendee.addAttendee(attendee);
//
//                    // Print additional details if needed
//                    System.out.println("Attendee details printed successfully.");
//
//                } else {
//                    System.out.println("Incomplete data in the CSV line: " + line);
//                    System.out.println("Number of fields: " + data.length);
//                    System.out.println("Data: " + Arrays.toString(data));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while reading from the CSV file.");
//            e.printStackTrace();
//        }
//    }

    public static Attendee loadAttendeeFromCSV(Attendee attendee) {
        String csvFilePath = "attendee.csv";
        
     // If the attendee is null, create a new one
        if (attendee == null) {
            attendee = new Attendee();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 11) {
                    // Set the attributes of the existing Attendee instead of creating a new one
                    attendee.setStudentID(data[0]);
                    attendee.setName(data[1]);
                    attendee.setPassword(data[2]);
                   // attendee.setSalt(null);
                    attendee.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
                    attendee.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV
                    attendee.setPoints(0);

                    // Parse CampAccessibility from the CSV
                    List<String> campAccessibility = new ArrayList<>();
                    if (!data[5].isEmpty()) {
                        String[] campAccessibilityArray = data[5].split("\\|");
                        for (String camp : campAccessibilityArray) {
                            campAccessibility.add(camp);
                        }
                    }
                    attendee.setCampAccessibility(campAccessibility);

                    // Parse CampCommittee from the CSV
                    boolean campCommittee = Boolean.parseBoolean(data[6]);
                    attendee.setCampCommittee(campCommittee);

                    // Parse RegisteredCamps from the CSV
                    List<Camp> registeredCamps = new ArrayList<>();
                    if (!data[7].isEmpty()) {
                        String[] registeredCampsArray = data[7].split("\\|");
                        for (String camp : registeredCampsArray) {
                            Camp registeredCamp = Camp.getCampByName(camp);
                            if (registeredCamp != null) {
                                registeredCamps.add(registeredCamp);
                            }
                        }
                    }
                    attendee.setRegisteredCamps(registeredCamps);

                    // Parse SecurityQuestions from the CSV
                    List<String> securityQuestions = new ArrayList<>();
                    if (!data[8].isEmpty()) {
                        String[] securityQuestionsArray = data[8].split("\\|");
                        for (String question : securityQuestionsArray) {
                            securityQuestions.add(question);
                        }
                    }
                    attendee.setSecurityQuestions(securityQuestions);

                    // Parse SecurityAnswers from the CSV
                    List<String> securityAnswers = new ArrayList<>();
                    if (!data[9].isEmpty()) {
                        String[] securityAnswersArray = data[9].split("\\|");
                        for (String answer : securityAnswersArray) {
                            securityAnswers.add(answer);
                        }
                    }
                    attendee.setSecurityAnswers(securityAnswers);

                    // Parse EnquirySubmitted from the CSV
                    List<Enquiry> enquirySubmitted = new ArrayList<>();
                    if (!data[10].isEmpty()) {
                        String[] enquirySubmittedArray = data[10].split("\\|");
                        for (String enquiry : enquirySubmittedArray) {
                            Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
                            if (submittedEnquiry != null) {
                                enquirySubmitted.add(submittedEnquiry);
                            }
                        }
                    }
                    attendee.setEnquirySubmitted(enquirySubmitted);

                    // Set SuggestionSubmitted to null (assuming this field is not present in the CSV)
                    attendee.setSuggestionSubmitted(null);

                    // Print success message and details about the loaded attendee
                    System.out.println("Attendee loaded successfully:");
                    System.out.println("Student ID: " + attendee.getStudentID());
                    System.out.println("Name: " + attendee.getName());

                    // You may want to save the updated attendee to a file or perform other actions.

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                    System.out.println("Number of fields: " + data.length);
                    System.out.println("Data: " + Arrays.toString(data));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }

        return attendee;
    }



    
    
    // Load Attendee from CSV
//    public static void loadAttendeeFromCSV(Attendee attendee) {
//        String csvFilePath = "attendee.csv";
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
//            String line;
//            boolean firstLine = true; // flag for header
//
//            while ((line = reader.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    continue; // Skip the first line (headers)
//                }
//
//                String[] data = line.split(",", -1); // Use -1 to keep empty fields
//
//                if (data.length >= 12) {
//                    // Create a new Student_User instance and set its attributes
//                    Attendee attendee1 = new Attendee();
//                    attendee1.setStudentID(data[0]);
//                    attendee1.setName(data[1]);
//                    attendee1.setPassword(data[2]);
//                    attendee1.setUserGroup(Student_Role.valueOf(data[3])); // Assuming Student_Role values are in the CSV
//                    attendee1.setFaculty(Faculty.valueOf(data[4])); // Assuming Faculty values are in the CSV
//
//                    // Parse CampAccessibility from the CSV
//                    List<String> campAccessibility = new ArrayList<>();
//                    String[] campAccessibilityArray = data[5].split("\\|");
//                    for (String camp : campAccessibilityArray) {
//                        campAccessibility.add(camp);
//                    }
//                    attendee1.setCampAccessibility(campAccessibility);
//
//                    // Parse CampCommittee from the CSV
//                    boolean campCommittee = Boolean.parseBoolean(data[6]);
//                    attendee1.setCampCommittee(campCommittee);
//
//                    List<Camp> registeredCamps = new ArrayList<>();
//                    if (!data[7].isEmpty()) {
//                        String[] registeredCampsArray = data[7].split("\\|");
//                        for (String camp : registeredCampsArray) {
//                            Camp registeredCamp = Camp.getCampByName(camp);
//                            if (registeredCamp != null) {
//                                registeredCamps.add(registeredCamp);
//                            }
//                        }
//                    }
//                    attendee1.setRegisteredCamps(registeredCamps);
//                    
//                    // Parse SecurityQuestions from the CSV
//                    List<String> securityQuestions = new ArrayList<>();
//                    String[] securityQuestionsArray = data[8].split("\\|");
//                    for (String question : securityQuestionsArray) {
//                        securityQuestions.add(question);
//                    }
//                    attendee1.setSecurityQuestions(securityQuestions);
//
//                 // Parse SecurityAnswers from the CSV
//                    List<String> securityAnswers = new ArrayList<>();
//                    String[] securityAnswersArray = data[9].split("\\|");
//                    for (String answer : securityAnswersArray) {
//                        securityAnswers.add(answer);
//                    }
//                    attendee1.setSecurityAnswers(securityAnswers);
//                   
//                    // Parse EnquirySubmitted from the CSV
//                    List<Enquiry> enquirySubmitted = new ArrayList<>();
//                    if (!data[10].isEmpty()) {
//                        String[] enquirySubmittedArray = data[10].split("\\|");
//                        for (String enquiry : enquirySubmittedArray) {
//                            Enquiry submittedEnquiry = Enquiry.getEnquiryBySubject(enquiry);
//                            if (submittedEnquiry != null) {
//                                enquirySubmitted.add(submittedEnquiry);
//                            }
//                        }
//                    }
//                    attendee1.setEnquirySubmitted(enquirySubmitted);
//
//                 // Add the current attendee to the attendeesMap
//                    attendee.addAttendee(attendee1);
//                    
//                    // Print the loaded student details
//                    System.out.println("Student ID: " + attendee1.getStudentID());
//                    System.out.println("Name: " + attendee1.getName());
//                    System.out.println("Password: " + attendee1.getPassword());
//                    System.out.println("User Group: " + attendee1.getUserGroup());
//                    System.out.println("Faculty: " + attendee1.getFaculty());
//
//                    // Print CampAccessibility
//                    System.out.print("Camp Accessibility: ");
//                    for (String camp : attendee1.getCampAccessibility()) {
//                        System.out.print(camp + "|");
//                    }
//                    System.out.println();
//
//                    System.out.println("Camp Committee: " + attendee1.getCampCommittee());
//
//                    // Print RegisteredCamps
//                    System.out.print("Registered Camps: ");
//                    for (Camp camp : attendee1.getRegisteredCamps()) {
//                        System.out.print(camp.getCampName() + "|");
//                    }
//                    System.out.println();
//
//                    // Print SecurityQuestions
//                    System.out.print("Security Questions: ");
//                    for (String question : attendee1.getSecurityQuestion()) {
//                        System.out.print(question + "|");
//                    }
//                    System.out.println();
//
//                    // Print SecurityAnswers
//                    System.out.print("Security Answers: ");
//                    for (String answer : attendee1.getSecurityAnswers()) {
//                        System.out.print(answer + "|");
//                    }
//                    System.out.println();
//
//                    // Print EnquirySubmitted
//                    System.out.print("Enquiry Submitted: ");
//                    for (Enquiry enquiry : attendee1.getEnquirySubmitted()) {
//                        System.out.print(enquiry.getEnquiry_Subject() + "|");
//                    }
//                    System.out.println();
//
//                    System.out.println("Student details printed successfully.");
//
//                } else {
//                    System.out.println("Incomplete data in the CSV line: " + line);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while reading from the CSV file.");
//            e.printStackTrace();
//        }
//    }

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
        Attendee attendee = new Attendee();
       attendee = attendee.existingAttendees().get(studentId);

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

    /********************************Staff*********************************************/

    public static void loadStaffsFromCSV() {
        String csvFilePath = "staff.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                if (data.length >= 8) {
                    Staff newStaff = new Staff(data[0], data[1], Faculty.valueOf(data[3]), data[5], data[6]);
                    newStaff.setPassword(data[3]);


                    String[] createdCampsArray = data[4].split("\\|");
                    for (String camp : createdCampsArray) {
                        Camp camp1 = Camp.getCampByName(camp);
                            newStaff.setCampsCreated(camp1);
                    }

                    Staff.getExistingStaff().put(newStaff.getStaffID(), newStaff);
                    Staff.getStaffByNameMap().put(newStaff.getName(), newStaff);

                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }


    public static void updateStaffCSVFile(Staff staff, String studentId) {
        // Get the path to the CSV file
        String csvFilePath = "staff.csv";

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

        // Check if the student exists
        if (staff != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                updatedContent.append(existingLines.get(0)).append("\n");
            }

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
            System.out.println("Staff not found. CSV file not updated.");
        }
    }

    /********************************Camp*********************************************/

    public static void loadCampsFromCSV() {
        String csvFilePath = "camp.csv";

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

                    Camp camp = new Camp(
                            data[0], // Camp Name
                            parseDatesFromString(data[1]), // Start and End Dates
                            LocalDate.parse(data[2]), // Reg Close Date
                            null, //user group
                            data[4], // Location
                            Integer.parseInt(data[5]), // Total Slots
                            Integer.parseInt(data[6]), // remaining slots
                            Integer.parseInt(data[8]),
                            data[9], // Staff in Charge
                            Boolean.parseBoolean(data[10]) // Visibility
                    );


                    String[] UserGroupArray = data[3].split("\\|");
                    ArrayList<Faculty> userGroupList = new ArrayList<>();
                    for (String userGroupStr : UserGroupArray) {
                        Faculty userGroup = Faculty.valueOf(userGroupStr);
                        userGroupList.add(userGroup);
                    }
                    camp.setUserGroup(userGroupList);

                    campData.addCampToMap(camp.getCampName(), camp);
                    campData.getCampList().add(camp);


                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }


    private static ArrayList<LocalDate> parseDatesFromString(String input) {
        String[] dateStrings = input.split("\\|");
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (String dateString : dateStrings) {
            dates.add(LocalDate.parse(dateString));
        }
        return dates;
    }


    public static void updateCampCSVFile(Camp camp) {
        // Get the path to the CSV file
        String csvFilePath = "camp.csv";

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
        StringBuilder campContent = new StringBuilder();

        // Check if the student exists
        if (camp != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                campContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            campContent.append(camp.getCampName()).append(",");
            campContent.append(formatDates(camp.getCampDates())).append(",");
            campContent.append(camp.getRegCloseDate()).append(",");
            campContent.append(String.join("|", camp.getUserGroup().stream().map(Faculty::name).toArray(String[]::new))).append(",");
            campContent.append(camp.getLocation()).append(",");
            campContent.append(camp.getTotalSlots()).append(",");
            campContent.append(camp.getRemainingSlots()).append(",");
            campContent.append(camp.getCommitteeSlots()).append(",");
            campContent.append(camp.getRemainingCommitteeSlots()).append(",");
            campContent.append(camp.getStaffInCharge()).append(",");
            campContent.append(camp.getVisibility()).append("\n");

            // Write the updated content to the CSV file
            try (FileWriter writer = new FileWriter(csvFilePath)) {
                writer.write(campContent.toString());
                System.out.println("CSV file updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the CSV file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Staff not found. CSV file not updated.");
        }
    }

    private static String formatDates(ArrayList<LocalDate> dates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dates.get(0).format(formatter) + "|" + dates.get(1).format(formatter);
    }

    /********************************Suggestions*********************************************/

    public static void loadSuggestionFromCSV() {
        String csvFilePath = "suggestion.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                if (data.length >= 6) {

                    Suggestion newSuggestion = new Suggestion(
                            data[0], // Camp Name
                            Committee.getCommitteeNameMap().get(data[1]), // Start and End Dates
                            data[2], // content
                            LocalDate.parse(data[3], formatter),
                            Status.valueOf(data[4]),
                            false // Total Slots
                    );

                    boolean processed;

                    processed = data[5].equals("true");

                    newSuggestion.setProcessed(processed);

                    Suggestion.getSuggestionArrayList().add(newSuggestion);
                    Suggestion.getSuggestionHashMap().put(newSuggestion.getSuggestion_Subject(), newSuggestion);

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }
    public static void updateSuggestionCSVFile(Suggestion suggestion) {
        // Get the path to the CSV file
        String csvFilePath = "camp.csv";

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
        StringBuilder campContent = new StringBuilder();

        // Check if the student exists
        if (suggestion != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                campContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            campContent.append(suggestion.getSuggestion_Subject()).append(",");
            campContent.append(suggestion.getSubmitter().getName()).append(",");
            campContent.append(suggestion.getContent()).append(",");
            campContent.append(suggestion.getDateSubmitted()).append(",");
            campContent.append(suggestion.getStatus()).append(",");
            campContent.append(suggestion.getProcessed()).append("\n");

            // Write the updated content to the CSV file
            try (FileWriter writer = new FileWriter(csvFilePath)) {
                writer.write(campContent.toString());
                System.out.println("CSV file updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the CSV file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Suggestion not found. CSV file not updated.");
        }
    }

    /********************************Enquirys*********************************************/

    public static void loadEnquiryFromCSV() {
        String csvFilePath = "enquiry.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true; // flag for header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (headers)
                }

                String[] data = line.split(",", -1); // Use -1 to keep empty fields

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                if (data.length >= 6) {

                    Enquiry newEnquiry = new Enquiry(
                            data[0], // Camp Name
                            data[1], // Start and End Dates
                            LocalDate.parse(data[2], formatter), // content
                            data[3],
                            data[4],
                            false // Total Slots
                    );

                    boolean processed;

                    processed = data[5].equals("true");

                    newEnquiry.setProcessed(processed);

                    Enquiry.getEnquiryHashMap().put(newEnquiry.getEnquiry_Subject(), newEnquiry);
                    Enquiry.getEnquiryArrayList().add(newEnquiry);

                } else {
                    System.out.println("Incomplete data in the CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the CSV file.");
            e.printStackTrace();
        }
    }
    public static void updateEnquiryCSVFile(Enquiry enquiry) {
        // Get the path to the CSV file
        String csvFilePath = "enquiry.csv";

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
        StringBuilder enquiryContent = new StringBuilder();

        // Check if the student exists
        if (enquiry != null) {
            // Append the header to the StringBuilder if the existing content is not empty
            if (!existingLines.isEmpty()) {
                enquiryContent.append(existingLines.get(0)).append("\n");
            }

            // Append the information for the specific student to the StringBuilder
            enquiryContent.append(enquiry.getContent()).append(",");
            enquiryContent.append(enquiry.getContent()).append(",");
            enquiryContent.append(enquiry.getDateSent()).append(","); //date
            enquiryContent.append(enquiry.getSubmitter()).append(",");
            enquiryContent.append(enquiry.getReply()).append(",");
            enquiryContent.append(enquiry.isProcessed()).append("\n");

            // Write the updated content to the CSV file
            try (FileWriter writer = new FileWriter(csvFilePath)) {
                writer.write(enquiryContent.toString());
                System.out.println("CSV file updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the CSV file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Suggestion not found. CSV file not updated.");
        }
    }
}
