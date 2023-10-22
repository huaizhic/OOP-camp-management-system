package camp_Application_Management_System;

public class Suggestion {
    private String title;
    private String description;

    public Suggestion(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // You can add other methods specific to Suggestion here.
}

