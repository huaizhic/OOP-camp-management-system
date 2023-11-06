package com.example.cms;

public class Suggestion {

	private String content;
	private CommitteeMember submitter;
	private Status status;
	private boolean processed;

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

	public CommitteeMember getSubmitter() {
		return this.submitter;
	}

	/**
	 * 
	 * @param submitter
	 */
	public void setSubmitter(CommitteeMember submitter) {
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
