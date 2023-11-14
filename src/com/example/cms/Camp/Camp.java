package com.example.cms.Camp;
import com.example.cms.*;
import com.example.cms.Student.Committee_Member;
import com.example.cms.Student.Student_User;
import com.example.cms.UserGroup;
import com.example.cms.Staff.StaffMember;
import com.example.cms.Enquiries.Enquiry;
import java.time.LocalDate;
import java.util.ArrayList;

public class Camp {
	private String campName;
	private ArrayList<LocalDate> campDates = new ArrayList<>();
	private LocalDate regCloseDate;
	private ArrayList<UserGroup> userGroup = new ArrayList<>();
	private String location;
	private int totalSlots;
	private int remainingSlots;
	private int committeeSlots;
	private int remainingCommitteeSlots;
	private String description;
	private StaffMember staffInCharge;
	private ArrayList<Student_User> studentsRegistered = new ArrayList<>();
	private ArrayList<Committee_Member> committeeRegistered;
	private boolean visibility;
	private ArrayList<Enquiry> enquiry = new ArrayList<>();
	private ArrayList<Suggestion> suggestion = new ArrayList<>();
	private static int counter = -1;

	public Camp(){

	}

	public Camp(String campName, ArrayList<LocalDate> campDate, LocalDate regCloseDate, ArrayList<UserGroup> userGroup, String location, int totalSlots, StaffMember staff, boolean visibility){
		this.campName = campName;
		this.campDates = campDate;
		this.regCloseDate = regCloseDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		remainingSlots = totalSlots;
		committeeSlots = 10;
		remainingCommitteeSlots = committeeSlots;
		staffInCharge = staff;
		this.visibility = visibility;
		counter ++;
	}



	public static int getCounter() {
		return counter;
	}

	public static void printAllCampInfo(Camp camp){
		System.out.print("Camp: " + camp.getCampName() + " - ");
		System.out.print("Date: " + camp.getCampDates().get(0) + " to " + camp.getCampDates().get(1));
		System.out.print("Reg close date: " + camp.getRegCloseDate());
		System.out.print("Eligible group: " + camp.getUserGroup());
		System.out.print("location: " + camp.getLocation());
		System.out.print("Remaining slot: " + camp.getRemainingSlots());
		System.out.print("Staff: " + camp.getStaffInCharge());
		System.out.println("Description: " + camp.getDescription());
		System.out.println();
		System.out.println();

	}

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

	public ArrayList<UserGroup> getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(ArrayList<UserGroup> userGroup) {
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

	public int getRemainingCommitteeSlots() {
		return remainingCommitteeSlots;
	}

	public void setRemainingCommitteeSlots(int remainingCommitteeSlots){
		this.remainingCommitteeSlots = remainingCommitteeSlots;
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

	public ArrayList<Student_User> getStudentsRegistered() {
		return this.studentsRegistered;
	}


	public void setStudentsRegistered(ArrayList<Student_User> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

	public ArrayList<Committee_Member> getCommitteeRegistered() {
		return committeeRegistered;
	}

	public void setCommitteeRegistered(Committee_Member committeeMember) {
		this.committeeRegistered.add(committeeMember);
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