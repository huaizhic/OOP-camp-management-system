package camp_Application_Management_System;

public class Enquiry {
    private String subject;
    private String message;
    private String reply; // New attribute to store the reply

    public Enquiry(Student student, Camp camp, String subject, String message) {
        this.subject = subject;
        this.message = message;
        this.reply = null; // Initialize reply as null
        // You can associate the student and camp here
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    // Other methods and constructors specific to Enquiry
}


