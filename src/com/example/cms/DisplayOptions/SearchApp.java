package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import com.example.cms.Faculty;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchApp {
    public static ArrayList<Camp> startSearch(ArrayList<Camp> campArrayList){
        Scanner input = new Scanner(System.in);
        System.out.println("=== what camp feature would you like to search for?");
        System.out.println("1. Camp name");
        System.out.println("2. Camp location");
        System.out.println("3. Camp start date");
        System.out.println("4. Camp registration close date");
        System.out.println("5. Camp remaining committee slots");
        System.out.println("6. Camp remaining attendee slots");
        System.out.println("7. Camp eligible user group");
        int option = input.nextInt();
        switch(option) {
            case (1):
                System.out.println("Insert keywords for camp name");
                String nameSearch = input.nextLine();
                DisplayBySearch<String, ArrayList<Camp>> searcherObj = new SearchByName();
                return searcherObj.Searching(nameSearch, campArrayList);
            case (2):
                System.out.println("Insert keywords for camp location");
                String locationSearchKey = input.nextLine();
                DisplayBySearch<String, ArrayList<Camp>> locationSearcher = new SearchByLocation();
                return locationSearcher.Searching(locationSearchKey, campArrayList);

            case (3):
                System.out.println("Insert camp date to search in the format of yyyy-mm-dd");
                LocalDate startDateToSearch = null;
                do {
                    String dateString = input.nextLine();
                    try {
                        startDateToSearch = LocalDate.parse(dateString);
                        System.out.println("Starting date to search: " + startDateToSearch);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please insert the data in the correct format: yyyy-mm-dd");
                    }
                } while (startDateToSearch == null);
                DisplayBySearch<LocalDate, ArrayList<Camp>> startDateSearcher = new SearchByCampDate();
                return startDateSearcher.Searching(startDateToSearch, campArrayList);

            case (4):
                System.out.println("Insert camp registration close date to search in the format of yyyy-mm-dd");
                LocalDate regCloseDateToSearch = null;
                do {
                    String dateString = input.nextLine();
                    try {
                        regCloseDateToSearch = LocalDate.parse(dateString);
                        System.out.println("Reg. close date to search: " + regCloseDateToSearch);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please insert the data in the correct format: yyyy-mm-dd");
                    }
                } while (regCloseDateToSearch == null);
                DisplayBySearch<LocalDate, ArrayList<Camp>> regCloseDateSearcher = new SearchByRegCloseDate();
                return regCloseDateSearcher.Searching(regCloseDateToSearch, campArrayList);

            case(5):
                System.out.println("Insert remaining committee slots to search");
                int committeeSlotToSearch = input.nextInt();
                DisplayBySearch<Integer, ArrayList<Camp>> committeeSlotSearcher = new SearchByCommitteeSlots();
                return committeeSlotSearcher.Searching(committeeSlotToSearch, campArrayList);

            case(6):
                System.out.println("Insert remaining attendee slots to search");
                int attendeeSlotToSearch = input.nextInt();
                DisplayBySearch<Integer, ArrayList<Camp>> attendeeSlotSearcher = new SearchByTotalSlots();
                return attendeeSlotSearcher.Searching(attendeeSlotToSearch, campArrayList);

            case(7):
                System.out.println("Insert an user group to search");
                Faculty userGroupToSearch = null;
                do {
                    String userGroupString = input.nextLine();
                    try{
                        userGroupToSearch = Faculty.valueOf(userGroupString);
                    }catch(IllegalArgumentException e){
                        System.out.println("The user group does not exist, please re-enter an user group to search");
                    }
                }while(userGroupToSearch == null);
                DisplayBySearch<Faculty, ArrayList<Camp>> userGroupSearcher = new SearchByUserGroup();
                return userGroupSearcher.Searching(userGroupToSearch, campArrayList);
            default:
                return null;
        }
    }
}
