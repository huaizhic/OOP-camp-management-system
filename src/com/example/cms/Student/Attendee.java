package com.example.cms.Student;

import com.example.cms.Camp.Camp;
import com.example.cms.Enquiries.Enquiry;
import com.example.cms.RegisterRole;

import java.util.ArrayList;

public class Attendee {

	/**
	 * 
	 * @param student
	 */
	public ArrayList<Camp> viewOpenCamp(Student_User Student) {
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
	public void register(Camp camp, Student_User Student, RegisterRole role) {
		// TODO - implement Attendee.register
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 */
	public ArrayList<Camp> viewRegisteredCamp(Student_User Student) {
		// TODO - implement Attendee.viewRegisteredCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 */
	public void withdraw(Student_User Student, Camp camp) {
		// TODO - implement Attendee.withdraw
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param content
	 */
	public void submitEnquiry(Student_User Student, Camp camp, String content) {
		// TODO - implement Attendee.submitEnquiry
		/* Enquiry newEnquiry = new Enquiry(content, student);    // create new enquiry object
		student.setEnquirySubmitted(newEnquiry);
		camp.setEnquiry(newEnquiry);
		//throw new UnsupportedOperationException(); */
		Enquiry newEnquiry = new Enquiry(Student.getStudentID(), Student.getName(), content, "datePlaceholder", null, false);
		newEnquiry.createEnquiry(Student.getStudentID(),Student.getName());
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 */
	public void viewEnquiry(Student_User student, Camp camp) {
		// TODO - implement Attendee.viewEnquiry

		// array of enquiry objects, need to narrow down to a specific camp requested
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		for (Enquiry studentEnquiry: studentEnquiryArray){
			for (Enquiry campEnquiry: campEnquiryArray){
				if (studentEnquiry.getContent() == campEnquiry.getContent()){
					System.out.println(studentEnquiry.getContent());
					return;
				}
			}
		}
		// throw new UnsupportedOperationException();
		// return;
	}
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param enquiry
	 */
	public void editEnquiry(Student_User student, Camp camp, int enquiryNo, String newMessage) {
		// TODO - implement Attendee.editEnquiry
		// key is to find the right enquiry from indexing
		// delete enquiry from both student and camp side
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		// ASSUMING enquiryNo is based on studentArray indexing, from the user POV
		// studentEnquiryArray.remove(enquiryNo);
		studentEnquiryArray.get(enquiryNo).setContent(newMessage);

		for (Enquiry campEnquiry: campEnquiryArray){
			if (campEnquiry.getStudentID() == student.getStudentID()){
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
	public void deleteEnquiry(Student_User student, Camp camp, int enquiryNo) {
		// TODO - implement Attendee.deleteEnquiry
		// key is to find the right enquiry from indexing
		// delete enquiry from both student and camp side
		ArrayList<Enquiry> studentEnquiryArray = student.getEnquirySubmitted();
		ArrayList<Enquiry> campEnquiryArray = camp.getEnquiry();

		// ASSUMING enquiryNo is based on studentArray indexing, from the user POV
		studentEnquiryArray.remove(enquiryNo);

		for (Enquiry campEnquiry: campEnquiryArray){
			if (campEnquiry.getStudentID() == student.getStudentID()){
				// get correct index of enquiry from camp enquiry array, then remove it
				campEnquiryArray.remove(campEnquiry);
				return;
			}
		}

	}

		//throw new UnsupportedOperationException();
	}

}
