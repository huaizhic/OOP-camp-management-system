package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.DisplayOptions.DisplayApp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Faculty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * When the Camp Attendee user chooses an option from the menu, they will be directed to this class to complete the required functions.
 */
public class Attendee extends Student_User {

	 private static Map<String, Attendee> attendeesMap = new HashMap<>();
     private ArrayList<Camp> registeredCamps;

     private ArrayList<Enquiry> enquirySubmitted;

	    public Attendee() {
	        super();
	        this.registeredCamps = new ArrayList<>();
	        attendeesMap.put(this.getStudentID(), this);
	    }

        public Map<String, Attendee> getAttendeesMap() {
	        return attendeesMap;
	    }

        // Method to add an attendee
        public void addAttendee(Attendee newAttendee) {
            attendeesMap.put(newAttendee.getStudentID(), newAttendee);
            // You may want to save the updated attendeesMap to a file or perform other actions.
        }

        // Method to get existing attendees
        public static Map<String, Attendee> existingAttendees() {
            return attendeesMap;
        }

    public ArrayList<Camp> getRegisteredCamps() {
        return registeredCamps;
    }

    public void setRegisteredCamps(Camp camp){
            registeredCamps.add(camp);
    }

    @Override
    public ArrayList<Enquiry> getEnquirySubmitted() {
        return enquirySubmitted;
    }

    public void setEnquirySubmitted(Enquiry enquiry) {
        enquirySubmitted.add(enquiry);
    }

    // New method to display registered camps for a specific student
    public static void displayRegisteredCamps(Attendee attendee) {
            if (attendee.getRegisteredCamps().isEmpty()) {
                System.out.println("You have not register any camp.");
            } else {
                for (Camp camp : attendee.getRegisteredCamps()) {
                    Camp.printAllCampInfo(camp);
                }
            }
	    }
	    

