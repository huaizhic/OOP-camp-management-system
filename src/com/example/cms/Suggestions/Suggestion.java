package com.example.cms.Suggestions;

import com.example.cms.Status;
import com.example.cms.Camp.Camp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Suggestion {
    private String studentID;
    private Camp campName;
    private String suggestion_Subject;
    private static String studentName;
    private String content;
    private String dateSubmitted;
    private Status status;
    private boolean processed = false;

 // Use suggestion_Subject as the key for the HashMap
    private static HashMap<String, Suggestion> suggestionHashMap = new HashMap<>();
    private static ArrayList<Suggestion> suggestionArrayList = new ArrayList<>();

    public Suggestion(String suggestion_Subject, String content, String dateSubmitted2) {
        this.suggestion_Subject = suggestion_Subject;
        this.content = content;
        this.dateSubmitted = dateSubmitted2;
        this.status = Status.Pending;
        // Add the suggestion to the HashMap using suggestion_Subject as the key
        suggestionHashMap.put(suggestion_Subject, this);
    }

 // Getter and setter for studentID
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Getter and setter for campName
    public Camp getCampName() {
        return campName;
    }

    public void setCampName(Camp campName2) {
        this.campName = campName2;
    }

    // Getter and setter for suggestion_Subject
    public String getSuggestion_Subject() {
        return suggestion_Subject;
    }

    public void setSuggestion_Subject(String suggestion_Subject) {
        this.suggestion_Subject = suggestion_Subject;
    }

    // Getter and setter for studentName
    public static String getStudentName() {
        return studentName;
    }

    public static void setStudentName(String studentName) {
        Suggestion.studentName = studentName;
    }

    // Getter and setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and setter for dateSubmitted
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    // Getter and setter for status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter and setter for processed
    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    // Getter for suggestionHashMap
    public static HashMap<String, Suggestion> getSuggestionHashMap() {
        return suggestionHashMap;
    }

    // Getter for suggestionArrayList
    public static ArrayList<Suggestion> getSuggestionArrayList() {
        return suggestionArrayList;
    }

    // Add a suggestion to the suggestionArrayList
    public static void addSuggestionToList(Suggestion suggestion) {
        suggestionArrayList.add(suggestion);
    }

	public static void printSuggestionInfo(Suggestion suggestion){
		System.out.println("The subject: " + suggestion.getSuggestion_Subject());
		System.out.println("The submitter: " + Suggestion.getStudentName());
		System.out.println("The date submitted: " + suggestion.getDateSubmitted());
		System.out.println("The content: "+ suggestion.getContent());
		System.out.println("The status: " + suggestion.getStatus());
	}
	
	public static void viewSuggestion(String studentId) {
	    if (suggestionHashMap.containsKey(studentId)) {
	        Suggestion suggestion = suggestionHashMap.get(studentId);

	        System.out.println("Suggestion Subject: " + suggestion.getSuggestion_Subject());
	        System.out.println("Date of Suggestion Submitted: " + suggestion.getDateSubmitted());
	        System.out.println("Student Name: " + Suggestion.getStudentName());
	        System.out.println("Suggestion Content: " + suggestion.getContent());
	        System.out.println("Suggestion Status: " + suggestion.getStatus());

	        if (suggestion.isProcessed()) {
	            System.out.println("Date of Processing: " + suggestion.getDateSubmitted()); // Assuming you meant to display the submitted date
	            System.out.println("Processing Status: Processed");
	            System.out.println("Processed Content: " + suggestion.getContent());
	        } else {
	            System.out.println("Date of Processing: N/A");
	            System.out.println("Processing Status: Pending");
	            System.out.println("Processed Content: N/A");
	        }

	        System.out.println("------------------------------------------------------------------------------------------");
	    } else {
	        System.out.println("No suggestion found for studentId: " + studentId);
	    }
	}
	
	public static void makeSuggestion(String studentID, ArrayList<Camp> registeredCamps) {
        Scanner scanner = new Scanner(System.in);

        // Display the registered camps for the user to choose
        System.out.println("Select the registered camp to make a suggestion (enter the index) or enter 0 to cancel:");
        for (int i = 0; i < registeredCamps.size(); i++) {
            System.out.println((i + 1) + ". " + registeredCamps.get(i));
        }

        int campChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (campChoice > 0 && campChoice <= registeredCamps.size()) {
            Camp campName = registeredCamps.get(campChoice - 1);

            // Prompt the user to enter their suggestion subject
            System.out.print("Enter your suggestion subject: ");
            String suggestion_Subject = scanner.nextLine();

            // Prompt the user to enter their suggestion content
            System.out.print("Enter your suggestion content: ");
            String content = scanner.nextLine();

            // Print the entered suggestion information
            System.out.println("Suggestion Information:");
            System.out.println("Camp Name: " + campName);
            System.out.println("Subject: " + suggestion_Subject);
            System.out.println("Content: " + content);

            // Ask the user if they want to send or cancel the suggestion
            System.out.println("Do you want to:");
            System.out.println("1. Send Suggestion");
            System.out.println("2. Cancel");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Get the current date and format it
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateSubmitted = sdf.format(date);

                    // Create a new Suggestion and add it to the suggestionHashMap
                    Suggestion newSuggestion = new Suggestion(suggestion_Subject, content, dateSubmitted);
                    newSuggestion.setStudentID(studentID); // Set the studentID for the suggestion
                    newSuggestion.setCampName(campName); // Set the campName for the suggestion

                    // Add the suggestion to the suggestionHashMap using suggestion_Subject as the key
                    suggestionHashMap.put(suggestion_Subject, newSuggestion);

                    System.out.println("Suggestion sent successfully.");
                    break;
                case 2:
                    System.out.println("Suggestion creation canceled.");
                    break;
                default:
                    System.out.println("Invalid choice. Suggestion creation canceled.");
            }
        } else if (campChoice != 0) {
            System.out.println("Invalid index. Suggestion creation canceled.");
        } else {
            System.out.println("Suggestion creation canceled.");
        }
    }

	
	public static void deleteSuggestion(String studentID, ArrayList<Camp> registeredCamps) {
	    Scanner scanner = new Scanner(System.in);

	    // View the suggestions of the student
	    viewSuggestion(studentID);

	    // Check if the student has any suggestions
	    if (suggestionHashMap.containsKey(studentID)) {
	        List<Suggestion> studentSuggestions = new ArrayList<>(suggestionHashMap.values());

	        System.out.println("Select the suggestion you want to delete (enter the index) or enter 0 to go back:");
	        for (int i = 0; i < studentSuggestions.size(); i++) {
	            System.out.println((i + 1) + ". " + studentSuggestions.get(i).getSuggestion_Subject());
	        }

	        int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        if (choice > 0 && choice <= studentSuggestions.size()) {
	            // Valid index, delete the selected suggestion
	            Suggestion deletedSuggestion = studentSuggestions.get(choice - 1);
	            suggestionHashMap.remove(deletedSuggestion.getSuggestion_Subject());
	            System.out.println("Suggestion deleted successfully.");
	        } else if (choice != 0) {
	            System.out.println("Invalid index. No changes made.");
	        } else {
	            System.out.println("Going back to the previous menu.");
	        }
	    } else {
	        System.out.println("No suggestions found for student with ID: " + studentID);
	    }
	}



}

