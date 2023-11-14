package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;
import com.example.cms.Camp.campData;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayApp {

    // the method returns null either there is no camp or the user exits, therefore the main should go back.
    public static ArrayList<Camp> viewAllCamp() {
        if (campData.getCampList().isEmpty()) {
            System.out.println("No camp is available to view");
            return null;
        } else {
            Scanner input = new Scanner(System.in);
            int choice;
            do{System.out.println("Please choose how to view camps:");
            System.out.println("1. Default: alphabetical order of camp name");
            System.out.println("2. Search for keywords");
            System.out.println("3. Sort by camp features");
            System.out.println("4. Exiting...");
            choice = input.nextInt();
            switch (choice) {
                case (1):
                    DisplayBySort aOb = new SortByName_Default();
                    return aOb.Sorting(campData.getCampList());
                case (2):
                    ArrayList<Camp> afterSearchCamp;
                    do {
                        afterSearchCamp = SearchApp.startSearch(campData.getCampList());
                        if (afterSearchCamp == null) {
                            System.out.println("Please choose a correct option");
                        }
                    } while (afterSearchCamp == null);
                    return afterSearchCamp;
                case (3):
                    ArrayList<Camp> afterSortCamp;
                    do {
                        afterSortCamp = SortApp.startSorting(campData.getCampList());
                        if (afterSortCamp == null) {
                            System.out.println("Please choose a correct option");
                        }
                    } while (afterSortCamp == null);
                    return afterSortCamp;
                case (4):
                    System.out.println("Action terminated by the user");
                    System.out.println("Exiting...");
                    return null;
                default:
                    System.out.println("Please insert a valid choice");
                    return null;
            }
            }while(choice == 1 || choice == 2 || choice == 3 || choice == 4);
        }
    }
}
