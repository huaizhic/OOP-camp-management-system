package Staff;

import restOfitems.Camp;
import restOfitems.Enquiry;

import java.text.Format;
import java.util.ArrayList;

public class Staff {

	/**
	 * 
	 * @param name
	 */
	public void createCamp(String name) {
		// TODO - implement Staff.createCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 */
	public void editCamp(Staff staff, int camp) {
		// TODO - implement Staff.editCamp
		throw new UnsupportedOperationException();
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

	/**
	 * 
	 * @param staff
	 * @param camp
	 */
	public void viewEnquiry(StaffMember staffMember, int campNo) {
		// TODO - implement Staff.viewEnquiry
		// throw new UnsupportedOperationException();

		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
		Camp campSelected = campsCreatedArray.get(campNo);
		System.out.println(campSelected.getEnquiry());
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
		// throw new UnsupportedOperationException();

		// at this stage, we assume that only either committee member OR staff can use a reply slot per enquiry.
		ArrayList<Camp> campsCreatedArray = staffMember.getCampsCreated();
		Camp selectedCamp = campsCreatedArray.get(campNo);  // select camp object instance
		ArrayList<Enquiry> campEnquiryArray = selectedCamp.getEnquiry();
		Enquiry selectedEnquiry = campEnquiryArray.get(enquiryNo);
		selectedEnquiry.setReply(reply);
		selectedEnquiry.setProcessed(true);

	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 */
	public void viewSuggestion(Staff staff, int camp) {
		// TODO - implement Staff.viewSuggestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param staff
	 * @param camp
	 * @param suggestion
	 */
	public void approveSuggestion(Staff staff, int camp, int suggestion) {
		// TODO - implement Staff.approveSuggestion
		throw new UnsupportedOperationException();
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