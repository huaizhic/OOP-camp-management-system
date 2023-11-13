package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByCampDate implements DisplayOption{

    public void Sorting(ArrayList<Camp> campList) {
        if (campList == null) {
            System.out.println("No camp available yet");
        } else {
            Collections.sort(campList, campDateComparator.thenComparing(SortByName_Default.campNameComparator));

            //Needs to find out how to sav2
            // e potentially into csv format

            for (Camp camp : campList) {
                System.out.println(camp);
                //Needs to find out how to print camp information, potentially direct from csv
            }
        }
    }

    public void SortSearchCampDate(LocalDate campDate, ArrayList<Camp> campList){
        if (campList == null) {
            System.out.println("No camp available yet");
        }else {
            Collections.sort(campList, campDateComparator.thenComparing(SortByName_Default.campNameComparator));
            for (Camp camp : campList) {
                for (int i = 0; i < camp.getCampDates().size(); i++) {
                    if (camp.getCampDates().get(i).compareTo(campDate) == 0) {
                        System.out.print(camp);
                        break;
                    }
                }
            }
        }
    }
    public static Comparator<Camp> campDateComparator = new Comparator<>(){
        public int compare(Camp c1, Camp c2){
            LocalDate camp1Date = c1.getCampDates().get(0);
            LocalDate camp2Date = c2.getCampDates().get(0);

            return camp1Date.compareTo(camp2Date);
        }
    };

}