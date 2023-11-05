package DisplayOptions;
import Camp.Camp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SortByName_Default implements DisplayOption{

	public void Sorting(ArrayList<Camp> campList){
		Collections.sort(campList, campNameComparator);

		//Needs to find out how to save potentially into csv format

		for(Camp camp : campList){
			System.out.println(camp);
			//Needs to find out how to print camp information, potentially direct from csv
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