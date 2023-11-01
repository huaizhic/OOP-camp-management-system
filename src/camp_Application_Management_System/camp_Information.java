package camp_Application_Management_System;

import java.util.Date;
//test 
public class camp_Information {
    private String campName;
    private Date campStartDate;
    private Date registrationClosingDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int committeeSlots;
    private String description;

    public camp_Information(String campName, Date campStartDate, Date registrationClosingDate, String userGroup,
                            String location, int totalSlots, int committeeSlots, String description, Staff staff) {
        this.campName = campName;
        this.campStartDate = campStartDate;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.committeeSlots = committeeSlots;
        this.description = description;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public Date getCampStartDate() {
        return campStartDate;
    }

    public void setCampStartDate(Date campStartDate) {
        this.campStartDate = campStartDate;
    }

    public Date getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(Date registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getCommitteeSlots() {
        return committeeSlots;
    }

    public void setCommitteeSlots(int committeeSlots) {
        this.committeeSlots = committeeSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Additional methods for camp information if needed
}

