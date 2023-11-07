package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByTotalSlots implements DisplayOption{

	public void Sorting(ArrayList<Camp> campList) {
		if(campList == null){
			System.out.println("No camp available yet");
		}else {
			Collections.sort(campList, campSlotsComparator.thenComparing(SortByName_Default.campNameComparator));

			//Needs to find out how to sav2
			// e potentially into csv format

			for (Camp camp : campList) {
				System.out.println(camp);
				//Needs to find out how to print camp information, potentially direct from csv
			}
		}
	}

	public void SortSearchRegCloseDate(int totalSlots, ArrayList<Camp> campList){
		Collections.sort(campList, campSlotsComparator.thenComparing(SortByName_Default.campNameComparator));
		for(Camp camp:campList){
			if(camp.getTotalSlots() >= totalSlots){
				System.out.print(camp);
			}
		}
	}

	public static Comparator<Camp> campSlotsComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			int c1SlotsLeft = c1.getRemainingSlots();
			int c2SlotsLeft = c2.getRemainingSlots();
			return -1* Integer.compare(c1SlotsLeft, c2SlotsLeft);
		}
	};
}