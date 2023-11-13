package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class SortByLocation implements DisplayOption {
	public void Sorting(ArrayList<Camp> campList) {
		if(campList == null){
			System.out.println("No camp available yet");
		}else {
			Collections.sort(campList, campLocationComparator.thenComparing(SortByName_Default.campNameComparator));

			//Needs to find out how to sav2
			// e potentially into csv format

			for (Camp camp : campList) {
				System.out.println(camp);
				//Needs to find out how to print camp information, potentially direct from csv
			}
		}
	}

	public void SortSearchName(String location, ArrayList<Camp> campList){
		Collections.sort(campList, campLocationComparator.thenComparing(SortByName_Default.campNameComparator));
		for(Camp camp:campList){
			if(camp.getLocation().contains(location)){
				System.out.print(camp);
			}
		}
	}

	public static Comparator<Camp> campLocationComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			String campLocation1 = c1.getLocation().toUpperCase();
			String campLocation2 = c2.getLocation().toUpperCase();

			return campLocation1.compareTo(campLocation2);
		}
	};

}