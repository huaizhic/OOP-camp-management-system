package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;
import com.example.cms.UserGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SortByUserGroup extends DisplayOption {

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

	public void SortSearchUserGroup(UserGroup userGroup, ArrayList<Camp> campList){
		Collections.sort(campList, campUserGroupComparator.thenComparing(SortByName_Default.campNameComparator));
		for(Camp camp:campList){
			for(int i = 0; i < camp.getUserGroup().size(); i++)
				if(camp.getUserGroup().get(i).compareTo(userGroup) == 0){
					System.out.print(camp);
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