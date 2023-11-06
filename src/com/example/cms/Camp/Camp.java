package com.example.cms.Camp;
import com.example.cms.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp {

	private String campName;
	private ArrayList<LocalDate> campDates;
	private LocalDate regCloseDate;
	private ArrayList<UserGroup> userGroup;
	private String location;
	private int totalSlots;
	private int remainingSlots;
	private int committeeSlots = 10;
	private String description;
	private StaffMember staffInCharge;
	private ArrayList<StudentUser> studentsRegistered;
	private boolean visibility;
	private ArrayList<Enquiry> enquiry;
	private ArrayList<Suggestion> suggestion;
	private int attribute;

	public String getCampName() {
		return this.campName;
	}

	/**
	 * 
	 * @param campName
	 */
	public void setCampName(String campName) {
		this.campName = campName;
	}

	public ArrayList<LocalDate> getCampDates() {
		return this.campDates;
	}

	public void setCampDates(ArrayList<LocalDate> dates) {
		campDates = dates;
	}

	public LocalDate getRegCloseDate() {
		return this.regCloseDate;
	}

	public void setRegCloseDate(LocalDate regCloseDate) {
		this.regCloseDate = regCloseDate;
	}

	public ArrayList<UserGroup> getUserGroup() {
		return this.userGroup;
	}

	/**
	 * 
	 * @param userGroup
	 */
	public void setUserGroup(ArrayList<UserGroup> userGroup) {
		this.userGroup = userGroup;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	/**
	 * 
	 * @param totalSlots
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	public int getRemainingSlots(){return this.remainingSlots;}

	public void setRemainingSlots(int remainingSlots){this.remainingSlots = remainingSlots;}

	public int getCommitteeSlots() {
		return this.committeeSlots;
	}

	/**
	 * 
	 * @param committeeSlots
	 */
	public void setCommitteeSlots(int committeeSlots) {
		this.committeeSlots = committeeSlots;
	}


	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public StaffMember getStaffInCharge() {
		return this.staffInCharge;
	}

	/**
	 * 
	 * @param staffInCharge
	 */
	public void setStaffInCharge(StaffMember staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	public ArrayList<StudentUser> getStudentsRegistered() {
		return this.studentsRegistered;
	}


	public void setStudentsRegistered(ArrayList<StudentUser> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

	public boolean getVisibility() {
		return this.visibility;
	}

	/**
	 * 
	 * @param visibility
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public ArrayList<enquiry> getEnquiry() {
		// TODO - implement com.example.cms.Camp.getEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiry
	 */
	public void setEnquiry(ArrayList<enquiry> enquiry) {
		// TODO - implement com.example.cms.Camp.setEnquiry
		throw new UnsupportedOperationException();
	}

	public ArrayList<Suggestion> getSuggestion() {
		return this.suggestion;
	}

	/**
	 * 
	 * @param suggestion
	 */
	public void setSuggestion(ArrayList<Suggestion> suggestion) {
		this.suggestion = suggestion;
	}

}