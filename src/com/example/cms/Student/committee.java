package com.example.cms.Student;


import com.example.cms.Camp.Camp;

import java.text.Format;

public class committee {

	private int point;

	/**
	 * 
	 * @param student
	 */
	public Camp viewRegisteredCamp(student_User Student) {
		// TODO - implement Committee.viewRegisteredCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 */
	public void viewEnquiry(Camp camp) {
		// TODO - implement Committee.viewEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param camp
	 * @param reply
	 */
	public void replyEnquiry(student_User Student, Camp camp, String reply) {
		// TODO - implement Committee.replyEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 * @param suggestion
	 * @param student
	 */
	public void submitSuggestion(Camp camp, String suggestion, student_User Student) {
		// TODO - implement Committee.submitSuggestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 */
	public void viewSuggestion(student_User Student) {
		// TODO - implement Committee.viewSuggestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void editSuggestion(student_User Student, int suggestion) {
		// TODO - implement Committee.editSuggestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param suggestion
	 */
	public void deleteSuggestion(student_User Student, int suggestion) {
		// TODO - implement Committee.deleteSuggestion
		throw new UnsupportedOperationException();
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
