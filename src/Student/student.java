package Student;


public class Student extends attendee {

	private String studentID;
	private String password = "password";
	private String name;
	private UserGroup userGroup;
	private ArrayList<Camp> registeredCamps;
	private boolean campCommittee;
	private ArrayList<Camp> notRegisterable;
	private String securityQuestion;
	private String securityAns;
	private ArrayList<Enquiry> enquirySubmitted;

	public Student() {
		// TODO - implement Student.Student
		throw new UnsupportedOperationException();
	}

	public String getStudentID() {
		return this.studentID;
	}

	public String getName() {
		return this.name;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	/**
	 * 
	 * @param userGroup
	 */
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public ArrayList<Camp> getRegisteredCamps() {
		return this.registeredCamps;
	}

	/**
	 * 
	 * @param registeredCamps
	 */
	public void setRegisteredCamps(ArrayList<Camp> registeredCamps) {
		this.registeredCamps = registeredCamps;
	}

	public boolean getCampCommittee() {
		return this.campCommittee;
	}

	/**
	 * 
	 * @param campCommittee
	 */
	public void setCampCommittee(boolean campCommittee) {
		this.campCommittee = campCommittee;
	}

	public ArrayList<Camp> getNotRegisterable() {
		return this.notRegisterable;
	}

	/**
	 * 
	 * @param notRegisterable
	 */
	public void setNotRegisterable(ArrayList<Camp> notRegisterable) {
		this.notRegisterable = notRegisterable;
	}

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	/**
	 * 
	 * @param securityQuestion
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAns() {
		return this.securityAns;
	}

	/**
	 * 
	 * @param securityAns
	 */
	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public ArrayList<Enquiry> getEnquirySubmitted() {
		return this.enquirySubmitted;
	}

	/**
	 * 
	 * @param enquirySubmitted
	 */
	public void setEnquirySubmitted(Enquiry enquirySubmitted) {
		// TODO - implement Student.setEnquirySubmitted
		throw new UnsupportedOperationException();
	}

}