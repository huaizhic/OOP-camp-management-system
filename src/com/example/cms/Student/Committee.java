package com.example.cms.Student;

import com.example.cms.Suggestion;
import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;

import java.util.ArrayList;

public class Committee {

	private int point;

	public Camp viewRegisteredCamp(Student_User Student) {

	}

	/**
	 * 
	 * @param camp
	 */
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
	public void submitSuggestion(Camp camp, String suggestion, Student_User Student) {
		// TODO - implement Committee.submitSuggestion
		ArrayList<Suggestion> suggestionsArray = camp.getSuggestion();
		Suggestion newSuggestion = new Suggestion(submitter, suggestion);
		suggestionsArray.add(newSuggestion);

		ArrayList<Suggestion> commMemberSuggestions = submitter.getSuggestionSubmitted();
		commMemberSuggestions.add(newSuggestion);
	}


	//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 */
	public void viewSuggestion(Student_User Student) {
		// TODO - implement Committee.viewSuggestion
		ArrayList<Suggestion> suggestionsArray = Committee_Member.getSuggestionSubmitted();
		for (Suggestion suggestion: suggestionsArray){
			System.out.println(suggestionsArray.indexOf(suggestion) + suggestion.getContent());
		}
	}
		//throw new UnsupportedOperationException();


	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void editSuggestion(Student_User Student, int suggestionNo, String newContent) {
		// TODO - implement Committee.editSuggestion
		ArrayList<Suggestion> suggestionsArray = Committee_Member.getSuggestionSubmitted();
		Suggestion suggestionToEdit = suggestionsArray.get(suggestionNo);  // gets the specified suggestion obj from ArrayList
		suggestionToEdit.setContent(newContent);

		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void deleteSuggestion(Student_User Student, int suggestionNo) {
		// TODO - implement Committee.deleteSuggestion
		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		suggestionsArray.remove(suggestionNo);
		//throw new UnsupportedOperationException();
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

	public int getPoint() {
		return this.point;
	}

	/**
	 * 
	 * @param point
	 */
	public void setPoint(int point) {
		this.point = point;
	}

}
