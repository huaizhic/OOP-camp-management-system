package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchByCampDate extends DisplayBySearch <LocalDate, ArrayList<Camp>>{
    private ArrayList<Camp> campAfterSearch = new ArrayList<>();
    public ArrayList<Camp> Searching(LocalDate campDate, ArrayList<Camp> campList){
        campAfterSearch.clear();
        if (campList == null) {
            System.out.println("No camp available yet");
            return null;
        }else {
            for (Camp camp : campList) {
                    if (camp.getCampDates().get(0).compareTo(campDate) == 0) {
                        campAfterSearch.add(camp);
                }
            }
            return campAfterSearch;
        }
    }
}
