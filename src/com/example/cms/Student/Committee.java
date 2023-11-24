package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.DisplayOptions.DisplayApp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Faculty;
import com.example.cms.Format;
import com.example.cms.Suggestions.Suggestion;
import com.example.cms.generate_report.CommitteeGenerateReport;
import com.example.cms.generate_report.GenerateReport;

import java.time.LocalDate;
import java.util.*;

public class Committee extends Student_User {
	private static Map<String, Committee> committeeMap = new HashMap<>();
    private Camp registeredCamp;
    private ArrayList<Camp> accessibleCamp;

    // Add a list to store suggestions associated with the committee
    private ArrayList<Suggestion> suggestionsSubmitted;

    public Committee() {
        super();
        this.accessibleCamp = new ArrayList<>();
        this.suggestionsSubmitted = new ArrayList<>();
        committeeMap.put(studentID, this); // Use studentID as the key
    }

    public static Map<String, Committee> getCommitteeMap() {
        return committeeMap;
    }

 // Method to add an attendee
    public void addCommittee(Committee newCommittee) {
        committeeMap.put(newCommittee.getStudentID(), newCommittee);
        // You may want to save the updated attendeesMap to a file or perform other actions.
    }

    // Method to get existing attendees
    public static Map<String, Committee> existingCommittee() {
        return committeeMap;
    }
    
    public ArrayList<Suggestion> getSuggestions() {
        return suggestionsSubmitted;
    }

    public void setSuggestions(Suggestion suggestion) {this.suggestionsSubmitted.add(suggestion);};


    public Camp getRegisteredCamp() {
        return registeredCamp;
    }

    public void setRegisteredCamp(Camp registeredCamp) {
        this.registeredCamp = registeredCamp;
    }

    public static void manageCamp(Committee committee) {
        boolean exit = false;
        do {
            System.out.println("Camp Management Menu:");
            System.out.println("1. View Camps");
            System.out.println("2. Generate List from a Camp");
            System.out.println("3. Back to Main Menu");
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);

                switch (choice) {
                    case 1:
                        viewAllCamp(committee);
                        break;
                    case 2:
                        committee.generateReport(committee);
                        break;
                 case 3:
                     exit = true;
                     break;
                 default:
                     System.out.println("Invalid choice. Please enter a valid option.");
                     break;
                }
        }while(!exit);
    }

    public static void viewAllCamp(Committee committee) {
        ArrayList<Camp> campArrayList = DisplayApp.viewAllCamp();
        if(campArrayList == null){
            return;
        }
        ArrayList<Camp> eligibleCamp = new ArrayList<>();
        for(Camp camp : campArrayList){
            if(camp.getVisibility() && (camp.getUserGroup().contains(Faculty.ALL) || camp.getUserGroup().contains(committee.getFaculty()))){
                eligibleCamp.add(camp);
            }
        }
        if(eligibleCamp.isEmpty()){
            System.out.println("No camp available to view, exiting...");
            return;
        }

        for(Camp camp : eligibleCamp){
            Camp.printAllCampInfo(camp);
        }

    }

    
    // Example conversion function
    /*private static Camp convertToCamp(camp_Test_Data campData) {
        // Create a new Camp object and set its properties based on camp_Test_Data
        Camp camp = new Camp();
        camp.setCampName(campData.getcamp_Test_DataName());
        // Set other properties...

        return camp;
    }*/
    
