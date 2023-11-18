package com.example.cms.Suggestions;

import com.example.cms.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Suggestion {
    private String suggestion_Subject;
    private String submitter;
    private String content;
    private String dateSubmitted;
    private Status status;
    private boolean processed = false;

    // Use suggestion_Subject as the key for the HashMap
    private static HashMap<String, Suggestion> suggestionHashMap = new HashMap<>();

    public Suggestion(String suggestion_Subject, String submitter, String content, String dateSubmitted) {
        this.suggestion_Subject = suggestion_Subject;
        this.submitter = submitter;
        this.content = content;
        this.dateSubmitted = dateSubmitted;
        this.status = Status.Pending;
        // Add the suggestion to the HashMap using suggestion_Subject as the key
        suggestionHashMap.put(suggestion_Subject, this);
    }

    // Getter and setter for suggestion_Subject
    public String getSuggestion_Subject() {
        return suggestion_Subject;
    }

    public void setSuggestion_Subject(String suggestion_Subject) {
        this.suggestion_Subject = suggestion_Subject;
    }

    // Getter and setter for submitter
    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
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
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
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
    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    // Getter for suggestionHashMap
    public static HashMap<String, Suggestion> getSuggestionHashMap() {
        return suggestionHashMap;
    }

  }
