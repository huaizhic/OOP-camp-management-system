package com.example.cms.Student;

import com.example.cms.Status;
import com.example.cms.Suggestion;
import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.Format;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Committee {

	public void viewEnquiry(Camp camp) {
		// TODO - implement Committee.viewEnquiry
		// view all enquiries from a camp
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();
		for (Enquiry enquiry: campEnquiryArray){
			System.out.println(campEnquiryArray.indexOf(enquiry) + ". " + enquiry.getContent());
		}
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param reply
	 */
	public void replyEnquiry(Student_User student, Camp camp, String reply) {
		// TODO - implement Committee.replyEnquiry
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();
		for (Enquiry studentEnquiry: studentEnquiryArray){
			for (Enquiry campEnquiry: campEnquiryArray){
				if (studentEnquiry.equals(campEnquiry)){
					studentEnquiry.setReply(reply);
					studentEnquiry.setProcessed(true);
					campEnquiry.setReply(reply);
					campEnquiry.setProcessed(true);
					return;
				}
			}
		}
	}

	//throw new UnsupportedOperationException();


	/**
	 * 
	 * @param camp
	 * @param suggestion
	 * @param student
	 */
	public String submitSuggestion(Camp camp, Committee_Member committeeMember) {
		Scanner input = new Scanner(System.in);
		System.out.println("You are about to submit a suggestion to the camp: " + committeeMember.getRegisteredCamps());
		String subject;
		boolean uniqueSubject = true; // a do-while loop to make sure that the subject name is unique
		do {System.out.println("Insert an unique subject of the suggestion or \"exit\" in lower case to exit");
		subject = input.nextLine();
		if(subject.equals("exit")){
			System.out.println("Action terminated by the user");
			return "exiting...";
		}
		for(Suggestion suggestion : Suggestion.getSuggestionArrayList()){
			if(suggestion.getSubject().equals(subject)){
				System.out.println("This name has already been taken, please have an unique subject name");
				uniqueSubject = false;
				break;
			}
		}
		}while(!uniqueSubject);

		System.out.println("Enter the content of the suggestion or \"exit\" in lower case to exit");
		String content = input.nextLine();
		if(content.equals("exit")){
			System.out.println("Action terminated by the user");
			return "exiting...";
		}
		LocalDate todayDate = LocalDate.now();
		System.out.println("Confirm to submit? Enter \"exit\" in lower case to exit ");
		if(input.nextLine().equals("exit")){
			System.out.println("Action terminated by the user");
			return "exiting...";
		}
		Suggestion.setSuggestionArrayList(new Suggestion(subject, content, todayDate, (Committee_Member) this));
		Suggestion.addSuggestionToMap(subject, Suggestion.getSuggestionArrayList().get(Suggestion.getCounter()));
		return "Your suggestion has been successfully submitted";
		/*int index = -1;
		for(Suggestion suggestion : Suggestion.getSuggestionArrayList()){
			if(suggestion.getSubject().equals(subject)){
				index = Suggestion.getSuggestionArrayList().indexOf(suggestion);
			}
		}
		Suggestion.addSuggestionToMap(subject, Suggestion.getSuggestionArrayList().get(index));*/
	}


	public void viewSuggestion(Committee_Member committeeMember) {
		if(committeeMember.getSuggestions().isEmpty()){
			System.out.println("No suggsetion has been submitted");
		}else {
			for (Suggestion suggestion : committeeMember.getSuggestions()) {
				Suggestion.printSuggestionInfo(suggestion);
			}
		}
	}


	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public String editSuggestion( Committee_Member committeeMember, int suggestionNo, String newContent) {

	}


	public String deleteSuggestion(Committee_Member committeeMember, int suggestionNo) {
		Scanner input = new Scanner(System.in);
		if (committeeMember.getSuggestions().isEmpty()) {
			return "No suggestion has been submitted";
		} else {
			for (Suggestion suggestion : committeeMember.getSuggestions()) {
				Suggestion.printSuggestionInfo(suggestion);
			}
			Suggestion suggestionToBeDel;
			do {
				System.out.println("Please select the suggestion that you want to delete by SUBJECT or enter exit in lower case to exit");
				String suggestionToBeDelStr = input.nextLine();
				if (suggestionToBeDelStr.equals("exit")) {
					System.out.println("Action terminated by user");
					return "exiting...";
				}
				suggestionToBeDel = Suggestion.getSuggestionHashMap().get(suggestionToBeDelStr);
				if (suggestionToBeDel == null) {
					System.out.println("Please insert the correct subject name");
				}
			} while (suggestionToBeDel == null);
			if (suggestionToBeDel.getProcessed()) {
				System.out.println("Cannot be deleted, the suggestion has been processed");
				return "exiting...";
			} else {
				System.out.println("Do you confirm to delete the suggestion: " + suggestionToBeDel.getSubject());
				System.out.println("Enter confirm or any other key to cancel");
				if (input.nextLine().equalsIgnoreCase("confirm")) {
					((Committee_Member) this).getSuggestions().remove(suggestionToBeDel);
					Suggestion.getSuggestionHashMap().remove(suggestionToBeDel.getSubject());
					Suggestion.getSuggestionArrayList().remove(suggestionToBeDel);
					int originalCounter = Suggestion.getCounter();
					Suggestion.setCounter(originalCounter - 1);
					return "Suggestion deleted successfully, exiting...";
				} else {
					System.out.println("Action terminated by user");
					return "exiting...";
				}
			}
		}
	}

	/**
	 * 
	 * @param camp
	 * @param format
	 */
	public void generateReport(Camp camp, Format format) {
		// TODO - implement Committee.generateReport
		throw new UnsupportedOperationException();
	}

}
