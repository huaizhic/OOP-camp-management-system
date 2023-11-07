package com.example.cms;
import com.example.cms.Student.committee_Member;

public class Suggestion {

	private String content;
	private committee_Member submitter;
	private Status status;
	private boolean processed;

	public Suggestion(String content, committee_Member submitter, Status status, boolean processed){
		this.content = content;
		this.submitter = submitter;
		this.status = status;
		this.processed = processed;
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

	public committee_Member getSubmitter() {
		return this.submitter;
	}

	/**
	 * 
	 * @param submitter
	 */
	public void setSubmitter(committee_Member submitter) {
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
