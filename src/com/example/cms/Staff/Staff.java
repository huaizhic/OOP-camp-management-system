package com.example.cms.Staff;

import com.example.cms.CSVConverter.CSVDataManager;
import com.example.cms.Camp.Camp;
import com.example.cms.Camp.campData;
import com.example.cms.DisplayOptions.*;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Faculty;
import com.example.cms.Format;
import com.example.cms.Status;
import com.example.cms.Student.Committee;
import com.example.cms.Suggestions.Suggestion;
import com.example.cms.generate_report.GenerateReport;
import com.example.cms.generate_report.StaffGenerateReport;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * When the Staff user chooses an option from the menu, they will be directed to this class to complete the required functions.
 */
public class Staff extends Staff_User{

	public Staff(String staffID, String name, Faculty userGroup, String securityQuestion, String securityAns){
		super(staffID, name, userGroup, securityQuestion, securityAns);
		existingStaff.put(staffID, this);
	}

	private Scanner input;

	protected void createCamp() {
	    Scanner scanner = new Scanner(System.in);
	    input = new Scanner(System.in);
	    System.out.println("===== Creating a New Camp =====");

	    // Get a unique camp name
	    String campName = null;
	    boolean uniqueCampName;
	    do {
	        System.out.println("Please insert the new camp name that is unique:");
	        String campName1 = input.nextLine().trim();
	        campName = campName1;
	        uniqueCampName = campData.getCampList().stream().noneMatch(camp -> camp.getCampName().equalsIgnoreCase(campName1));
	        if (!uniqueCampName) {
	            System.out.println("This name has already been taken, please choose a unique camp name.");
	        }
	    } while (!uniqueCampName);
	    
	    System.out.println("Camp name: " + campName);

	    // Get and validate start date
	    LocalDate startDate = getDateFromUser("Please insert the starting date of the camp in the format yyyy-mm-dd:");

	    // Get and validate end date
	    LocalDate endDate = getDateFromUser("Please insert the ending date of the camp in the format yyyy-mm-dd:");

	    // Get and validate registration close date
	    LocalDate regCloseDate = getDateFromUser("Please insert the registration close date of the camp in the format yyyy-mm-dd:");

	    // Determine user groups
	    ArrayList<Faculty> userGroups = getUserGroups();

	    // Get location
	    System.out.println("Nearly there... Insert the location of the camp:");
	    String location = input.nextLine().trim();
	    System.out.println("Location: " + location);

	    // Get total slots
	    int slots = getNumericInput("Enter the total slot of the attendee:");
	    System.out.println("The total slot is " + slots);

	    // Get visibility
	    boolean visibility = getVisibility();

	    ArrayList<LocalDate> campDate = new ArrayList<>();
	    campDate.add(startDate);
	    campDate.add(endDate);

	    // Create new camp
	    Camp newCamp = new Camp(campName, campDate, regCloseDate, userGroups, location, slots, slots, 10, 10, this.getName(), visibility);

	    // Update camp data
	    campData.setCampList(newCamp);
	    campData.addCampToMap(campName, newCamp);
	    this.setCampsCreated(newCamp);

	    // Update CSV file for the new camp
	    CSVDataManager.updateCampCSVFile(newCamp);
	}

	private LocalDate getDateFromUser(String message) {
	    LocalDate date = null;
	    do {
	        System.out.println(message);
	        String dateString = input.nextLine().trim();
	        try {
	            date = LocalDate.parse(dateString);
	            System.out.println("Date: " + date);
	        } catch (DateTimeParseException e) {
	            System.out.println("Please insert the date in the correct format: yyyy-mm-dd");
	        }
	    } while (date == null);
	    return date;
	}

	private ArrayList<Faculty> getUserGroups() {
	    ArrayList<Faculty> userGroups = new ArrayList<>();
	    boolean conti = true;
	    String ans;
	    do {
	        System.out.println("Please insert the school/faculty that have access to the camp separated by space:");
	        String userGroupString = input.nextLine();
	        String[] usergroups = userGroupString.split("\\s+");
	        ArrayList<String> userGroupArray = new ArrayList<>(Arrays.asList(usergroups));
	        for (String usergroup : userGroupArray) {
	            for (Faculty enumUserGroup : Faculty.values()) {
	                if (usergroup.equals(enumUserGroup.name())) {
	                    userGroups.add(enumUserGroup);
	                    break;
	                }
	            }
	        }
	        if (userGroups.size() == userGroupArray.size()) {
	            System.out.println("The following school/faculty can view and register:");
	            userGroups.forEach(userGroup -> System.out.print(userGroup + " "));
	        } else {
	            System.out.println("Invalid input received, only the following are valid. Continue?");
	            userGroups.forEach(userGroup -> System.out.print(userGroup + " "));
	            do {
	                System.out.println("Insert Yes or Re-enter:");
	                ans = input.next();
	                conti = ans.equalsIgnoreCase("Yes");
	            } while (!ans.equalsIgnoreCase("Yes") && !ans.equalsIgnoreCase("Re-enter"));
	        }
	    } while (!conti);
	    return userGroups;
	}

