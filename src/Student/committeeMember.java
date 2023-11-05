package Student;

import restOfitems.Camp;
import restOfitems.Suggestion;
import restOfitems.UserGroup;

import java.util.ArrayList;

public class committeeMember extends committee {

	private String studentID;
	private String name;
	private UserGroup userGroup;
	private Camp registeredCamps;
	private String securityQuestion;
	private String securityAns;
	private ArrayList<Suggestion> suggestionSubmitted;
	private int point;

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

	public Camp getRegisteredCamps() {
		return this.registeredCamps;
	}

	/**
	 * 
	 * @param registeredCamps
	 */
	public void setRegisteredCamps(Camp registeredCamps) {
		this.registeredCamps = registeredCamps;
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

	public ArrayList<Suggestion> getSuggestionSubmitted() {
		return this.suggestionSubmitted;
	}

	/**
	 * 
	 * @param suggestionSubmitted
	 */
	public void setSuggestionSubmitted(Suggestion newSuggestion) {
		// TODO - implement CommiteeMember.setSuggestionSubmitted
		// throw new UnsupportedOperationException();

		// interpretation: append new suggestion to existing suggestion arraylist
		suggestionSubmitted.add(newSuggestion);
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