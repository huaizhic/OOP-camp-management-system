package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SortByName_Default extends DisplayOption{

	public void Sorting(ArrayList<Camp> campList){
		Collections.sort(campList, campNameComparator);

		for(Camp camp : campList){
			System.out.println(camp);
			//Needs to find out how to print camp information, potentially direct from csv
		}
	}

	public void SortSearchName(String name, ArrayList<Camp> campList){
		Collections.sort(campList, campNameComparator);
		for(Camp camp:campList){
			if(camp.getCampName().contains(name)){
				System.out.print(camp);
			}
		}
	}

	public static Comparator<Camp> campNameComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			String campName1 = c1.getCampName().toUpperCase();
			String campName2 = c2.getCampName().toUpperCase();

			return campName1.compareTo(campName2);
		}
	};

}