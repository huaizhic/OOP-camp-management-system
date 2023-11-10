package com.example.cms.Camp;
import com.example.cms.*;
import com.example.cms.Student.Student_User;
import com.example.cms.Staff.StaffMember;
import com.example.cms.Enquiries.Enquiry;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp {
	private String campName;
	private ArrayList<LocalDate> campDates;
	private LocalDate regCloseDate;
	private ArrayList<userGroup> userGroup;
	private String location;
	private int totalSlots;
	private int remainingSlots;
	private int committeeSlots;
	private String description;
	private StaffMember staffInCharge;
	private ArrayList<student_User> studentsRegistered;
	private boolean visibility;
	private ArrayList<Enquiry> enquiry ;
	private ArrayList<Suggestion> suggestion;

	public Camp(String campName, ArrayList<LocalDate> campDate, LocalDate regCloseDate, ArrayList<UserGroup> userGroup, String location, int totalSlots, StaffMember staff, boolean visibility){
		this.campName = campName;
		this.campDates = campDate;
		this.regCloseDate = regCloseDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		remainingSlots = totalSlots;
		committeeSlots = 10;
		staffInCharge = staff;
		studentsRegistered = new ArrayList<>();
		this.visibility = visibility;
		enquiry = new ArrayList<>();
		suggestion = new ArrayList<>();
	}

	// Add a hashmap

	public String getCampName() {
		return this.campName;
	}

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

	public ArrayList<userGroup> getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(ArrayList<userGroup> userGroup) {
		this.userGroup = userGroup;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	public int getRemainingSlots(){return this.remainingSlots;}

	public void setRemainingSlots(int remainingSlots){this.remainingSlots = remainingSlots;}

	public int getCommitteeSlots() {
		return this.committeeSlots;
	}

	public void setCommitteeSlots(int committeeSlots) {
		this.committeeSlots = committeeSlots;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StaffMember getStaffInCharge() {
		return this.staffInCharge;
	}

	public void setStaffInCharge(StaffMember staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	public ArrayList<student_User> getStudentsRegistered() {
		return this.studentsRegistered;
	}


	public void setStudentsRegistered(ArrayList<Student_User> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

	public boolean getVisibility() {
		return this.visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public ArrayList<Enquiry> getEnquiry() {
		return this.enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry.add(enquiry);
	}

	public ArrayList<Suggestion> getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(ArrayList<Suggestion> suggestion) {
		this.suggestion = suggestion;
	}
}