package com.example.cms;

import com.example.cms.Student.Committee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Suggestion {
	private String subject;
	private String content;
	private LocalDate dateSubmitted;
	private Committee submitter;
	private Status status;
	private boolean processed = false;
	private static HashMap<String, Suggestion> suggestionHashMap = new HashMap<>();
	private static ArrayList<Suggestion> suggestionArrayList = new ArrayList<>();

	public Suggestion(String subject, String content, LocalDate dateSubmitted, Committee submitter){
		this.subject = subject;
		this.content = content;
		this.dateSubmitted = dateSubmitted;
		this.submitter = submitter;
		this.status = Status.Pending;
	}

	public static void printSuggestionInfo(Suggestion suggestion){
		System.out.println("The subject: " + suggestion.getSubject());
		System.out.println("The submitter: " + suggestion.getSubmitter());
		System.out.println("The date submitted: " + suggestion.getDateSubmitted());
		System.out.println("The content: "+ suggestion.getContent());
		System.out.println("The status: " + suggestion.getStatus());
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(LocalDate dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Committee getSubmitter() {
		return this.submitter;
	}

	public void setSubmitter(Committee submitter) {
		this.submitter = submitter;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean getProcessed() {
		return this.processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public static HashMap<String, Suggestion> getSuggestionHashMap() {
		return suggestionHashMap;
	}

	public static void addSuggestionToMap(String subject, Suggestion suggestion){
		suggestionHashMap.put(subject, suggestion);
	}

	public static ArrayList<Suggestion> getSuggestionArrayList() {
		return suggestionArrayList;
	}

	public static void setSuggestionArrayList(Suggestion suggestion) {
		suggestionArrayList.add(suggestion);
	}

}
