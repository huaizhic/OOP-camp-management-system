package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.RegisterRole;

import java.util.ArrayList;

public class attendee {

	/**
	 * 
	 * @param student
	 */
	public ArrayList<Camp> viewOpenCamp(student_User Student) {
		// TODO - implement Attendee.viewOpenCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 */
	public int viewRemainingSlot(Camp camp) {
		// TODO - implement Attendee.viewRemainingSlot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 * @param student
	 * @param role
	 */
	public void register(Camp camp, student_User Student, RegisterRole role) {
		// TODO - implement Attendee.register
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 */
	public ArrayList<Camp> viewRegisteredCamp(student_User Student) {
		// TODO - implement Attendee.viewRegisteredCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 */
	public void withdraw(student_User Student, Camp camp) {
		// TODO - implement Attendee.withdraw
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param content
	 */
	public void submitEnquiry(student_User Student, Camp camp, String content) {
		// TODO - implement Attendee.submitEnquiry
		Enquiry newEnquiry = new Enquiry(content, student);    // create new enquiry object
		student.setEnquirySubmitted(newEnquiry);
		camp.setEnquiry(newEnquiry);
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 */
	public void viewEnquiry(student_User student, Camp camp) {
		// TODO - implement Attendee.viewEnquiry

		// array of enquiry objects, need to narrow down to a specific camp requested
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		for (Enquiry studentEnquiry: studentEnquiryArray){
			for (Enquiry campEnquiry: campEnquiryArray){
				if (studentEnquiry.getContent() == campEnquiry.getContent()){
					return studentEnquiry.getContent();
				}
			}
		}
		// throw new UnsupportedOperationException();
		return "error";
	}
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param enquiry
	 */
	public void editEnquiry(student_User student, Camp camp, int enquiryNo, String newMessage) {
		// TODO - implement Attendee.editEnquiry
		// key is to find the right enquiry from indexing
		// delete enquiry from both student and camp side
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		// ASSUMING enquiryNo is based on studentArray indexing, from the user POV
		// studentEnquiryArray.remove(enquiryNo);
		studentEnquiryArray.get(enquiryNo).setContent(newMessage);

		for (Enquiry campEnquiry: campEnquiryArray){
			if (campEnquiry.getSubmitter().getStudentID() == student.getStudentID()){
				// get correct index of enquiry from camp enquiry array, then remove it
				campEnquiry.setContent(newMessage);
				return;
			}
		}
	}
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param enquiry
	 */
	public void deleteEnquiry(student_User student, Camp camp, int enquiryNo) {
		// TODO - implement Attendee.deleteEnquiry
		// key is to find the right enquiry from indexing
		// delete enquiry from both student and camp side
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		// ASSUMING enquiryNo is based on studentArray indexing, from the user POV
		studentEnquiryArray.remove(enquiryNo);

		for (Enquiry campEnquiry: campEnquiryArray){
			if (campEnquiry.getSubmitter().getStudentID() == student.getStudentID()){
				// get correct index of enquiry from camp enquiry array, then remove it
				campEnquiryArray.remove(campEnquiry);
				return;
			}
		}

	}

		//throw new UnsupportedOperationException();
	}

}
