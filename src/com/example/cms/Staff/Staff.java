package com.example.cms.Staff;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.campData;
import com.example.cms.DisplayOptions.DisplayBySort;
import com.example.cms.DisplayOptions.SortApp;
import com.example.cms.DisplayOptions.SearchApp;
import com.example.cms.DisplayOptions.SortByName_Default;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Status;
import com.example.cms.Suggestion;
import com.example.cms.UserGroup;

import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Staff {
	private Scanner input;

	public void createCamp() {
		input = new Scanner(System.in);
		System.out.println("===== Creating a New Camp =====");
		System.out.println("    Please insert the new camp name");
		String campName = input.nextLine();
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
		ArrayList<UserGroup> userGroups = new ArrayList<>();
			do {
				System.out.println("    Please insert the school/faculty that have access to the camp separated by space");
				String userGroupString = input.nextLine();
				String[] usergroups = userGroupString.split("\\s+");
				ArrayList<String> userGroupArray = new ArrayList<>();
				Collections.addAll(userGroupArray, usergroups);
				for (String usergroup : userGroupArray) {
					for (UserGroup enumUserGroup : UserGroup.values()) {
						if (usergroup.equals(enumUserGroup.name())) {
							userGroups.add(enumUserGroup);
							break;
						}
					}
				}
				if (userGroups.size() == userGroupArray.size()) {
					System.out.println("The following school/faculty can view and register");
					for (UserGroup userGroup : userGroups) {
						System.out.print(userGroup + " ");
					}
				} else {
					System.out.println("Invalid input has received, only the following is valid, continue? ");
					for (UserGroup userGroup : userGroups) {
						System.out.print(userGroup + " ");
					}
					do {
						System.out.println("Insert Yes or Re-enter");
						ans = input.next();
                        conti = ans.equals("Yes");
					} while (!ans.equals("Yes") && !ans.equals("Re-enter"));
				}
			}while(!conti);

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

			campData.setCampList(new Camp(campName, campDate, regCloseDate, userGroups, location, slots, (StaffMember) this, visibility));
			campData.addCampToMap(campName, campData.getCampList().get(Camp.getCounter()));

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


	public void editCamp(StaffMember staff) {
		viewCampCreated(staff);
			boolean completedEditing = false;
			do {
				Camp campToBeEdited = null;
				String campToEditString;
				do{System.out.println("Please insert the camp name that you would like to edit");
				campToEditString = input.nextLine();
				campToBeEdited = campData.getCampHashMap().get(campToEditString);
					if(campToBeEdited == null){
						System.out.println("The camp does not exist, please verify the CAMP NAME");
					}
				}while(campToBeEdited == null);

				System.out.println("====What camp information would you like to edit====");
				System.out.println("1. Camp name");
				System.out.println("2. Camp location");
				System.out.println("3. Camp start date");
				System.out.println("4. Camp end date");
				System.out.println("5. Camp registration close date");
				System.out.println("6. Camp total slots");
				System.out.println("7. Camp eligible user group");
				int thingsToEdit = input.nextInt();
				switch(thingsToEdit){
					case(1):
						System.out.println("Enter the new camp name");
						String newCampName = input.nextLine();
						campToBeEdited.setCampName(newCampName);
						campData.getCampHashMap().remove(campToEditString);
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
						if(campToBeEdited.getStudentsRegistered().size() > newAttendeeSlot){
							System.out.println("The number of registered attendee has already passed the new slot number");
						}else{
							eligibleSlot = true;
							campToBeEdited.setRemainingSlots(newAttendeeSlot - campToBeEdited.getStudentsRegistered().size());
							campToBeEdited.setTotalSlots(newAttendeeSlot);
							System.out.println("The new camp attendee slots is now: " + campToBeEdited.getTotalSlots());
							System.out.println("Any other camp to edit? Yes or No");
						}
						} while(!eligibleSlot);
						break;
					case(7):
						boolean conti = true;
						String ans;
						ArrayList<UserGroup> userGroups = new ArrayList<>();
						do {
							System.out.println("    Please insert the school/faculty that have access to the camp separated by space");
							String userGroupString = input.nextLine();
							String[] usergroups = userGroupString.split("\\s+");
							ArrayList<String> userGroupArray = new ArrayList<>();
							Collections.addAll(userGroupArray, usergroups);
							for (String usergroup : userGroupArray) {
								for (UserGroup enumUserGroup : UserGroup.values()) {
									if (usergroup.equals(enumUserGroup.name())) {
										userGroups.add(enumUserGroup);
										break;
									}
								}
							}
							if (userGroups.size() == userGroupArray.size()) {
								System.out.println("The following school/faculty can view and register");
								for (UserGroup userGroup : userGroups) {
									System.out.print(userGroup + " ");
								}
							} else {
								System.out.println("Invalid input has received, only the following is valid, continue? ");
								for (UserGroup userGroup : userGroups) {
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
				}
				String ans;
				do {
					System.out.println("Any other camp to edit? Yes or No");
					ans = input.nextLine();
					completedEditing = (ans == "No");
				}while(ans != "Yes" && ans != "No");
			}while(!completedEditing);
		}


	public void deleteCamp(StaffMember staff) {
		if(staff.getCampsCreated().isEmpty()){
			System.out.println("No camp has been created");
		}
		else {
			viewCampCreated(staff);
			String campNameToDeleteStr;
			Camp campToBeDeleted = null;
			do {
				System.out.println("Please select the camp by camp NAME that you would like to delete");
				campNameToDeleteStr = input.nextLine();
				campToBeDeleted = campData.getCampHashMap().get(campNameToDeleteStr);
				if (campToBeDeleted == null) {
					System.out.println("The camp does not exist, please verify the CAMP NAME");
				}
			} while (campToBeDeleted == null);
			if (campToBeDeleted.getStudentsRegistered().isEmpty() && campToBeDeleted.getCommitteeRegistered().isEmpty()) {
				System.out.println("Confirm to delete Camp: " + campNameToDeleteStr +". Insert Confirm or any other key");
				if(input.nextLine().equals("Confirm")){
					staff.getCampsCreated().remove(campToBeDeleted);
					campData.getCampHashMap().remove(campNameToDeleteStr);
				}
			} else {
				System.out.println("There is already attendee or committee registered, the camp cannot be deleted");
			}
		}
	}

	public void viewAllCamps() {
		System.out.println("Please view all camps with the following ways:");
		System.out.println("1. Default: alphabetical order of camp name");
		System.out.println("2. Search for keywords");
		System.out.println("3. Sort by camp features");
		int choice = input.nextInt();
		switch (choice) {
			case (1):
				DisplayBySort aOb = new SortByName_Default();
				ArrayList<Camp> sorted = aOb.Sorting(campData.getCampList());
				for (Camp camp : sorted) {
					Camp.printAllCampInfo(camp);
				}
				break;
			case (2):
				ArrayList<Camp> afterSearchCamp;
				do {
					afterSearchCamp = SearchApp.startSearch(campData.getCampList());
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
					afterSortCamp = SortApp.startSorting(campData.getCampList());
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



	public void viewEnquiry(StaffMember staff, int campNo) {
		viewCampCreated(staff);
		String campNameToViewEnquiryStr;
		Camp campNameToViewEnquiry = null;
		do {
			System.out.println("Please select the camp that you have created by camp NAME that you would like to see the enquiry");
			campNameToViewEnquiryStr = input.nextLine();
			campNameToViewEnquiry = campData.getCampHashMap().get(campNameToViewEnquiryStr);
			if (campNameToViewEnquiry == null) {
				System.out.println("The camp does not exist, please verify the CAMP NAME");
			}
		} while (campNameToViewEnquiry == null);
		Enquiry.




		ArrayList<Camp> campsCreatedArray = staff.getCampsCreated();
		Camp campSelected = campsCreatedArray.get(campNo);
		System.out.println(campSelected.getEnquiry());
		// throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 * @param enquiry
	 * @param reply
	 */
	public void replyEnquiry(StaffMember staffMember, int campNo, int enquiryNo, String reply) {
		// TODO - implement Staff.replyEnquiry
		// at this stage, we assume that only either committee member OR staff can use a reply slot per enquiry.
		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
		Camp selectedCamp = campsCreatedArray.get(campNo);  // select camp object instance
		ArrayList<Enquiry> campEnquiryArray = selectedCamp.getEnquiry();
		Enquiry selectedEnquiry = campEnquiryArray.get(enquiryNo);
		selectedEnquiry.setReply(reply);
		selectedEnquiry.setProcessed(true);

		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 */
	public void viewSuggestion(StaffMember staffMember, int campNo) {
		// TODO - implement Staff.viewSuggestion
		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
		Camp campSelected = campsCreatedArray.get(campNo);
		System.out.println(campSelected.getSuggestion());
		// throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param staff
	 * @param camp
	 * @param suggestion
	 */
	public void approveSuggestion(StaffMember staffMember, int campNo, int suggestionNo, Status status) {
		// TODO - implement Staff.approveSuggestion
		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
		Camp selectedCamp = campsCreatedArray.get(campNo);  // select camp object instance
		ArrayList<Suggestion> campSuggestionArray = selectedCamp.getSuggestion();
		Suggestion selectedSuggestion = campSuggestionArray.get(suggestionNo);
		selectedSuggestion.setStatus(status);
		selectedSuggestion.setProcessed(true);
		// throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 * @param format
	 */
	public void generateCampReport(Staff staff, int camp, Format format) {
		// TODO - implement Staff.generateCampReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 * @param format
	 */
	public void generateCommitteeReport(Staff staff, Camp camp, Format format) {
		// TODO - implement Staff.generateCommitteeReport
		throw new UnsupportedOperationException();
	}


}