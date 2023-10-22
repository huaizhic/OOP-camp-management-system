package camp_Application_Management_System;

public class User {
    private String userID;
    private String password;
    private String faculty;

    public User(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public void changePassword(String currentPassword, String newPassword) {
        // Check if the current password matches the stored password
        if (currentPassword.equals(password)) {
            // Update the password with the new one
            password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Current password is incorrect. Password change failed.");
        }
    }
}
