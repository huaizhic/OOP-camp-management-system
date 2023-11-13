package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;

public class SearchByName extends DisplayBySearch<String, ArrayList<Camp>> {
    private ArrayList<Camp> campAfterSearch = new ArrayList<>();

    public ArrayList<Camp> Searching(String name, ArrayList<Camp> campList) {
        campAfterSearch.clear();
        if (campList == null) {
            System.out.println("No camp available yet");
            return null;
        }else {
        for (Camp camp : campList) {
            if (camp.getCampName().contains(name)) {
                campAfterSearch.add(camp);
            }
        }
        }
        return campAfterSearch;
    }
}
