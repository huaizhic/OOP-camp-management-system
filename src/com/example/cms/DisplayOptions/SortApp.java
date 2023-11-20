package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;
import java.util.Scanner;

public class SortApp {
    public static ArrayList<Camp> startSorting(ArrayList<Camp> campArrayList){
        Scanner input = new Scanner(System.in);
        System.out.println("=== what camp feature would you like to sort for?");
        System.out.println("1. Camp location");
        System.out.println("2. Camp start date");
        System.out.println("3. Camp registration close date");
        System.out.println("4. Camp remaining committee slots");
        System.out.println("5. Camp remaining attendee slots");
        System.out.println("6. Camp eligible user group");
        int option = input.nextInt();
        switch(option){
            case(1):
                System.out.println("Sorting by camp location...");
                DisplayBySort locationSorter = new SortByLocation();
                return locationSorter.Sorting(campArrayList);
            case(2):
                System.out.println("Sorting by camp starting date...");
                DisplayBySort campStartDateSorter = new SortByCampDate();
                return campStartDateSorter.Sorting(campArrayList);
            case(3):
                System.out.println("Sorting by camp registration close date...");
                DisplayBySort regCloseDateSorter = new SortByRegCloseDate();
                return regCloseDateSorter.Sorting(campArrayList);
            case(4):
                System.out.println("Sorting by remaining committee slots...");
                DisplayBySort committeeSlotSorter = new SortByCommitteeSlots();
                return committeeSlotSorter.Sorting(campArrayList);
            case(5):
                System.out.println("Sorting by remaining attendee slots...");
                DisplayBySort attendeeSlotsSorter = new SortByTotalSlots();
                return attendeeSlotsSorter.Sorting(campArrayList);
            case(6):
                System.out.println("Sorting by eligible user group...");
                DisplayBySort userGroupSorter = new SortByUserGroup();
                return userGroupSorter.Sorting(campArrayList);
            default:
                return null;
        }
    }
}
