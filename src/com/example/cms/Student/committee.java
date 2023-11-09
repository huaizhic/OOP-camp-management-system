package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;

import java.util.ArrayList;

public class committee {

	private int point;

	public Camp viewRegisteredCamp(student_User Student) {

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
	public void replyEnquiry(student_User student, Camp camp, String reply) {
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
	}

	/**
	 * 
	 * @param camp
	 * @param suggestion
	 * @param student
	 */
	public void submitSuggestion(Camp camp, String suggestion, student_User Student) {
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
	public void viewSuggestion(student_User Student) {
		// TODO - implement Committee.viewSuggestion
		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		for (Suggestion suggestion: suggestionsArray){
			System.out.println(suggestionsArray.indexOf(suggestion) + suggestion.getContent());
		}
	}
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void editSuggestion(student_User Student, int suggestionNo) {
		// TODO - implement Committee.editSuggestion
		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		Suggestion suggestionToEdit = suggestionsArray.get(suggestionNo);  // gets the specified suggestion obj from ArrayList
		suggestionToEdit.setContent(newContent);

		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void deleteSuggestion(student_User Student, int suggestionNo) {
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
