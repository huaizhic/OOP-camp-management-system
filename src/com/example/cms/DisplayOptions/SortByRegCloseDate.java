package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByRegCloseDate implements DisplayOption{

	public void Sorting(ArrayList<Camp> campList) {
		if (campList == null) {
			System.out.println("No camp available yet");
		} else {
			Collections.sort(campList, campRegDateComparator.thenComparing(SortByName_Default.campNameComparator));

			//Needs to find out how to sav2
			// e potentially into csv format

			for (Camp camp : campList) {
				System.out.println(camp);
				//Needs to find out how to print camp information, potentially direct from csv
			}
		}
	}

	public void SortSearchRegCloseDate(LocalDate regCloseDate, ArrayList<Camp> campList){
		Collections.sort(campList, campRegDateComparator.thenComparing(SortByName_Default.campNameComparator));
		for(Camp camp:campList){
			if(camp.getRegCloseDate().compareTo(regCloseDate) == 0){
				System.out.print(camp);
			}
		}
	}
	public static Comparator<Camp> campRegDateComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			LocalDate camp1Date = c1.getRegCloseDate();
			LocalDate camp2Date = c2.getRegCloseDate();

			return camp1Date.compareTo(camp2Date);
		}
	};

}