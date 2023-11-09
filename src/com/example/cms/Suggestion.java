package com.example.cms;

import com.example.cms.Student.committeeMember;
public class Suggestion {

	private String content;
	private committeeMember submitter;
	private Status status;
	private boolean processed;

	// constructor for suggestion
	public Suggestion(committeeMember submitter, String content){
		this.content = content;
		this.submitter = submitter;
		this.status = null;
		this.processed = false;
	}

	public String getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public committeeMember getSubmitter() {
		return this.submitter;
	}

	/**
	 * 
	 * @param submitter
	 */
	public void setSubmitter(committeeMember submitter) {
		this.submitter = submitter;
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean getProcessed() {
		return this.processed;
	}

	/**
	 * 
	 * @param processed
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

}