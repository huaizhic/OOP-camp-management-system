package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchByRegCloseDate extends DisplayBySearch<LocalDate, ArrayList<Camp>>{
    private ArrayList<Camp> campAfterSearch = new ArrayList<>();
    public ArrayList<Camp> Searching(LocalDate regCloseDate, ArrayList<Camp> campList){
        campAfterSearch.clear();
        if (campList == null) {
            System.out.println("No camp available yet");
            return null;
        }else {
        for(Camp camp:campList) {
            if (camp.getRegCloseDate().compareTo(regCloseDate) == 0) {
                campAfterSearch.add(camp);
            }
        }
        }
        return campAfterSearch;
    }
}