    public static void manageCamp(Attendee attendee) {
            boolean exit = false;
            do {
                System.out.println("Camp Enquiries Menu:");
                System.out.println("1. View Camps");
                System.out.println("2. Register for a Camp. Note: if you do not know the camp name, please select view camps to check the camp name first");
                System.out.println("3. Withdraw from a Camp");
                System.out.println("4. Back to Main Menu");

                System.out.print("Enter your choice: ");
                Scanner scanner = null;
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Delegate to specific functionality in the Attendee class
                        viewCamp(attendee);
                        break;
                    case 2:
                        // Delegate to specific functionality in the Attendee class
                        registerForCamp(attendee);
                        break;
                    case 3:
                        // Delegate to specific functionality in the Attendee class
                        withdrawFromCamp(attendee);
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
    
   

    public static void viewCamp(Attendee attendee) {
        ArrayList<Camp> campArrayList = DisplayApp.viewAllCamp();
        if(campArrayList == null){
            return;
        }
        ArrayList<Camp> eligibleCamp = new ArrayList<>();
        for(Camp camp : campArrayList){
            if(camp.getVisibility() && (camp.getUserGroup().contains(Faculty.ALL) || camp.getUserGroup().contains(attendee.getFaculty()))){
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



    public static void registerForCamp(Attendee attendee) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you know the camp name of the camp that you want to register for?");
        System.out.println("Enter \"Yes\" or any other key for no");
        String campNameKnown = input.next();
        if(!campNameKnown.equalsIgnoreCase("Yes")){
            System.out.println("Please view all camps to check the camp name first");
            return;
        }
        Camp campToRegister;
        do {
            System.out.println("Please enter the camp name EXACTLY as it is displayed, or enter \" exit \" to go back");
            String campName = input.nextLine();
            if(campName.equalsIgnoreCase("exit")){
                System.out.println("Action terminated by the user, exiting...");
                return;
            }
            campToRegister = Camp.getCampByName(campName);
            if(campToRegister == null){
                System.out.println("Camp not found, please check the camp name");
            }else{
                System.out.println("Enter \"confirm\" to register or any key to go back");
                String confirm = input.next();
                if(!confirm.equalsIgnoreCase("confirm")){
                    System.out.println("Action terminated by the user, exiting...");
                    return;
                }
                attendee.setRegisteredCamps(campToRegister);
                campToRegister.setAttendeesRegistered(attendee);
                int originalSlot = campToRegister.getRemainingSlots();
                campToRegister.setRemainingSlots(originalSlot - 1);
                System.out.println("Camp registeration successful");
            }
        }while(campToRegister == null);
    }

    public static void withdrawFromCamp(Attendee attendee) {
        Scanner input = new Scanner(System.in);
        if(attendee.getRegisteredCamps().isEmpty()){
            System.out.println("You have not registered any camp, cannot withdraw. Exiting...");
            return;
        }
        for(Camp camp: attendee.getRegisteredCamps()){
            Camp.printAllCampInfo(camp);
        }

        System.out.println("Which camp do you want to withdraw from?");
        Camp campToWithdraw;
        do {
            System.out.println("Please enter the camp name EXACTLY as it is displayed, or enter \" exit \" to go back");
            String campName = input.nextLine();
            if(campName.equalsIgnoreCase("exit")){
                System.out.println("Action terminated by the user, exiting...");
                return;
            }
            campToWithdraw = Camp.getCampByName(campName);
            if(campToWithdraw == null){
                System.out.println("Camp not found, please check the camp name");
            }else{
                System.out.println("Enter \"confirm\" to withdraw or any key to go back");
                String confirm = input.next();
                if(!confirm.equalsIgnoreCase("confirm")){
                    System.out.println("Action terminated by the user, exiting...");
                    return;
                }
                attendee.getRegisteredCamps().remove(campToWithdraw);
                campToWithdraw.getAttendeesRegistered().remove(attendee);
                int originalSlot = campToWithdraw.getRemainingSlots();
                campToWithdraw.setRemainingSlots(originalSlot + 1);
                System.out.println("Camp withdrawn successful");
            }
        }while(campToWithdraw == null);
    }

    /**************** FOR ENQUIRIES ***********************************/
    
    public static void manageEnquiries(Attendee attendee) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Camp Enquiries Menu:");
            System.out.println("1. View Enquiries");
            System.out.println("2. Make Enquiries");
            System.out.println("3. Edit Enquiries");
            System.out.println("4. Delete Enquiries");
            System.out.println("5. Back to Main Menu");

            int choice = getUserChoiceWithExceptionHandling(scanner);

            switch (choice) {
                case 1:
                    // Delegate to specific functionality in the Attendee class
                    Attendee.viewEnquiry(attendee);
                    break;
                case 2:
                    // Delegate to specific functionality in the Attendee class
                    Attendee.makeEnquiry(attendee);
                    break;
                case 3:
                    Attendee.editEnquiry(attendee);
                    break;
                case 4:
                    // Delegate to specific functionality in the Attendee class
                    Attendee.deleteEnquiry(attendee);
                    break;
                case 5:
                    // Back to the main menu
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (!exit);
    }

    private static int getUserChoiceWithExceptionHandling(Scanner scanner) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

    
    public static void viewEnquiry(Attendee attendee) {
        if(attendee.getEnquirySubmitted().isEmpty()){
            System.out.println("No enquiry has been submitted");
            return;
        }

        for(Enquiry enquiry : attendee.getEnquirySubmitted()){
            Enquiry.printAllEnquiryInfo(enquiry);
        }
    }

    public static void makeEnquiry(Attendee attendee){
        System.out.println("Please firstly select the camp that you want to submit the enquiry to");
        System.out.println("Please note down the camp name of the interested camp");
        ArrayList<Camp> campArrayList = DisplayApp.viewAllCamp();
        if(campArrayList == null){
            return;
        }
        ArrayList<Camp> eligibleCamp = new ArrayList<>();
        for(Camp camp : campArrayList){
            if(camp.getVisibility() && (camp.getUserGroup().contains(Faculty.ALL) || camp.getUserGroup().contains(attendee.getFaculty()))){
                eligibleCamp.add(camp);
            }
        }
        if(eligibleCamp.isEmpty()){
            System.out.println("No camp available to view, exiting...");
            return;
        }

        for(Camp camp : eligibleCamp){
            Camp.printAllCampInfo(camp);
            System.out.println();
        }

        Scanner input = new Scanner(System.in);
        Camp campToEnquire;
        do {
            System.out.println("Please enter the camp name, that you want to enquire,EXACTLY as it is displayed, or enter \" exit \" to go back");
            String campName = input.nextLine();
            if(campName.equalsIgnoreCase("exit")){
                System.out.println("Action terminated by the user, exiting...");
                return;
            }
            campToEnquire = Camp.getCampByName(campName);
            if(campToEnquire == null){
                System.out.println("Camp not found, please check the camp name");
            }else{
                System.out.println("Enter \"confirm\" to choose the selected camp or any key to go back: ");
                String confirm = input.next();
                if(!confirm.equalsIgnoreCase("confirm")){
                    System.out.println("Action terminated by the user, exiting...");
                    return;
                }
                System.out.println("you are about to submit an enquiry for the camp: " + campName);
            }
        }while(campToEnquire == null);

        String subject;
        boolean uniqueSubject; // a do-while loop to make sure that the subject name is unique
        do {System.out.println("Insert an unique enquiry subject and try to keep it short, or \"exit\"  to exit");
            subject = input.nextLine();
            if(subject.equals("exit")){
                System.out.println("Action terminated by the user, exiting...");
                return;
            }
            if(Enquiry.getEnquiryHashMap().containsKey(subject)){
                System.out.println("This name has already been taken, please have an unique subject name");
                uniqueSubject = false;
            }else{
                uniqueSubject = true;
            }
        }while(!uniqueSubject);

        System.out.print("Enter your enquiry content or \"exit\" in to exit");
        String content = input.nextLine();
        if(content.equalsIgnoreCase("exit")){
            System.out.println("Action terminated by the user..., exiting");
            return;
        }
        LocalDate todayDate = LocalDate.now();
        Enquiry newEnquiry = new Enquiry(subject, content, todayDate, attendee);

        Enquiry.getEnquiryHashMap().put(subject, newEnquiry); //add enquiry to hashmap
        Enquiry.setEnquiryArrayList(newEnquiry); //add enquiry to the total enquiry
        attendee.setEnquirySubmitted(newEnquiry); //add enquiry to the attendee
        campToEnquire.setEnquiry(newEnquiry); // add enquiry to the camp
        System.out.println("The enquiry has been sent successfully");
    }

    public static void editEnquiry(Attendee attendee){
        Scanner input = new Scanner(System.in);
        // if no enquiry has been submitted, exit.
        if(attendee.getEnquirySubmitted().isEmpty()){
            System.out.println("No enquiry has been submitted");
            System.out.println("exiting...");
            return;
        }
        //print all submitted enquiry.
        System.out.println("These are the enquiries that are yet to be processed");
        for(Enquiry enquiry : attendee.getEnquirySubmitted()){
            if(!enquiry.isProcessed()){
                Enquiry.printAllEnquiryInfo(enquiry);
            }
        }


        Enquiry enquiryToBeEdit;
        boolean exitEnquiryEdit = false;
        do {
            System.out.println("Please select the enquiry that you want to edit by SUBJECT or enter exit to go back");
            String enquiryToBeEditStr = input.nextLine();
            if (enquiryToBeEditStr.equalsIgnoreCase("exit")) {
                System.out.println("Action terminated by user");
                System.out.println("exiting...");
                return;
            }
            enquiryToBeEdit = Enquiry.getEnquiryBySubject(enquiryToBeEditStr);
            if (enquiryToBeEdit == null) {
                System.out.println("Please insert the correct subject name");
            }
        } while (enquiryToBeEdit == null);

        if (enquiryToBeEdit.isProcessed()) {
            System.out.println("Cannot be edited, the enquiry has been processed");
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
                    System.out.println("The original subject is " + enquiryToBeEdit.getEnquiry_Subject());
                    String newSubject;
                    boolean uniqueSubject = true; // a do-while loop to make sure that the subject name is unique
                    do {
                        System.out.println("Insert an unique subject of the suggestion or \"exit\"  to go back");
                        newSubject = input.nextLine();
                        if (newSubject.equals("exit")) {
                            System.out.println("Action terminated by the user");
                            System.out.println("exiting...");
                            break outerLoop;
                        }
                        if (Enquiry.getEnquiryHashMap().containsKey(newSubject)) {
                            System.out.println("This name has already been taken, please have an unique subject name");
                            uniqueSubject = false;
                        } else {
                            uniqueSubject = true;
                        }
                    } while (!uniqueSubject);

                    System.out.println("Your new subject is: " + newSubject);
                    System.out.println("Enter confirm or any other key to cancel");
                    if (input.nextLine().equalsIgnoreCase("confirm")) {
                        Enquiry.getEnquiryHashMap().remove(enquiryToBeEdit.getEnquiry_Subject());
                        enquiryToBeEdit.setEnquiry_Subject(newSubject);
                        Enquiry.getEnquiryHashMap().put(newSubject, enquiryToBeEdit);
                        System.out.println("Suggestion subject edited successfully");
                    } else {
                        System.out.println("Action terminated by user");
                        System.out.println("exiting...");
                        break outerLoop;
                    }
                    break;
                case (2):
                    String newContent;
                    System.out.println("The original enquiry is: " + enquiryToBeEdit.getContent());
                    System.out.println("Please insert the new content for the enquiry within the same line");
                    newContent = input.nextLine();
                    System.out.println("Enter confirm or any other key to cancel");
                    if (input.nextLine().equalsIgnoreCase("confirm")) {
                        enquiryToBeEdit.setContent(newContent);
                        System.out.println("Enquiry content edited successfully");
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

    public static void deleteEnquiry(Attendee attendee){
        Scanner input = new Scanner(System.in);
        // if no enquiry has been submitted, exit.
        if(attendee.getEnquirySubmitted().isEmpty()){
            System.out.println("No enquiry has been submitted");
            System.out.println("exiting...");
            return;
        }
        //print all submitted enquiry.
        System.out.println("These are the enquiries that are yet to be processed");
        for(Enquiry enquiry : attendee.getEnquirySubmitted()){
            if(!enquiry.isProcessed()){
                Enquiry.printAllEnquiryInfo(enquiry);
            }
        }

        Enquiry enquiryToDelete;

        do {
            System.out.println("Please select the enquiry that you want to delete by SUBJECT or enter \"exit\" to go back");
            String enquiryToBeDelStr = input.nextLine();
            if (enquiryToBeDelStr.equals("exit")) {
                System.out.println("Action terminated by user, exiting...");
                return;
            }
            enquiryToDelete = Enquiry.getEnquiryHashMap().get(enquiryToBeDelStr);
            if (enquiryToDelete == null) {
                System.out.println("Please insert the correct subject name");
            }
        } while (enquiryToDelete == null);

        if (enquiryToDelete.isProcessed()) {
            System.out.println("Cannot be deleted, the suggestion has been processed. Exiting...");
            return;
        }

        System.out.println("Do you confirm to delete the suggestion: " + enquiryToDelete.getEnquiry_Subject());
        System.out.println("Enter \"confirm\" or any other key to cancel");
        if (input.nextLine().equalsIgnoreCase("confirm")) {
            attendee.getEnquirySubmitted().remove(enquiryToDelete); // delete in attribute
            Enquiry.getEnquiryArrayList().remove(enquiryToDelete); // delete in arraylist
            Enquiry.getEnquiryHashMap().remove(enquiryToDelete.getEnquiry_Subject()); // delete in hashmap

            for(Camp camp:attendee.getRegisteredCamps()){
                if(camp.getEnquiry().contains(enquiryToDelete)){
                    camp.getEnquiry().remove(enquiryToDelete);
                }
            } // delete in the camp that has it
            System.out.println("Enquiry deleted successfully, exiting...");
        }
        else {
            System.out.println("Action terminated by user, exiting");
        }
    }

    }
