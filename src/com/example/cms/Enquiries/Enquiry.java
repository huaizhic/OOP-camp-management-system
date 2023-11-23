package com.example.cms.Enquiries;

import com.example.cms.Camp.Camp;
import com.example.cms.Student.Student_User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Enquiry {
    private String studentID;
    private String campName; 
    private String enquiry_Subject; 
    private static String studentName;  
    private String content;
    private String dateSent;
    private String reply;
    private boolean processed;

    // Initialize a HashMap to store individual student inquiries with enquiry_Subject as the key
    private static HashMap<String, Enquiry> enquiryHashMap = new HashMap<>();

    public static void printAllEnquiryInfo(Enquiry enquiry) {
        System.out.println("Submitted by: " + enquiry.getStudentName());
        System.out.println("Date sent: " + enquiry.getDateSent());
        System.out.println("Enquiry content: " + enquiry.getContent());
        System.out.println("Processed: " + enquiry.isProcessed()); // this might not be necessary 
    }

    public Enquiry(String studentID, String studentName, String campName, String enquiry_Subject, String content, String dateSent) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.campName = campName;
        this.enquiry_Subject = enquiry_Subject;
        this.content = content;
        this.dateSent = dateSent;
        this.reply = null;
        this.processed = false;

        // Create a new Enquiry and add it to the enquiries map with enquiry_Subject as the key
        enquiryHashMap.put(enquiry_Subject, this);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getEnquiry_Subject() {
        return enquiry_Subject;
    }

    public void setEnquiry_Subject(String enquiry_Subject) {
        this.enquiry_Subject = enquiry_Subject;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentID) {
        String studentName = Student_User.getName(studentID);
        if (studentName != null) {
            this.studentName = studentName;
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    public String getCampName() {
    	return campName;
    }
    
    public void setCampName(String campName) {
    	this.campName = campName;
    }

    public static HashMap<String, Enquiry> getEnquiryHashMap() {
        return enquiryHashMap;
    }

    private static void viewEnquiry(String studentID) {

    }
    
    public static Enquiry getEnquiryBySubject(String enquiry) {
        return enquiryHashMap.get(enquiry);
    }
    

    public static void makeEnquiry(String studentID) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the camp name
        System.out.print("Enter the name of the camp: ");
        String campName = scanner.nextLine();

        // Prompt the user to enter their enquiry subject
        System.out.print("Enter your enquiry subject: ");
        String enquiry_Subject = scanner.nextLine();

        // Prompt the user to enter their enquiry content
        System.out.print("Enter your enquiry content: ");
        String content = scanner.nextLine();

        // Print the entered enquiry information
        System.out.println("Enquiry Information:");
        System.out.println("Camp Name: " + campName);
        System.out.println("Subject: " + enquiry_Subject);
        System.out.println("Content: " + content);

        // Ask the user if they want to send or edit the enquiry
        System.out.println("Do you want to:");
        System.out.println("1. Send Enquiry");
        System.out.println("2. Edit Enquiry");
        System.out.println("3. Cancel");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                // Get the current date and format it
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateSent = sdf.format(date);

                // Create a new Enquiry and add it to the enquiries map
                Enquiry newEnquiry = new Enquiry(studentID, studentName, campName, enquiry_Subject, content, dateSent);
                Enquiries.put(newEnquiry.getEnquiry_Subject(), newEnquiry);

                System.out.println("Enquiry sent successfully.");
                break;
            case 2:
                // Edit the enquiry
                System.out.print("Enter your edited enquiry subject: ");
                enquiry_Subject = scanner.nextLine();
                
                System.out.print("Enter your edited enquiry content: ");
                content = scanner.nextLine();

                // Recursively call makeEnquiry to retry the process
                makeEnquiry(studentID);
                break;
            case 3:
                System.out.println("Enquiry creation canceled.");
                break;
            default:
                System.out.println("Invalid choice. Enquiry creation canceled.");
        }
    }

    public static void deleteEnquiry(String studentID) {
        Scanner scanner = new Scanner(System.in);

        // View the enquiries of the student
        viewEnquiry(studentID);

        // Check if the student has any enquiries
        if (enquiries.containsKey(studentID)) {
            List<Enquiry> studentEnquiries = new ArrayList<>(enquiries.values());

            System.out.println("Select the enquiry you want to delete (enter the index) or enter 0 to go back:");
            for (int i = 0; i < studentEnquiries.size(); i++) {
                System.out.println((i + 1) + ". " + studentEnquiries.get(i).getEnquiry_Subject());
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice > 0 && choice <= studentEnquiries.size()) {
                // Valid index, delete the selected enquiry
                Enquiry deletedEnquiry = studentEnquiries.get(choice - 1);
                enquiries.remove(deletedEnquiry.getEnquiry_Subject());
                System.out.println("Enquiry deleted successfully.");
            } else if (choice != 0) {
                System.out.println("Invalid index. No changes made.");
            } else {
                System.out.println("Going back to the previous menu.");
            }
        } else {
            System.out.println("No enquiries found for student with ID: " + studentID);
        }
    }



    /*************************FOR CAMP COMMITEE********************************/

    
     public static int answerEnquiries(String studentID) {
        // Get the list of registered camps for the student
        List<Camp> registeredCamps = Student_User.getStudentById(studentID).getRegisteredCamps();

        if (registeredCamps.isEmpty()) {
            System.out.println("You are not registered for any camps. No enquiries to answer.");
            return 0;
        }

        // Display the registered camps for the user to choose
        System.out.println("Select the registered camp to answer enquiries (enter the index) or enter 0 to go back:");
        for (int i = 0; i < registeredCamps.size(); i++) {
            System.out.println((i + 1) + ". " + registeredCamps.get(i).getCampName());
        }

        Scanner scanner = new Scanner(System.in);
        int campChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (campChoice > 0 && campChoice <= registeredCamps.size()) {
            Camp selectedCamp = registeredCamps.get(campChoice - 1);
            String campName = selectedCamp.getCampName();

            // Check if there are any enquiries related to the selected camp
            if (enquiries.containsKey(campName)) {
                List<Enquiry> campEnquiries = getEnquiriesByCamp(campName);

                // Display enquiries for the selected camp
                System.out.println("Enquiries for Camp: " + campName);
                for (int i = 0; i < campEnquiries.size(); i++) {
                    Enquiry enquiry = campEnquiries.get(i);
                    System.out.println((i + 1) + ". " + "Subject: " + enquiry.getEnquiry_Subject());
                    System.out.println("   Date Sent: " + enquiry.getDateSent());
                    System.out.println("   Content: " + enquiry.getContent());
                    System.out.println("------------------------------------------------------------------------------------------");
                }

                // Ask the user to choose which enquiry to answer
                System.out.println("Select the enquiry to answer (enter the index) or enter 0 to go back:");
                int enquiryChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (enquiryChoice > 0 && enquiryChoice <= campEnquiries.size()) {
                    Enquiry selectedEnquiry = campEnquiries.get(enquiryChoice - 1);

                    // Display student information and enquiry details
                    System.out.println("Attendee Student ID: " + selectedEnquiry.getStudentID());
                    System.out.println("Attendee Name: " + selectedEnquiry.getStudentName());
                    System.out.println("Enquiry Subject: " + selectedEnquiry.getEnquiry_Subject());
                    System.out.println("Enquiry Content: " + selectedEnquiry.getContent());
                    System.out.println("------------------------------------------------------------------------------------------");

                    // Ask for the reply
                    System.out.print("Enter your reply: ");
                    String reply = scanner.nextLine();

                    // Display the reply for confirmation and editing
                    System.out.println("Your Reply: " + reply);
                    System.out.println("Do you want to:");
                    System.out.println("1. Confirm and Send");
                    System.out.println("2. Edit Reply");
                    System.out.println("3. Cancel");

                    int replyChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (replyChoice) {
                        case 1:
                            // Update the enquiry with the reply and mark it as processed
                            selectedEnquiry.setReply(reply);
                            selectedEnquiry.setProcessed(true);

                            System.out.println("Reply sent successfully.");
                            return 1; // Return 1 only if the reply is sent successfully
                        case 2:
                            // Edit the reply
                            System.out.print("Enter your edited reply: ");
                            reply = scanner.nextLine();

                            // Recursively call answerEnquiries to retry the process
                            return answerEnquiries(studentID); // Return the result of the recursive call
                        case 3:
                            System.out.println("Reply canceled.");
                            return 0; // Return 0 if the reply is canceled
                        default:
                            System.out.println("Invalid choice. Reply canceled.");
                            return 0; // Return 0 for any other invalid choice
                    }
                } else if (enquiryChoice != 0) {
                    System.out.println("Invalid index. No changes made.");
                    return 0;
                } else {
                    System.out.println("Going back to the previous menu.");
                }
            } else {
                System.out.println("No enquiries found for the camp: " + campName);
            }
        } else if (campChoice != 0) {
            System.out.println("Invalid index. No changes made.");
        } else {
            System.out.println("Going back to the previous menu.");
        }
		return 0;
    }

    private static List<Enquiry> getEnquiriesByCamp(String campName) {
        List<Enquiry> campEnquiries = new ArrayList<>();

        for (Enquiry enquiry : enquiries.values()) {
            if (enquiry.getCampName().equals(campName)) {
                campEnquiries.add(enquiry);
            }
        }

        return campEnquiries;
    }

    // save newly submitted enquiry to CSV containing all existing enquiries
    public static void saveToCSV(Enquiry newEnquiry){
         String file = "src\\Enquiries.csv";
        try {
            PrintWriter out = new PrintWriter(file);
            // Since our CSV contains commas naturally, use regex instead of commas to separate the cells
            out.println(newEnquiry.getEnquiry_Subject()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newEnquiry.getContent()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newEnquiry.getStudentName()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newEnquiry.getCampName()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newEnquiry.getDateSent() +",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newEnquiry.getReply()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}