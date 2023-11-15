package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;
import com.example.cms.Faculty;

import java.util.ArrayList;

public class SearchByUserGroup extends DisplayBySearch<Faculty, ArrayList<Camp>>{
    private ArrayList<Camp> campAfterSearch = new ArrayList<>();
    public ArrayList<Camp> Searching(Faculty userGroup, ArrayList<Camp> campList){
        campAfterSearch.clear();
        if (campList == null) {
            System.out.println("No camp available yet");
            return null;
        }else {
        for(Camp camp:campList) {
            for (int i = 0; i < camp.getUserGroup().size(); i++)
                if (camp.getUserGroup().get(i).compareTo(userGroup) == 0) {
                    campAfterSearch.add(camp);
                    break;
                }
        }
        }
        return campAfterSearch;
    }
}
