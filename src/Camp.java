import Package2.*;
import Package.*;

public class Camp {

	private String campName;
	private ArrayList<Date> dates;
	private Date regCloseDate;
	private ArrayList<UserGroup> userGroup;
	private String location;
	private int totalSlots;
	private int committeeSlots = 10;
	private String description;
	private StaffMember staffInCharge;
	private ArrayList<Student> studentsRegistered;
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

	public ArrayList<Date> getDates() {
		return this.dates;
	}

	/**
	 * 
	 * @param dates
	 */
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public Date getRegCloseDate() {
		return this.regCloseDate;
	}

	/**
	 * 
	 * @param regCloseDate
	 */
	public void setRegCloseDate(Date regCloseDate) {
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

	public void getAttribute() {
		// TODO - implement Camp.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute) {
		this.attribute = attribute;
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

	public ArrayList<Student> getStudentsRegistered() {
		return this.studentsRegistered;
	}

	/**
	 * 
	 * @param studentsRegistered
	 */
	public void setStudentsRegistered(ArrayList<Student> studentsRegistered) {
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
		// TODO - implement Camp.getEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiry
	 */
	public void setEnquiry(ArrayList<enquiry> enquiry) {
		// TODO - implement Camp.setEnquiry
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