package com.example.cms.Enquiries;

import java.text.SimpleDateFormat;
import java.util.*;

import com.example.cms.Student.Student_User;

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
    private static Map<String, Enquiry> enquiries = new HashMap<>();

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
        enquiries.put(enquiry_Subject, this);
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


    
    public static void viewEnquiry(String studentID) {
        if (enquiries.containsKey(studentID)) {
            Enquiry studentEnquiry = enquiries.get(studentID);

            System.out.println("Enquiry Subject: " + studentEnquiry.getEnquiry_Subject());
            System.out.println("Date of Enquiry Sent: " + studentEnquiry.getDateSent());
            System.out.println("Student Name: " + studentEnquiry.getStudentName());
            System.out.println("Enquiry Content: " + studentEnquiry.getContent());

            if (studentEnquiry.getReply() != null) {
                System.out.println("Date of Response: " + studentEnquiry.getDateSent()); // Assuming you meant to display the sent date
                System.out.println("Response Content: " + studentEnquiry.getReply());
            } else {
                System.out.println("Date of Response: N/A");
                System.out.println("Response Content: Pending");
            }

            System.out.println("------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No enquiries found for student with ID: " + studentID);
        }
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
                enquiries.put(newEnquiry.getEnquiry_Subject(), newEnquiry);

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
}