	private int getNumericInput(String message) {
	    int inputNumber = 0;
	    boolean validInput = false;

	    while (!validInput) {
	        try {
	            System.out.println(message);
	            String inputLine = input.nextLine().trim();

	            if (!inputLine.isEmpty()) {
	                inputNumber = Integer.parseInt(inputLine);
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a valid number.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    return inputNumber;
	}

	private boolean getVisibility() {
	    boolean visibility;
	    do {
	        System.out.println("Enter the visibility of the camp: On or Off");
	        String visibilityStr = input.next().trim();
	        visibility = visibilityStr.equalsIgnoreCase("On") || visibilityStr.equalsIgnoreCase("Off");
	    } while (!visibility);
	    return visibility;
	}

	protected void viewCampCreated(Staff staff) {
	    if (staff.getCampsCreated().isEmpty()) {
	        System.out.println("No camp has been created, please create one before editing");
	    } else {
	        System.out.println("Please view the camp that you have created with the following ways:");
	        System.out.println("1. Default: alphabetical order of camp name");
	        System.out.println("2. Search for keywords");
	        System.out.println("3. Sort by camp features");

	        int choice = getNumericInput2("Enter your choice:");
	        switch (choice) {
	            case 1:
	                DisplayBySort aOb = new SortByName_Default();
	                ArrayList<Camp> sorted = aOb.Sorting(staff.getCampsCreated());
	                sorted.forEach(camp -> Camp.printAllCampInfo(camp));
	                break;
	            case 2:
	                ArrayList<Camp> afterSearchCamp;
	                do {
	                    afterSearchCamp = SearchApp.startSearch(staff.getCampsCreated());
	                    if (afterSearchCamp == null) {
	                        System.out.println("Please choose a correct option");
	                    } else {
	                        afterSearchCamp.forEach(camp -> Camp.printAllCampInfo(camp));
	                    }
	                } while (afterSearchCamp == null);
	                break;
	            case 3:
	                ArrayList<Camp> afterSortCamp;
	                do {
	                    afterSortCamp = SortApp.startSorting(staff.getCampsCreated());
	                    if (afterSortCamp == null) {
	                        System.out.println("Please choose a correct option");
	                    } else {
	                        afterSortCamp.forEach(camp -> Camp.printAllCampInfo(camp));
	                    }
	                } while (afterSortCamp == null);
	                break;
	        }
	    }
	}

	private Camp selectCamp(ArrayList<Camp> campArrayList) {
	    String campToBeSelectedStr;
	    Camp campToBeSelected;

	    do {
	        campToBeSelectedStr = getStringInput("Please insert the camp name or enter 'exit' to cancel:");
	        if (campToBeSelectedStr.equalsIgnoreCase("exit")) {
	            return null;
	        } else {
	            campToBeSelected = campData.getCampHashMap().get(campToBeSelectedStr);
	            if (campToBeSelected == null) {
	                System.out.println("The camp does not exist, please verify the CAMP NAME");
	            }
	        }
	    } while (campToBeSelected == null);

	    return campToBeSelected;
	}

	private int getNumericInput2(String prompt) {
	    int input = 0;
	    boolean validInput = false;
	    Scanner scanner = new Scanner(System.in);

	    while (!validInput) {
	        try {
	            System.out.print(prompt);
	            input = Integer.parseInt(scanner.nextLine().trim());
	            validInput = true;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    return input;
	}

	private String getStringInput(String prompt) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print(prompt);
	    return scanner.nextLine().trim();
	}


	public void editCamp(Staff staff) {
		viewCampCreated(staff);
			boolean completedEditing = false;
			do {
				Camp campToBeEdited = selectCamp(staff.getCampsCreated());
				if(campToBeEdited == null){
					System.out.println("Action terminated, exiting...") ;
					return;
				}
				System.out.println("====What camp information would you like to edit====");
				System.out.println("1. Camp name");
				System.out.println("2. Camp location");
				System.out.println("3. Camp start date");
				System.out.println("4. Camp end date");
				System.out.println("5. Camp registration close date");
				System.out.println("6. Camp total slots");
				System.out.println("7. Camp eligible user group");
				System.out.println("8. Exiting");
				int thingsToEdit = input.nextInt();
				switch(thingsToEdit){
					case(1):
						System.out.println("Enter the new camp name");
						String newCampName = input.nextLine();
						String oldCampName = campToBeEdited.getCampName();
						campData.getCampHashMap().remove(oldCampName);
						campToBeEdited.setCampName(newCampName);
						campData.addCampToMap(newCampName, campToBeEdited);
						System.out.println("The new camp name is now: " + campToBeEdited.getCampName());
						System.out.println("Any other camp to edit? Yes or No");
						break;
					case(2):
						System.out.println("Enter the new camp location");
						String newLocation = input.nextLine();
						campToBeEdited.setLocation(newLocation);
						System.out.println("The new camp name is now: " + campToBeEdited.getLocation());
						System.out.println("Any other camp to edit? Yes or No");
						break;
					case(3):
						System.out.println("Please insert the new camp start date in the format yyyy-mm-dd");
						LocalDate newCampStartDate = null;
						do{String regCloseString = input.nextLine();
							try{
								newCampStartDate = LocalDate.parse(regCloseString);
								campToBeEdited.getCampDates().set(0, newCampStartDate);
								System.out.println("New camp start date: " + campToBeEdited.getCampDates().get(0));
								System.out.println("Any other camp to edit? Yes or No");
							}catch(DateTimeParseException e){
								System.out.println("Please insert the new camp start date in the correct format: yyyy-mm-dd");
							}}while(newCampStartDate == null);
						break;
					case(4):
						System.out.println("Please insert the new camp end date in the format yyyy-mm-dd");
						LocalDate newCampEndDate = null;
						do{String newCampEndDateString = input.nextLine();
							try{
								newCampEndDate = LocalDate.parse(newCampEndDateString);
								campToBeEdited.getCampDates().set(1, newCampEndDate);
								System.out.println("New camp end date: " + campToBeEdited.getCampDates().get(1));
								System.out.println("Any other camp to edit? Yes or No");
							}catch(DateTimeParseException e){
								System.out.println("Please insert the new camp end date in the correct format: yyyy-mm-dd");
							}}while(newCampEndDate == null);
						break;
					case(5):
						System.out.println("Please insert the new camp registration close date in the format yyyy-mm-dd");
						LocalDate newRegCloseDate = null;
						do{String newRegDateString = input.nextLine();
							try{
								newRegCloseDate = LocalDate.parse(newRegDateString);
								campToBeEdited.setRegCloseDate(newRegCloseDate);
								System.out.println("New camp reg close date: " + campToBeEdited.getRegCloseDate());
								System.out.println("Any other camp to edit? Yes or No");
							}catch(DateTimeParseException e){
								System.out.println("Please insert the new camp end date in the correct format: yyyy-mm-dd");
							}}while(newRegCloseDate == null);
						break;
					case(6):
						boolean eligibleSlot = false;
						do{System.out.println("Enter the new camp attendee slots");
						int newAttendeeSlot = input.nextInt();
						if(campToBeEdited.getAttendeesRegistered().size() > newAttendeeSlot){
							System.out.println("The number of registered attendee has already passed the new slot number");
						}else{
							eligibleSlot = true;
							campToBeEdited.setRemainingSlots(newAttendeeSlot - campToBeEdited.getAttendeesRegistered().size());
							campToBeEdited.setTotalSlots(newAttendeeSlot);
							System.out.println("The new camp attendee slots is now: " + campToBeEdited.getTotalSlots());
							System.out.println("Any other camp to edit? Yes or No");
						}
						} while(!eligibleSlot);
						break;
					case(7):
						boolean conti = true;
						String ans;
						ArrayList<Faculty> userGroups = new ArrayList<>();
						do {
							System.out.println("    Please insert the school/faculty that have access to the camp separated by space");
							String userGroupString = input.nextLine();
							String[] usergroups = userGroupString.split("\\s+");
							ArrayList<String> userGroupArray = new ArrayList<>();
							Collections.addAll(userGroupArray, usergroups);
							for (String usergroup : userGroupArray) {
								for (Faculty enumUserGroup : Faculty.values()) {
									if (usergroup.equals(enumUserGroup.name())) {
										userGroups.add(enumUserGroup);
										break;
									}
								}
							}
							if (userGroups.size() == userGroupArray.size()) {
								System.out.println("The following school/faculty can view and register");
								for (Faculty userGroup : userGroups) {
									System.out.print(userGroup + " ");
								}
							} else {
								System.out.println("Invalid input has received, only the following is valid, continue? ");
								for (Faculty userGroup : userGroups) {
									System.out.print(userGroup + " ");
								}
								do {
									System.out.println("Insert Yes or Re-enter");
									ans = input.next();
									conti = ans.equals("Yes");
								} while (!ans.equals("Yes") && !ans.equals("Re-enter"));
							}
						}while(!conti);
						campToBeEdited.setUserGroup(userGroups);
						break;
					case(8):
						System.out.println("Action terminated by the user，exiting...");
						return;
				}
				String ans;
				do {
					System.out.println("Any other camp to edit? Yes or No");
					ans = input.nextLine();
					completedEditing = (ans == "No");
				}while(ans != "Yes" && ans != "No");

				CSVDataManager.updateCampCSVFile(campToBeEdited);
			}while(!completedEditing);
			System.out.println("Edit successful!");
		}


	public void deleteCamp(Staff staff) {
		if(staff.getCampsCreated().isEmpty()){
			System.out.println("No camp has been created, exiting");
			return;
		}
		else {
			viewCampCreated(staff);
			Camp campToBeDeleted = selectCamp(staff.getCampsCreated());
			if (campToBeDeleted == null) {
				return;
			} else {
				String campNameToDeleteStr = campToBeDeleted.getCampName();
				if (campToBeDeleted.getAttendeesRegistered().isEmpty() && campToBeDeleted.getCommitteeRegistered().isEmpty()) {
					System.out.println("Confirm to delete Camp: " + campNameToDeleteStr + ". Insert Confirm or any other key");
					if (input.nextLine().equals("Confirm")) {
						staff.getCampsCreated().remove(campToBeDeleted);
						campData.getCampList().remove(campToBeDeleted);
						campData.getCampHashMap().remove(campNameToDeleteStr);
						for(Camp camp : campData.getCampList()){
							CSVDataManager.updateCampCSVFile(camp);
						}
						System.out.println("Camp deletes successfully");
                    }
					else{
						System.out.println("Action terminated by user, exiting...");
					}
				} else {
					System.out.println("There is already attendee or committee registered, the camp cannot be deleted");
					System.out.println("Action terminated, exiting...");
				}
			}
		}
	}

	protected void viewAllCamps() {
		ArrayList<Camp> campArrayList = DisplayApp.viewAllCamp();
		if(campArrayList == null){
			System.out.println("Action terminated");
		}else{
			for(Camp camp: campArrayList){
				Camp.printAllCampInfo(camp);
			}
		}

	}

	protected void viewEnquiry(Staff staff) {
		viewCampCreated(staff);
		Camp campNameToViewEnquiry = selectCamp(staff.getCampsCreated());
		if(campNameToViewEnquiry == null){
			return;
		}
		if(campNameToViewEnquiry.getEnquiry().isEmpty()){
			System.out.println("No enquiry has been submitted for this camp");
			System.out.println("Existing...");
		}
		else {
			for (Enquiry enquiry : campNameToViewEnquiry.getEnquiry()) {
				Enquiry.printAllEnquiryInfo(enquiry);
			}
		}
	}

	public void replyEnquiry(Staff staff) {
		Scanner input = new Scanner(System.in);
		viewCampCreated(staff);
		Enquiry enquiryToReply = null;
		Camp campNameToReplyEnquiry = selectCamp(staff.getCampsCreated());
		if(campNameToReplyEnquiry == null){
			return;
		}
		if(campNameToReplyEnquiry.getEnquiry().isEmpty()){
			System.out.println("No enquiry has been submitted for this camp, exiting...");
			return;
		}

		for (Enquiry enquiry : campNameToReplyEnquiry.getEnquiry()) {
				Enquiry.printAllEnquiryInfo(enquiry);
		}

		do{
			System.out.println("Which enquiry would you like to reply to? Insert enquiry SUBJECT. Enter \"exit \" to go back");
			String enquirySubjectStr = input.nextLine();
			if(enquirySubjectStr.equalsIgnoreCase("exit")){
				System.out.println("Action terminated by the user, exiting...");
				return;
			}
			enquiryToReply = Enquiry.getEnquiryHashMap().get(enquirySubjectStr);
			if(enquiryToReply == null){
				System.out.println("Please check the subject of the enquiry and try again");
			}
		}while(enquiryToReply == null);

		System.out.println("The enquiry selected is: " + enquiryToReply.getContent());
		System.out.println();
		System.out.println("Insert your reply:");
		String reply = input.nextLine();
		System.out.println("Enter \"confirm\" to submit your reply or any other key to cancel");
		if(input.next().equalsIgnoreCase("confirm")){
			enquiryToReply.setReply(reply);
			CSVDataManager.updateEnquiryCSVFile(enquiryToReply);
		}else{
			System.out.println("Action terminated by the user, exiting...");
		}
	}

	public void viewSuggestion(Staff staff) {
		viewCampCreated(staff);
		Camp toView = selectCamp(staff.getCampsCreated());
		if(toView == null){
			return;
		}
		if(toView.getSuggestion().isEmpty()){
			System.out.println("No suggestion has been submitted for " + toView.getCampName());
		}
		else{
			for(Suggestion suggestion : toView.getSuggestion()){
				Suggestion.printSuggestionInfo(suggestion);
			}
		}
	}


	public void approveSuggestion(Staff staff) {
		viewSuggestion(staff);
		Suggestion suggestionToApprove;
		do{
			System.out.println("Which suggestion would you like to approve/disapprove? Insert suggestion SUBJECT. Enter \"exit \" to go back");
			String suggestionSubjectStr = input.nextLine();
			if(suggestionSubjectStr.equalsIgnoreCase("exit")){
				System.out.println("Action terminated by the user, exiting...");
				return;
			}
			suggestionToApprove =Suggestion.getSuggestionHashMap().get(suggestionSubjectStr);
			if(suggestionToApprove == null){
				System.out.println("Please enter the SUBJECT of the suggestion and try again");
			}
		}while(suggestionToApprove == null);
		System.out.println("The enquiry selected is: " + suggestionToApprove.getContent());
		System.out.println();
		System.out.println("Please select your response from the following");
		System.out.println("1. Approve");
		System.out.println("2. Disapprove");
		System.out.println("3. Pending");
		System.out.println("4. Cancel and exit");
		int decision;
		do {
			try {
				System.out.print("Enter your choice: ");
				decision = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				input.nextLine(); // Consume the invalid input
				decision = -1; // Set a default value or use a flag to handle the loop
			}
		} while (decision != 1 && decision != 2 && decision != 3 && decision != 4);
		Status response = null;
		switch(decision){
			case(1):
				response = Status.Approved;
				break;
			case(2):
				response = Status.Disapproved;
				break;
			case(3):
				response = Status.Pending;
				break;
			case(4):
				System.out.println("Action terminated by the user, exiting");
				return;
		}

		System.out.println("Enter \"confirm\" to submit your decision or any other key to cancel");
		if(input.next().equalsIgnoreCase("confirm")){
			suggestionToApprove.setStatus(response);
			suggestionToApprove.setProcessed(true);
			Committee submitter = Committee.committeeNameMap.get(suggestionToApprove.getSubmitter());
			int points = submitter.getPoints();
			submitter.setPoints(points + 1);
			CSVDataManager.updateSuggestionCSVFile(suggestionToApprove);
			CSVDataManager.updateCommitteeCSVFile(submitter);
			System.out.println("The suggestion has been processed and your response has been successfully recorded");
		}else{
			System.out.println("Action terminated by the user, exiting...");
		}
	}

	public void generateReport(Staff staff) {
		viewCampCreated(staff);
		Camp campToReport = selectCamp(staff.getCampsCreated());
		Scanner scanner = new Scanner(System.in);
		GenerateReport staffGenerator = new StaffGenerateReport();
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
							staffGenerator.generateCommitteeList(campToReport, Format.CSV);
						} else if (format.equalsIgnoreCase("TXT")) {
							staffGenerator.generateCommitteeList(campToReport, Format.TXT);
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
							staffGenerator.generateAttendeeList(campToReport, Format.CSV);
						} else if (format2.equalsIgnoreCase("TXT")) {
							staffGenerator.generateAttendeeList(campToReport, Format.CSV);
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