/*************************FOR MANAGE ENQUIRIES**********************************/

    public static void manageEnquiries(Committee committee) {
        boolean exit = false;
        do {
            System.out.println("Enquiries Management Menu:");
            System.out.println("1. View Enquiries");
            System.out.println("2. Reply Enquiries");
            System.out.println("3. Back to Main Menu");
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);
                switch (choice) {
                    case 1:
                        committee.viewEnquiries(committee);
                        break;
                    case 2:
                        committee.answerEnquiries(committee);
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
        }while(!exit);
    }

    public void viewEnquiries(Committee committee){
        Camp campOfEnquiry = committee.getRegisteredCamp();
        if(campOfEnquiry.getEnquiry().isEmpty()){
            System.out.println("No enquiry has been submitted, exiting...");
            return;
        }
        System.out.println("The enquiry displayed will be related to the camp: " + campOfEnquiry.getCampName());
        for(Enquiry enquiry:campOfEnquiry.getEnquiry()){
            Enquiry.printAllEnquiryInfo(enquiry);
        }

    }

    public void answerEnquiries(Committee committee) {
        Scanner input = new Scanner(System.in);
        committee.viewEnquiries(committee);
        Enquiry enquiryToBeAnswer;
        boolean exitAnswerEnquiry = false;
        do {
            do {
                System.out.println("Please select the enquiry that you want to reply by SUBJECT or enter exit in lower case to exit");
                String enquiryToBeAnsStr = input.nextLine();
                if (enquiryToBeAnsStr.equals("exit")) {
                    System.out.println("Action terminated by user");
                    System.out.println("exiting...");
                    return;
                }
                enquiryToBeAnswer = Enquiry.getEnquiryHashMap().get(enquiryToBeAnsStr);
                if (enquiryToBeAnswer == null) {
                    System.out.println("Please insert the correct subject name");
                }
            } while (enquiryToBeAnswer == null);

        System.out.println("This is the enquiry content that you selected to reply to: " + enquiryToBeAnswer.getContent());
        System.out.println();
        System.out.println("Please enter your reply: ");
        String reply = input.nextLine();
        System.out.println("Would you like to send your reply: " + reply);
        System.out.println("Enter \"confirm\" or any other key to cancel");
        if (input.next().equalsIgnoreCase("confirm")) {
            enquiryToBeAnswer.setReply(reply);
            enquiryToBeAnswer.setProcessed(true);
           this.setPoints(this.getPoints() + 1);
            System.out.println("Reply sent successfully");
            String ans;
            do {
                System.out.println("Do you want to reply to another enquiries? Enter Yes or No");
                ans = input.next();
                if(ans.equalsIgnoreCase("Yes")){
                    exitAnswerEnquiry = false;
                }else if(ans.equalsIgnoreCase("No")){
                    exitAnswerEnquiry = true;
                } else{
                    System.out.println("Enter the correct answer: Yes or No");
                }
            }while(!ans.equalsIgnoreCase("Yes") && !ans.equalsIgnoreCase("No"));
        } else {
            System.out.println("Action terminated by user");
            System.out.println("exiting...");
            return;
        }
    }while(!exitAnswerEnquiry);
    }

 /*************************************FOR SUGGESTIONS****************************************/

    public static void manageSuggestions(Committee committee) {
        boolean exit = false;
        do {
            System.out.println("Suggestions Management Menu:");
            System.out.println("1. View Suggestions");
            System.out.println("2. Make Suggestions");
            System.out.println("3. Delete Suggestions");
            System.out.println("4. Back to Main Menu");
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);

                switch (choice) {
                    case 1:
                        viewSuggestions(committee);
                        break;
                    case 2:
                        committee.submitSuggestion(committee);
                        break;
                    case 3:
                        committee.deleteSuggestion(committee);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
        }while(!exit);
    }

    public static void viewSuggestions(Committee committee) {
        if(committee.getSuggestions().isEmpty()){
            System.out.println("No suggestion has been submitted");
        }else {
            for (Suggestion suggestion : committee.getSuggestions()) {
                Suggestion.printSuggestionInfo(suggestion);
            }
        }
    }

    public void submitSuggestion(Committee committee) {
        Scanner input = new Scanner(System.in);
        System.out.println("You are about to submit a suggestion to the camp: " + committee.getRegisteredCamp());
        String subject;
        boolean uniqueSubject; // a do-while loop to make sure that the subject name is unique
        do {System.out.println("Insert an unique subject of the suggestion or \"exit\"  to exit");
            subject = input.nextLine();
            if(subject.equals("exit")){
                System.out.println("Action terminated by the user, exiting...");
                return;
            }
            if(Suggestion.getSuggestionHashMap().containsKey(subject)){
                System.out.println("This name has already been taken, please have an unique subject name");
                uniqueSubject = false;
            }else{
                uniqueSubject = true;
            }
        }while(!uniqueSubject);

        System.out.println("Enter the content of the suggestion or \"exit\" in lower case to exit");
        String content = input.nextLine();
        if(content.equals("exit")){
            System.out.println("Action terminated by the user..., exiting");
            return;
        }
        LocalDate todayDate = LocalDate.now();
        System.out.println("Confirm to submit? Enter \"confirm\" to continue or any other key to cancel ");
        if(input.nextLine().equalsIgnoreCase("confirm")){
            Suggestion newSuggestion = new Suggestion(subject, this, content, todayDate);
            committee.setSuggestions(newSuggestion);
            Suggestion.getSuggestionHashMap().put(subject, newSuggestion);
            Suggestion.getSuggestionArrayList().add(newSuggestion);
            System.out.println("Your suggestion has been successfully submitted");
        }else{
            System.out.println("Action terminated by the user, exiting...");
        }
    }

    public void editSuggestion(Committee committee){
        Scanner input = new Scanner(System.in);
        if(committee.getSuggestions().isEmpty()){
            System.out.println("No suggestion has been submitted");
            System.out.println("exiting...");
            return;
        }
        viewSuggestions(committee);
        Suggestion suggestionToBeEdit;
        boolean exitSuggestionEdit = false;
            do {
                System.out.println("Please select the suggestion that you want to edit by SUBJECT or enter exit in lower case to exit");
                String suggestionToBeEditStr = input.nextLine();
                if (suggestionToBeEditStr.equals("exit")) {
                    System.out.println("Action terminated by user");
                    System.out.println("exiting...");
                    return;
                }
                suggestionToBeEdit = Suggestion.getSuggestionHashMap().get(suggestionToBeEditStr);
                if (suggestionToBeEdit == null) {
                    System.out.println("Please insert the correct subject name");
                }
            } while (suggestionToBeEdit == null);

            if (suggestionToBeEdit.getProcessed()) {
                System.out.println("Cannot be edited, the suggestion has been processed");
                System.out.println("exiting...");
                return;
            }
            boolean exitEditing = false;
            outerLoop:do {
                System.out.println("What do you want to edit?");
                System.out.println("1. Subject");
                System.out.println("2. Content");
                System.out.println("3. Exit");
                int editAttr = input.nextInt();
                switch (editAttr) {
                    case (1):
                        System.out.println("The original subject is " + suggestionToBeEdit.getSuggestion_Subject());
                        String newSubject;
                        boolean uniqueSubject = true; // a do-while loop to make sure that the subject name is unique
                        do {
                            System.out.println("Insert an unique subject of the suggestion or \"exit\"  to exit");
                            newSubject = input.nextLine();
                            if (newSubject.equals("exit")) {
                                System.out.println("Action terminated by the user");
                                System.out.println("exiting...");
                                break outerLoop;
                            }
                            if (Suggestion.getSuggestionHashMap().containsKey(newSubject)) {
                                System.out.println("This name has already been taken, please have an unique subject name");
                                uniqueSubject = false;
                            } else {
                                uniqueSubject = true;
                            }
                        } while (!uniqueSubject);

                        System.out.println("Your new subject is: " + newSubject);
                        System.out.println("Enter confirm or any other key to cancel");
                        if (input.nextLine().equalsIgnoreCase("confirm")) {
                            Suggestion.getSuggestionHashMap().remove(suggestionToBeEdit.getSuggestion_Subject());
                            suggestionToBeEdit.setSuggestion_Subject(newSubject);
                            Suggestion.getSuggestionHashMap().put(newSubject, suggestionToBeEdit);
                            System.out.println("Suggestion subject edited successfully");
                        } else {
                            System.out.println("Action terminated by user");
                            System.out.println("exiting...");
                            break outerLoop;
                        }
                        break;
                    case (2):
                        String newContent;
                        System.out.println("The original suggestion is: " + suggestionToBeEdit.getContent());
                        System.out.println("Please insert the new content for the suggestion within the same line");
                        newContent = input.nextLine();
                        System.out.println("Enter confirm or any other key to cancel");
                        if (input.nextLine().equalsIgnoreCase("confirm")) {
                            suggestionToBeEdit.setContent(newContent);
                            System.out.println("Suggestion content edited successfully");
                        } else {
                            System.out.println("Action terminated by user");
                            System.out.println("exiting...");
                            break outerLoop;
                        }
                        break;
                    case (3):
                        System.out.println("Action terminated by user");
                        exitEditing = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } while (!exitEditing);
    }


    public void deleteSuggestion(Committee committee) {
        Scanner input = new Scanner(System.in);
        if (committee.getSuggestions().isEmpty()) {
            System.out.println("No suggestion has been submitted");
            return;
        }
        for (Suggestion suggestion : committee.getSuggestions()) {
                Suggestion.printSuggestionInfo(suggestion);
            }
            Suggestion suggestionToBeDel;

            do {
                System.out.println("Please select the suggestion that you want to delete by SUBJECT or enter exit in lower case to exit");
                String suggestionToBeDelStr = input.nextLine();
                if (suggestionToBeDelStr.equals("exit")) {
                    System.out.println("Action terminated by user, exiting...");
                    return;
                }
                suggestionToBeDel = Suggestion.getSuggestionHashMap().get(suggestionToBeDelStr);
                if (suggestionToBeDel == null) {
                    System.out.println("Please insert the correct subject name");
                }
            } while (suggestionToBeDel == null);

            if (suggestionToBeDel.getProcessed()) {
                System.out.println("Cannot be deleted, the suggestion has been processed. Exiting...");
                return;
            }

            System.out.println("Do you confirm to delete the suggestion: " + suggestionToBeDel.getSuggestion_Subject());
            System.out.println("Enter \"confirm\" or any other key to cancel");
            if (input.nextLine().equalsIgnoreCase("confirm")) {
                committee.getSuggestions().remove(suggestionToBeDel); //delete in committee attribute
                committee.getRegisteredCamp().getSuggestion().remove(suggestionToBeDel); // delete in camp suggestion
                Suggestion.getSuggestionHashMap().remove(suggestionToBeDel.getSuggestion_Subject()); //delete in suggestion hashmap
                Suggestion.getSuggestionArrayList().remove(suggestionToBeDel); // delete in suggestion arraylist
                System.out.println("Suggestion deleted successfully, exiting...");
                }
            else {
                    System.out.println("Action terminated by user, exiting");
                }
    }


    public void generateReport(Committee committee) {
        Scanner scanner = new Scanner(System.in);
        GenerateReport committeeGenerator = new CommitteeGenerateReport();
        boolean exit = false;
        do {
            System.out.println("Generate Report Menu:");
            System.out.println("1. View Camp Committee Reports");
            System.out.println("2. View Camp Attendee Reports");
            System.out.println("3. Back to Main Menu");

            int choice;
            do {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                    choice = -1; // Set a default value or use a flag to handle the loop
                }
            } while (choice != 1 && choice != 2 && choice != 3);
                switch (choice) {
                    case 1:
                        String format = null;
                        do {
                            System.out.println("What format do you want to use, CSV or TXT? Please enter CSV or TXT");
                            format = scanner.next();
                            if (format.equalsIgnoreCase("CSV")) {
                                committeeGenerator.generateCommitteeList(committee.getRegisteredCamp(), Format.CSV);
                            } else if (format.equalsIgnoreCase("TXT")) {
                                committeeGenerator.generateCommitteeList(committee.getRegisteredCamp(), Format.TXT);
                            } else {
                                System.out.println("Only CSV and TXT are accepted");
                            }
                        }while(!format.equalsIgnoreCase("CSV") && !format.equalsIgnoreCase("TXT"));
                        break;
                    case 2:
                        String format2 = null;
                        do {
                            System.out.println("What format do you want to use, CSV or TXT? Please enter CSV or TXT");
                            format2 = scanner.next();
                            if (format2.equalsIgnoreCase("CSV")) {
                                committeeGenerator.generateAttendeeList(committee.getRegisteredCamp(), Format.CSV);
                            } else if (format2.equalsIgnoreCase("TXT")) {
                                committeeGenerator.generateAttendeeList(committee.getRegisteredCamp(), Format.CSV);
                            } else {
                                System.out.println("Only CSV and TXT are accepted");
                            }
                        }while(!format2.equalsIgnoreCase("CSV") && !format2.equalsIgnoreCase("TXT"));
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
        }while(!exit);
    }


}
