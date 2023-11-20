package com.example.cms.Camp;

import java.util.ArrayList;
import java.util.HashMap;

public class campData {
	private static ArrayList<Camp> campList;
	private static HashMap<String, Camp> campHashMap= new HashMap<String, Camp>();

	public static ArrayList<Camp> getCampList() {
		return campList;
	}
	public static void setCampList(Camp camp) {campList.add(camp);}

	public static void addCampToMap(String name, Camp camp){
		campHashMap.put(name, camp);
	}

	public static HashMap<String, Camp> getCampHashMap() {
		return campHashMap;
	}
}