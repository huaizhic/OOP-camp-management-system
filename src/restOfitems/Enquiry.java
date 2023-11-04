package restOfitems;

import Student.*;

public class Enquiry {

	private String content;
	private Student submitter;
	private String reply;
	private boolean processed;

	// enquiry class constructor
	public Enquiry(String message, Student student ){
		this.content = message;
		this.submitter = student;
		this.reply = null;
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

	public Student getSubmitter() {
		return this.submitter;
	}

	/**
	 * 
	 * @param submitter
	 */
	public void setSubmitter(Student submitter) {
		this.submitter = submitter;
	}

	public String getReply() {
		return this.reply;
	}

	/**
	 * 
	 * @param reply
	 */
	public void setReply(String reply) {
		this.reply = reply;
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