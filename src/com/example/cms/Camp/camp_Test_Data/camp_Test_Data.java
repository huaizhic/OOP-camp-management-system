package com.example.cms.Camp.camp_Test_Data;

import com.example.cms.*;
import com.example.cms.Student.Committee_Member;
import com.example.cms.Student.Student_User;
import com.example.cms.Faculty;
import com.example.cms.Camp.Camp;
import com.example.cms.Staff.StaffMember;
import com.example.cms.Enquiries.Enquiry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class camp_Test_Data {
    private String campName;
    private ArrayList<LocalDate> campDates = new ArrayList<>();
    private LocalDate regCloseDate;
    private ArrayList<Faculty> faculty = new ArrayList<>();
    private String location;
    private int totalSlots;
    private int remainingSlots;
    private int committeeSlots;
    private int remainingCommitteeSlots;
    private String description;
    private StaffMember staffInCharge;
    private ArrayList<Student_User> studentsRegistered = new ArrayList<>();
    private ArrayList<Committee_Member> committeeRegistered;
    private boolean visibility;
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    private ArrayList<Suggestion> suggestions = new ArrayList<>();
	private String camp_Test_DataName;
    private static int counter = -1;

    // HashMap to store instances of camp_Test_Data
    private static HashMap<String, camp_Test_Data> campsData = new HashMap<>();

    public camp_Test_Data() {

    }

    public camp_Test_Data(String campName, ArrayList<LocalDate> campDates, LocalDate regCloseDate, ArrayList<Faculty> faculty, String location, int totalSlots, StaffMember staff, boolean visibility) {
        this.campName = campName;
        this.campDates = campDates;
        this.regCloseDate = regCloseDate;
        this.faculty = faculty;
        this.location = location;
        this.totalSlots = totalSlots;
        remainingSlots = totalSlots;
        committeeSlots = 10;
        remainingCommitteeSlots = committeeSlots;
        staffInCharge = staff;
        this.visibility = visibility;
        counter++;

        // Save the instance in the HashMap with campName as the key
        campsData.put(campName, this);
    }

    // Getter for campsData HashMap
    public static HashMap<String, camp_Test_Data> getCampsData() {
        return campsData;
    }

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter){
		camp_Test_Data.counter = counter;
	}
	
	public static void viewRegisteredCamp(String campName) {
        if (campsData.containsKey(campName)) {
            camp_Test_Data camp = campsData.get(campName);
            System.out.println("Camp Name: " + camp.getcamp_Test_DataName());
            System.out.println("Dates: " + camp.getcamp_Test_DataDates().get(0) + " to " + camp.getcamp_Test_DataDates().get(1));
            System.out.println("Reg Close Date: " + camp.getRegCloseDate());
            System.out.println("Eligible Group: " + camp.getFaculty());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Remaining Slots: " + camp.getRemainingSlots());
            System.out.println("Committee Slots: " + camp.getCommitteeSlots());
            System.out.println("Remaining Committee Slots: " + camp.getRemainingCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Staff In Charge: " + camp.getStaffInCharge());
            System.out.println("Visibility: " + camp.getVisibility());

            // Print registered students
            System.out.println("Registered Students:");
            for (Student_User student : camp.getStudentsRegistered()) {
                System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
            }

            // Print committee members
            System.out.println("Committee Members:");
            for (Committee_Member committeeMember : camp.getCommitteeRegistered()) {
                System.out.println("Member ID: " + committeeMember.getUserID() + ", Name: " + committeeMember.getName());
            }

            // Print enquiries
            System.out.println("Enquiries:");
            for (Enquiry enquiry : camp.getEnquiry()) {
                System.out.println("Enquiry ID: " + enquiry.getEnquiryID() + ", Content: " + enquiry.getContent());
            }

            // Print suggestions
            System.out.println("Suggestions:");
            for (Suggestion suggestion : camp.getSuggestion()) {
                System.out.println("Suggestion ID: " + suggestion.getSuggestionID() + ", Content: " + suggestion.getContent());
            }
        } else {
            System.out.println("Camp with name " + campName + " not found.");
        }
    }
	
	public static void viewCamp(String campName) {
	    System.out.println("Available Camps:");
	    if (campsData.containsKey(campName)) {
            camp_Test_Data camp = campsData.get(campName);
            System.out.println("Camp Name: " + camp.getcamp_Test_DataName());
            System.out.println("Dates: " + camp.getcamp_Test_DataDates().get(0) + " to " + camp.getcamp_Test_DataDates().get(1));
            System.out.println("Reg Close Date: " + camp.getRegCloseDate());
            System.out.println("Eligible Group: " + camp.getFaculty());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Remaining Slots: " + camp.getRemainingSlots());
            System.out.println("Committee Slots: " + camp.getCommitteeSlots());
            System.out.println("Remaining Committee Slots: " + camp.getRemainingCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Staff In Charge: " + camp.getStaffInCharge());
            System.out.println("Visibility: " + camp.getVisibility());

            // Print registered students
            System.out.println("Registered Students:");
            for (Student_User student : camp.getStudentsRegistered()) {
                System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getName());
            }

            // Print committee members
            System.out.println("Committee Members:");
            for (Committee_Member committeeMember : camp.getCommitteeRegistered()) {
                System.out.println("Member ID: " + committeeMember.getUserID() + ", Name: " + committeeMember.getName());
            }

            // Print enquiries
            System.out.println("Enquiries:");
            for (Enquiry enquiry : camp.getEnquiry()) {
                System.out.println("Enquiry ID: " + enquiry.getEnquiryID() + ", Content: " + enquiry.getContent());
            }

            // Print suggestions
            System.out.println("Suggestions:");
            for (Suggestion suggestion : camp.getSuggestion()) {
                System.out.println("Suggestion ID: " + suggestion.getSuggestionID() + ", Content: " + suggestion.getContent());
            }
        } else {
            System.out.println("Camp with name " + campName + " not found.");
        }

	}
	
	
	public static camp_Test_Data registerForCamp(ArrayList<Camp> registeredCamps, String studentID) {
	    System.out.println("Available Camps for Registration:");
	    List<camp_Test_Data> availableCamps = findAvailableCamps(registeredCamps, studentID);

	    if (availableCamps.isEmpty()) {
	        System.out.println("No available camps for registration.");
	        return null; // No camp registered, return null
	    } else {
	        for (int i = 0; i < availableCamps.size(); i++) {
	            camp_Test_Data camp = availableCamps.get(i);
	            System.out.println((i + 1) + ". Camp Name: " + camp.getcamp_Test_DataName());
	            System.out.println("   Dates: " + camp.getcamp_Test_DataDates().get(0) + " to " + camp.getcamp_Test_DataDates().get(1));
	            System.out.println("   Reg Close Date: " + camp.getRegCloseDate());
	            System.out.println("   Eligible Group: " + camp.getFaculty());
	            System.out.println("   Location: " + camp.getLocation());
	            System.out.println("   Total Slots: " + camp.getTotalSlots());
	            System.out.println("   Remaining Slots: " + camp.getRemainingSlots());
	            System.out.println("   Committee Slots: " + camp.getCommitteeSlots());
	            System.out.println("   Remaining Committee Slots: " + camp.getRemainingCommitteeSlots());
	            System.out.println("   Description: " + camp.getDescription());
	            System.out.println("   Staff In Charge: " + camp.getStaffInCharge());
	            System.out.println("   Visibility: " + camp.getVisibility());
	            System.out.println();
	        }

	        // Assume there's a method to get user input for camp registration
	        int choice = getUserInputForCampRegistration(availableCamps.size());

	        // Validate the user choice and perform registration
	        if (choice >= 1 && choice <= availableCamps.size()) {
	            // Perform the registration logic, e.g., add the user to the selected camp's registered students
	            camp_Test_Data selectedCamp = availableCamps.get(choice - 1);
	            selectedCamp.getStudentsRegistered().add(Student_User.getStudentById(studentID));
	            System.out.println("Registration successful for " + selectedCamp.getcamp_Test_DataName());

//	            // Update the lists of registered camps for all students
//	            updateRegisteredCampsForStudent(studentID, selectedCamp);

	            // Return the successfully registered camp
	            return selectedCamp;
	        } else {
	            System.out.println("Invalid choice. No registration performed.");
	            return null; // No camp registered, return null
	        }
	    }
	}

    private static List<camp_Test_Data> findAvailableCamps(ArrayList<Camp> registeredCamps, String studentID) {
        List<camp_Test_Data> availableCamps = new ArrayList<>();

        // Assume there's a method to get the currently registered camps for the student
        List<camp_Test_Data> currentRegisteredCamps = getCurrentRegisteredCamps(Student_User.getStudentById(studentID));

        // Iterate through all camps and check for clashes with the date of currently registered camps
        for (camp_Test_Data camp : getCampsData().values()) {
            if (!currentRegisteredCamps.stream().anyMatch(rc -> doDatesOverlap(rc, camp))) {
                // Camp does not clash with any currently registered camps, add to available camps
                availableCamps.add(camp);
            }
        }

        return availableCamps;
    }

    private static boolean doDatesOverlap(camp_Test_Data camp1, camp_Test_Data camp2) {
        // Extract start and end dates for each camp
        LocalDate startDate1 = camp1.getcamp_Test_DataDates().get(0);
        LocalDate endDate1 = camp1.getcamp_Test_DataDates().get(1);

        LocalDate startDate2 = camp2.getcamp_Test_DataDates().get(0);
        LocalDate endDate2 = camp2.getcamp_Test_DataDates().get(1);

        // Check for date overlap
        return !(endDate1.isBefore(startDate2) || startDate1.isAfter(endDate2));
    }


    private static List<camp_Test_Data> getCurrentRegisteredCamps(Student_User student) {
        List<camp_Test_Data> registeredCamps = new ArrayList<>();

        // Assuming student has a list of camp names they are registered for
        List<Camp> registeredCampNames = student.getRegisteredCamps();

        // Iterate through the registered camp names and retrieve corresponding camp objects
        for (Camp campName : registeredCampNames) {
            if (campsData.containsKey(campName)) {
                registeredCamps.add(campsData.get(campName));
            } else {
                // Handle the case where the camp data for a registered camp is not found
                System.out.println("Camp data not found for camp: " + campName);
            }
        }

        return registeredCamps;
    }

    private static int getUserInputForCampRegistration(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        
        // Keep prompting the user until a valid choice is entered
        while (true) {
            try {
                System.out.print("Enter your choice (1-" + maxChoice + "): ");
                userChoice = scanner.nextInt();

                // Validate if the entered choice is within the valid range
                if (userChoice >= 1 && userChoice <= maxChoice) {
                    break; // Exit the loop if the choice is valid
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        
        return userChoice;
    }
	
    public String withdrawFromCamp(String studentID) {
        // Retrieve the student based on the provided studentID
        Student_User student = Student_User.getStudentById(studentID);

        if (student != null) {
            List<Camp> registeredCamps = student.getRegisteredCamps();

            // Display the list of registered camps
            if (registeredCamps.isEmpty()) {
                System.out.println("You are not registered for any camps.");
            } else {
                System.out.println("Your Registered Camps:");
                for (int i = 0; i < registeredCamps.size(); i++) {
                    Camp registeredCamp = registeredCamps.get(i);
                    System.out.println((i + 1) + ". Camp Name: " + registeredCamp.getCampName());
                    System.out.println();
                }

                // Assume there's a method to get user input for camp withdrawal
                int choice = getUserInputForCampWithdrawal(registeredCamps.size());

                // Validate the user choice and perform withdrawal
                if (choice >= 1 && choice <= registeredCamps.size()) {
                    // Perform the withdrawal logic, e.g., remove the user from the selected camp's registered students
                    Camp selectedCamp = registeredCamps.get(choice - 1);
                    selectedCamp.getStudentsRegistered().remove(student);
                    System.out.println("Withdrawal successful from " + selectedCamp.getCampName());
                    return selectedCamp.getCampName(); // Return the withdrawn camp name
                } else {
                    System.out.println("Invalid choice. No withdrawal performed.");
                }
            }
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }

        return null; // Return null if no camp is withdrawn
    }

    private int getUserInputForCampWithdrawal(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        // Keep prompting the user until a valid choice is entered
        while (true) {
            try {
                System.out.print("Enter the index of the camp to withdraw from (1-" + maxChoice + "): ");
                userChoice = scanner.nextInt();

                // Validate if the entered choice is within the valid range
                if (userChoice >= 1 && userChoice <= maxChoice) {
                    break; // Exit the loop if the choice is valid
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return userChoice;
    }




	

	public String getcamp_Test_DataName() {
		return this.getcamp_Test_DataName();
	}

	public void setcamp_Test_DataName(String camp_Test_DataName) {
		this.camp_Test_DataName = camp_Test_DataName;
	}

	public ArrayList<LocalDate> getcamp_Test_DataDates() {
		return this.getcamp_Test_DataDates();
	}

	public void setcamp_Test_DataDates(ArrayList<LocalDate> dates) {
		ArrayList<LocalDate> camp_Test_DataDates = dates;
	}

	public LocalDate getRegCloseDate() {
		return this.regCloseDate;
	}

	public void setRegCloseDate(LocalDate regCloseDate) {
		this.regCloseDate = regCloseDate;
	}

	public ArrayList<Faculty> getFaculty() {
		return this.getFaculty();
	}

	public void setUserGroup(ArrayList<Faculty> faculty) {
		this.faculty = faculty;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalSlots() {
		return this.totalSlots;
	}

	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	public int getRemainingSlots(){return this.remainingSlots;}

	public void setRemainingSlots(int remainingSlots){this.remainingSlots = remainingSlots;}

	public int getCommitteeSlots() {
		return this.committeeSlots;
	}

	public void setCommitteeSlots(int committeeSlots) {
		this.committeeSlots = committeeSlots;
	}

	public int getRemainingCommitteeSlots() {
		return remainingCommitteeSlots;
	}

	public void setRemainingCommitteeSlots(int remainingCommitteeSlots){
		this.remainingCommitteeSlots = remainingCommitteeSlots;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StaffMember getStaffInCharge() {
		return this.staffInCharge;
	}

	public void setStaffInCharge(StaffMember staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	public ArrayList<Student_User> getStudentsRegistered() {
		return this.studentsRegistered;
	}


	public void setStudentsRegistered(ArrayList<Student_User> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

	public ArrayList<Committee_Member> getCommitteeRegistered() {
		return committeeRegistered;
	}

	public void setCommitteeRegistered(Committee_Member committeeMember) {
		this.committeeRegistered.add(committeeMember);
	}

	public boolean getVisibility() {
		return this.visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public ArrayList<Enquiry> getEnquiry() {
		return this.enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry.add(enquiry);
	}

	public ArrayList<Suggestion> getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(ArrayList<Suggestion> suggestion) {
		this.suggestion = suggestion;
	}
}