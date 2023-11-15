package com.example.cms.Staff;

import com.example.cms.Camp.Camp;
import com.example.cms.Faculty;

import java.util.ArrayList;

public class StaffMember extends Staff {

	private String staffID;
	private String name;
	private Faculty userGroup;
	private ArrayList<Camp> campsCreated;
	private String securityQuestion;
	private String securityAns;

	public StaffMember(String staffID, String name, Faculty userGroup, String securityQuestion, String securityAns){
		this.staffID = staffID;
		this.name = name;
		this.userGroup = userGroup;
		campsCreated = new ArrayList<>();
		this.securityQuestion = securityQuestion;
		this.securityAns = securityAns;
	}

	public String getStaffID() {
		return this.staffID;
	}

	public String getName() {
		return this.name;
	}

	public Faculty getUserGroup() {
		return this.userGroup;
	}

	/**
	 * 
	 * @param userGroup
	 */
	public void setUserGroup(Faculty userGroup) {
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