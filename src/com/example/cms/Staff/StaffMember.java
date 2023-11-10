package com.example.cms.Staff;
import com.example.cms.Camp.Camp;
import com.example.cms.UserGroup;
import java.util.ArrayList;

public class StaffMember extends Staff {

	private String staffID;
	private String name;
	private UserGroup userGroup;
	private ArrayList<Camp> campsCreated;
	private String securityQuestion;
	private String securityAns;

	public String getStaffID() {
		return this.staffID;
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

	public ArrayList<Camp> getCampsCreated() {
		return this.campsCreated;
	}

	/**
	 * 
	 * @param campsCreated
	 */
	public void setCampsCreated(Camp campsCreated) {this.campsCreated.add(campsCreated);}


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

}