package com.example.cms.Suggestions;

import com.example.cms.Status;
import com.example.cms.Student.Committee;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;

public class Suggestion {
    private String suggestion_Subject;
    private Committee submitter;
    private String content;
    private LocalDate dateSubmitted;
    private Status status;
    private boolean processed = false;

    // Use suggestion_Subject as the key for the HashMap
    private static HashMap<String, Suggestion> suggestionHashMap = new HashMap<>();

    public Suggestion(String suggestion_Subject, Committee committee, String content, LocalDate dateSubmitted) {
        this.suggestion_Subject = suggestion_Subject;
        this.submitter = submitter;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.status = Status.Pending;
        // Add the suggestion to the HashMap using suggestion_Subject as the key
        suggestionHashMap.put(suggestion_Subject, this);
    }

    public static void printSuggestionInfo(Suggestion suggestion){
        System.out.println("The subject: " + suggestion.getSuggestion_Subject());
        System.out.println("The submitter: " + suggestion.getSubmitter());
        System.out.println("The date submitted: " + suggestion.getDateSubmitted());
        System.out.println("The content: "+ suggestion.getContent());
        System.out.println("The status: " + suggestion.getStatus());
    }

    // Getter and setter for suggestion_Subject

    public String getSuggestion_Subject() {
        return suggestion_Subject;
    }

    public void setSuggestion_Subject(String suggestion_Subject) {
        this.suggestion_Subject = suggestion_Subject;
    }

    // Getter and setter for submitter
    public Committee getSubmitter() {
        return this.submitter;
    }

    public void setSubmitter(Committee submitter) {
        this.submitter = submitter;
    }

    // Getter and setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and setter for dateSubmitted
    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    // Getter and setter for status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter and setter for processed
    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    // Getter for suggestionHashMap
    public static HashMap<String, Suggestion> getSuggestionHashMap() {
        return suggestionHashMap;
    }
    
    public static Suggestion getSuggestionBySubject(String subject) {
        return suggestionHashMap.get(subject);
    }

    // save newly submitted suggestion to CSV containing all existing suggestions
    public static void saveToCSV(Suggestion newSuggestion){
        String file = "src\\Suggestions.csv";
        try {
            PrintWriter out = new PrintWriter(file);
            // Since our CSV contains commas naturally, use regex instead of commas to separate the cells
            out.println(newSuggestion.getSuggestion_Subject()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newSuggestion.getContent()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newSuggestion.getSubmitter()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newSuggestion.getProcessed()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newSuggestion.getDateSubmitted() +",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"
                    +newSuggestion.getStatus()+",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  }
