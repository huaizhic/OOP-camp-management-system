package com.example.cms.Camp;

import java.util.ArrayList;
import java.util.HashMap;

public class campData {
	private ArrayList<Camp> campList;

	public ArrayList<Camp> getCampList() {
		return this.campList;
	}

	public void setCampList(Camp camp) {campList.add(camp);}

	private static HashMap<String, Camp> campHashMap= new HashMap<String, Camp>();

	public static void addCampToMap(String name, Camp camp){
		campHashMap.put(name, camp);
	}

	}