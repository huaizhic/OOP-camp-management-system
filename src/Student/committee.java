package Student;

import restOfitems.Camp;
import restOfitems.Enquiry;
import restOfitems.Suggestion;

import java.text.Format;
import java.util.ArrayList;

public class committee {

	private int point;

	/**
	 * 
	 * @param student
	 */
	public Camp viewRegisteredCamp(Student student) {
		// TODO - implement Committee.viewRegisteredCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 */
	public void viewEnquiry(Camp camp) {
		// TODO - implement Committee.viewEnquiry
		// throw new UnsupportedOperationException();

		// view all enquiries from a camp
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();
		for (Enquiry enquiry: campEnquiryArray){
			System.out.println(campEnquiryArray.indexOf(enquiry) + ". " + enquiry.getContent());
		}
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param reply
	 */
	public void replyEnquiry(Student student, Camp camp, String reply) {
		// TODO - implement Committee.replyEnquiry
		// throw new UnsupportedOperationException();
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

	/**
	 * 
	 * @param camp
	 * @param suggestion
	 * @param student
	 */
	public void submitSuggestion(Camp camp, String suggestion, committeeMember submitter) {
		// TODO - implement Committee.submitSuggestion
		// throw new UnsupportedOperationException();
		ArrayList<Suggestion> suggestionsArray = camp.getSuggestion();
		Suggestion newSuggestion = new Suggestion(submitter, suggestion);
		suggestionsArray.add(newSuggestion);

		ArrayList<Suggestion> commMemberSuggestions = submitter.getSuggestionSubmitted();
		commMemberSuggestions.add(newSuggestion);
	}

	/**
	 * 
	 * @param student
	 */
	public void viewSuggestion(committeeMember committeeMember) {
		// TODO - implement Committee.viewSuggestion
		// throw new UnsupportedOperationException();

		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		for (Suggestion suggestion: suggestionsArray){
			System.out.println(suggestionsArray.indexOf(suggestion) + suggestion.getContent());
		}
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void editSuggestion(committeeMember committeeMember, int suggestionNo, String newContent) {
		// TODO - implement Committee.editSuggestion
		// throw new UnsupportedOperationException();

		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		Suggestion suggestionToEdit = suggestionsArray.get(suggestionNo);  // gets the specified suggestion obj from ArrayList
		suggestionToEdit.setContent(newContent);
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void deleteSuggestion(committeeMember committeeMember, int suggestionNo) {
		// TODO - implement Committee.deleteSuggestion
		// throw new UnsupportedOperationException();
		ArrayList<Suggestion> suggestionsArray = committeeMember.getSuggestionSubmitted();
		suggestionsArray.remove(suggestionNo);
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