package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SortByUserGroup implements DisplayOption {

	public void Sorting(ArrayList<Camp> campList) {

		if(campList == null){
			System.out.println("No camp available yet");
		}else {
			Collections.sort(campList, campUserGroupComparator.thenComparing(SortByName_Default.campNameComparator));

			//Needs to find out how to sav2
			// e potentially into csv format

			for (Camp camp : campList) {
				System.out.println(camp);
				//Needs to find out how to print camp information, potentially direct from csv
			}
		}
	}
	public static Comparator<Camp> campUserGroupComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			int c1UserGroup = c1.getUserGroup().size();
			int c2UserGroup = c2.getUserGroup().size();
			return -1* Integer.compare(c1UserGroup, c2UserGroup);
		}
	};
}