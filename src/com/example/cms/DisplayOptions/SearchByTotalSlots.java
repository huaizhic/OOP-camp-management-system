package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;

public class SearchByTotalSlots extends DisplayBySearch<Integer, ArrayList<Camp>>{
    private ArrayList<Camp> campAfterSearch = new ArrayList<>();
    public ArrayList<Camp> Searching(Integer totalSlots, ArrayList<Camp> campList){
        campAfterSearch.clear();
        if (campList == null) {
            System.out.println("No camp available yet");
            return null;
        }else {
        for(Camp camp:campList) {
            if (camp.getTotalSlots() >= totalSlots) {
                campAfterSearch.add(camp);
            }
        }
        }
        return campAfterSearch;
    }
}
