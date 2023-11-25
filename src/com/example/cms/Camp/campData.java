package com.example.cms.Camp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents entity class campData to hold and process data of all camps.
 */
public class campData {
	private static ArrayList<Camp> campList;
	private static HashMap<String, Camp> campHashMap= new HashMap<String, Camp>();

	/**
	 * Gets ArrayList of all Camp objects.
	 * @return list of all camps.
	 */
	public static ArrayList<Camp> getCampList() {
		return campList;
	}

	/**
	 * Adds newly created camp object to existing ArrayList of camp objects.
	 * @param camp
	 */
	public static void setCampList(Camp camp) {campList.add(camp);}

	/**
	 * Adds a key value pair (camp name, camp object) of a newly created camp into the existing hashmap.
	 * @param name
	 * @param camp
	 */
	public static void addCampToMap(String name, Camp camp){
		campHashMap.put(name, camp);
	}

	/**
	 * Gets hashmap of all camp names (key) tied to their corresponding camp objects (value)
	 * @return hashmap of all camp names (key) tied to their corresponding camp objects (value)
	 */
	public static HashMap<String, Camp> getCampHashMap() {
		return campHashMap;
	}
}