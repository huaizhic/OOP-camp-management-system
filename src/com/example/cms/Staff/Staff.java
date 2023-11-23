package com.example.cms.Staff;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.campData;
import com.example.cms.DisplayOptions.*;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Faculty;
import com.example.cms.Status;
import com.example.cms.Suggestions.Suggestion;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Staff {
	private Scanner input;

	public void createCamp() {
		input = new Scanner(System.in);
		System.out.println("===== Creating a New Camp =====");
		boolean uniqueCampName = true;
		String campName;
		do {
			System.out.println("    Please insert the new camp name that is unique");
			campName = input.nextLine();
			for (Camp camp : campData.getCampList()) {
				if (camp.getCampName().equals(campName)) {
					System.out.println("This name has already been taken, please have an unique camp name");
					uniqueCampName = false;
					break;
				}
			}
		}while(!uniqueCampName);
		System.out.println("    Camp name: " + campName);

		System.out.println("    Please insert the starting date of the camp in the format yyyy-mm-dd");
		LocalDate startDate = null;
		do{String dateString = input.nextLine();
		try{
			startDate = LocalDate.parse(dateString);
			System.out.println("Starting date: " + startDate);
		}catch(DateTimeParseException e){
			System.out.println("Please insert the data in the correct format: yyyy-mm-dd");
		}}while(startDate == null);

		System.out.println("    Please insert the ending date of the camp in the format yyyy-mm-dd");
		LocalDate endDate = null;
		do{String endDateString = input.nextLine();
			try{
				endDate = LocalDate.parse(endDateString);
				System.out.println("End date: " + endDate);
			}catch(DateTimeParseException e){
				System.out.println("Please insert the ending data in the correct format: yyyy-mm-dd");
			}}while(endDate == null);

		System.out.println("    Please insert the registration close date of the camp in the format yyyy-mm-dd");
		LocalDate regCloseDate = null;
		do{String regCloseString = input.nextLine();
			try{
				regCloseDate = LocalDate.parse(regCloseString);
				System.out.println("Registration Close Date: " + regCloseDate);
			}catch(DateTimeParseException e){
				System.out.println("Please insert the registration close data in the correct format: yyyy-mm-dd");
			}}while(regCloseDate == null);

		boolean conti = true;
		String ans;
		ArrayList<Faculty> userGroups = new ArrayList<>();
		System.out.println("Is the camp open to all NTU faculties?");
		String allNTU = null;
		do{
			System.out.println("Enter 'Yes' or 'No' ");
			allNTU = input.next();
			if(allNTU.compareToIgnoreCase("Yes") ==0 ){
				userGroups.add(Faculty.ALL);
			}

		}while(allNTU.compareTo("Yes") != 0 && allNTU.compareToIgnoreCase("No") != 0);

		if(userGroups.contains(Faculty.ALL)){
			System.out.println("The camp is open to all NTU schools");
		}else {

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
			} while (!conti);
		}

			System.out.println("    Nearly there... Insert the location of the camp");
			String location = input.nextLine();
			System.out.println("Location: " + location);

			System.out.println("    Enter the total slot of the attendee");
			int slots = input.nextInt();
			System.out.println("The total slot is " + slots);

			String visibilityStr;
			boolean visibility;

			do {
				System.out.println("Enter the visibility of the camp: On or Off");
				visibilityStr = input.next();
				visibility = visibilityStr.equals("On");
			}while(!visibilityStr.equals("On") && !visibilityStr.equals("Off"));

			ArrayList<LocalDate> campDate = new ArrayList<>();
			campDate.add(startDate);
			campDate.add(endDate);
			Camp newCamp = new Camp(campName, campDate, regCloseDate, userGroups, location, slots, (StaffMember) this, visibility);

			campData.setCampList(newCamp);
			campData.addCampToMap(campName, newCamp);
		((StaffMember) this).setCampsCreated(newCamp);

	}

	private void viewCampCreated(StaffMember staff){
		if(staff.getCampsCreated().isEmpty()){
			System.out.println("No camp has been created, please create one before editing");
		}else {
			System.out.println("Please view the camp that you have created with the following ways:");
			System.out.println("1. Default: alphabetical order of camp name");
			System.out.println("2. Search for keywords");
			System.out.println("3. Sort by camp features");
			int choice = input.nextInt();
			switch (choice) {
				case (1):
					DisplayBySort aOb = new SortByName_Default();
					ArrayList<Camp> sorted = aOb.Sorting(staff.getCampsCreated());
					for (Camp camp : sorted) {
						Camp.printAllCampInfo(camp);
					}
					break;
				case (2):
					ArrayList<Camp> afterSearchCamp;
					do {
						afterSearchCamp = SearchApp.startSearch(staff.getCampsCreated());
						if (afterSearchCamp == null) {
							System.out.println("Please choose a correct option");
						} else {
							for (Camp camp : afterSearchCamp) {
								Camp.printAllCampInfo(camp);
							}
						}
					} while (afterSearchCamp == null);
				case (3):
					ArrayList<Camp> afterSortCamp;
					do {
						afterSortCamp = SortApp.startSorting(staff.getCampsCreated());
						if (afterSortCamp == null) {
							System.out.println("Please choose a correct option");
						} else {
							for (Camp camp : afterSortCamp) {
								Camp.printAllCampInfo(camp);
							}
						}
					} while (afterSortCamp == null);
			}
		}
	}
	private Camp selectCamp(ArrayList<Camp> campArrayList){
		String campToBeSelectedStr;
		Camp campToBeSelected = null;
		do{System.out.println("Please insert the camp name or enter exit to cancel in lower case");
			campToBeSelectedStr = input.nextLine();
			if(campToBeSelectedStr.equals("exit")){
				return null;
			}
			else {
				campToBeSelected = campData.getCampHashMap().get(campToBeSelectedStr);
				if (campToBeSelected == null) {
					System.out.println("The camp does not exist, please verify the CAMP NAME");
				}
			}
		}while(campToBeSelected == null);
		return campToBeSelected;
	}


	public String editCamp(StaffMember staff) {
		viewCampCreated(staff);
			boolean completedEditing = false;
			do {
				Camp campToBeEdited = selectCamp(staff.getCampsCreated());
				if(campToBeEdited == null){
					return "Action terminated, exiting...";
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
						System.out.println("Action terminated by the user");
						return "Exiting...";
				}
				String ans;
				do {
					System.out.println("Any other camp to edit? Yes or No");
					ans = input.nextLine();
					completedEditing = (ans == "No");
				}while(ans != "Yes" && ans != "No");
			}while(!completedEditing);

			return "Edit successful!";
		}


	public String deleteCamp(StaffMember staff) {
		if(staff.getCampsCreated().isEmpty()){
			System.out.println("No camp has been created");
			return "exiting...";
		}
		else {
			viewCampCreated(staff);
			Camp campToBeDeleted = selectCamp(staff.getCampsCreated());
			if (campToBeDeleted == null) {
				return null;
			} else {
				String campNameToDeleteStr = campToBeDeleted.getCampName();
				if (campToBeDeleted.getAttendeesRegistered().isEmpty() && campToBeDeleted.getCommitteeRegistered().isEmpty()) {
					System.out.println("Confirm to delete Camp: " + campNameToDeleteStr + ". Insert Confirm or any other key");
					if (input.nextLine().equals("Confirm")) {
						staff.getCampsCreated().remove(campToBeDeleted);
						campData.getCampList().remove(campToBeDeleted);
						campData.getCampHashMap().remove(campNameToDeleteStr);
						//int originalCounter = Camp.getCounter();
						//Camp.setCounter(originalCounter - 1);
						return "Camp deletes successfully";
					}
					else{
						System.out.println("Action terminated by user");
						return "exiting...";
					}
				} else {
					System.out.println("There is already attendee or committee registered, the camp cannot be deleted");
					return "Action terminated, exiting...";
				}
			}
		}
	}

	public void viewAllCamps() {
		ArrayList<Camp> campArrayList = DisplayApp.viewAllCamp();
		if(campArrayList == null){
			System.out.println("Action terminated");
		}else{
			for(Camp camp: campArrayList){
				Camp.printAllCampInfo(camp);
			}
		}

	}

	public void viewEnquiry(StaffMember staff) {
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

	public void replyEnquiry(StaffMember staff) {
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
		}else{
			System.out.println("Action terminated by the user, exiting...");
		}
	}

	public void viewSuggestion(StaffMember staff) {
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


	public void approveSuggestion(StaffMember staff) {
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
			suggestionToApprove.getSubmitter().setPoints();
			System.out.println("The suggestion has been processed and your response has been successfully recorded");
		}else{
			System.out.println("Action terminated by the user, exiting...");
		}
	}


}