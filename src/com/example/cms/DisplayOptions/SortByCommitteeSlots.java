package com.example.cms.DisplayOptions;
import com.example.cms.Camp.Camp;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
public class SortByCommitteeSlots extends DisplayOption {

	public void Sorting (ArrayList<Camp> campList) {
		if(campList == null){
			System.out.println("No camp available yet");
		}
		else{
			Collections.sort(campList, campCommitteeSlotsComparator.thenComparing(SortByName_Default.campNameComparator));

			//Needs to find out how to sav2
			// e potentially into csv format

			for(Camp camp : campList){
				System.out.println(camp);
				//Needs to find out how to print camp information, potentially direct from csv
			}
		}
	}

	public void SortSearchCommitteeSlots(int committeeSlot, ArrayList<Camp> campList){
		Collections.sort(campList, campCommitteeSlotsComparator.thenComparing(SortByName_Default.campNameComparator));
		for(Camp camp:campList){
			if(camp.getCommitteeSlots() >= committeeSlot){
				System.out.print(camp);
			}
		}
	}

	public static Comparator<Camp> campCommitteeSlotsComparator = new Comparator<>(){
		public int compare(Camp c1, Camp c2){
			int campCommitteeSlot1 = c1.getCommitteeSlots();
			int campCommitteeSLot2 = c2.getCommitteeSlots();

			return -1*Integer.compare(campCommitteeSLot2, campCommitteeSlot1);
		}
	};

}