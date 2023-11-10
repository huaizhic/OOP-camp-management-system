package com.example.cms.Staff;
import com.example.cms.Camp.*;
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


	public void editCamp(StaffMember staff) {
		if(staff.getCampsCreated().isEmpty()){
			System.out.println("No camp has been created, please create one before editing");
		}else{
			System.out.println("Please choose the camp that you would like to edit");
			for(Camp camp: staff.getCampsCreated()) {
				camp.printAllCampInfo(camp);
			}
		}

	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 */
	public void deleteCamp(Staff staff, int camp) {
		// TODO - implement Staff.deleteCamp
		throw new UnsupportedOperationException();
	}

	public void viewAllCamps() {
		// TODO - implement Staff.viewAllCamps
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 */
	public ArrayList<Camp> viewCreatedCamps(Staff staff) {
		// TODO - implement Staff.viewCreatedCamps
		throw new UnsupportedOperationException();
	}


	public void viewEnquiry(StaffMember staffMember, int campNo) {
		// TODO - implement Staff.viewEnquiry
		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
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

	public void operation() {
		// TODO - implement Staff.operation
		throw new UnsupportedOperationException();
	}

}