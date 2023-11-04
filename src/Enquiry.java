import Package.*;

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

	public void getAttribute() {
		// TODO - implement Enquiry.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute) {
		// TODO - implement Enquiry.setAttribute
		throw new UnsupportedOperationException();
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

	public Camp getCamp() {
		// TODO - implement Enquiry.getCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param camp
	 */
	public void setCamp(Camp camp) {
		// TODO - implement Enquiry.setCamp
		throw new UnsupportedOperationException();
	}

}