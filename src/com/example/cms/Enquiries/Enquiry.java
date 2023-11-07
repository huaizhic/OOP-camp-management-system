package com.example.cms.Enquiries;

import java.text.SimpleDateFormat;
import java.util.*;

import com.example.cms.Student.student_User;

public class Enquiry {
    private String studentID;
    private String studentName;
    private String content;
    private String dateSent;
    private String reply;
    private boolean processed;

    // Initialize a HashMap to store individual student inquiries
    private Map<String, Enquiry> enquiries = new HashMap<>();

    // Initialize a list to store separate HashMaps for inquiries of different students
    private List<Map<String, Enquiry>> inquiryList = new ArrayList<>();

    public Enquiry(String studentID, String studentName, String content, String dateSent, String reply, boolean processed) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.content = content;
        this.dateSent = dateSent;
        this.reply = reply;
        this.processed = processed;

        // Create a new Enquiry and add it to the enquiries map
        enquiries.put(studentID, this);

        // Create a new HashMap for this student's inquiry and add it to the list
        Map<String, Enquiry> newInquiry = new HashMap<>();
        newInquiry.put(studentID, this);
        inquiryList.add(newInquiry);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentID) {
        String studentName = student_User.getName(studentID);
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

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Hello, " + studentName + "!");
            System.out.println("Please select an option:");
            System.out.println("1 - Display Enquiries");
            System.out.println("2 - Create Enquiries");
            System.out.println("3 - Go Back or Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayEnquiries(studentID);
                    break;
                case 2:
                    createEnquiry(studentID, studentName);
                    break;
                case 3:
                    System.out.println("Going back or exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void displayEnquiries(String studentID) {
        System.out.println("Enquiries sent by student with ID: " + studentID);
        System.out.println("------------------------------------------------------------------------------------------");

        if (enquiries.containsKey(studentID)) {
            Enquiry enquiry = enquiries.get(studentID);
            System.out.println("| Date of Enquiry Sent | " + enquiry.getDateSent());
            System.out.println("| Student Name         | " + enquiry.getStudentName());
            System.out.println("| Enquiry Content      | " + enquiry.getContent());

            if (enquiry.getReply() != null) {
                System.out.println("| Date of Response     | " + enquiry.getDateSent()); // Assuming you meant to display the sent date
                System.out.println("| Response Content     | " + enquiry.getReply());
            } else {
                System.out.println("| Date of Response     | N/A");
                System.out.println("| Response Content     | Pending");
            }
        } else {
            System.out.println("No enquiries found for student with ID: " + studentID);
        }

        System.out.println("------------------------------------------------------------------------------------------");
    }


    public void createEnquiry(String studentID, String studentName) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their enquiry content
        System.out.print("Enter your enquiry: ");
        String content = scanner.nextLine();

        // Allow the user to review and edit the enquiry
        System.out.println("Review your enquiry:");
        System.out.println("1. Content: " + content);
        System.out.println("2. Edit Enquiry");
        System.out.println("3. Send Enquiry");
        System.out.println("4. Cancel");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                // Content is fine, proceed
                break;
            case 2:
                // Edit the enquiry
                System.out.print("Enter your edited enquiry: ");
                content = scanner.nextLine();
                break;
            case 3:
                // Get the current date and format it
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateSent = sdf.format(date);

                // Create a new Enquiry and add it to the enquiries map
                Enquiry newEnquiry = new Enquiry(studentID, studentName, content, dateSent, null, false);
                enquiries.put(newEnquiry.getStudentID(), newEnquiry);

                // Create a new HashMap for this student's inquiry and add it to the list
                Map<String, Enquiry> newInquiry = new HashMap<>();
                newInquiry.put(newEnquiry.getStudentID(), newEnquiry);
                inquiryList.add(newInquiry);

                System.out.println("Enquiry sent successfully.");
                break;
            case 4:
                System.out.println("Enquiry creation canceled.");
                break;
            default:
                System.out.println("Invalid choice. Enquiry creation canceled.");
        }
    }
    
    public static void main(String[] args) {
        Enquiry enquiry = new Enquiry("studentID1", "", "", "", "", false);
        enquiry.setStudentName("studentID1");
        enquiry.start();
    }
}