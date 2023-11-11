package com.example.cms.DisplayOptions;

import com.example.cms.Camp.Camp;

import java.util.ArrayList;

public abstract class DisplayBySearch <T, U>{

    public abstract ArrayList<Camp> Searching(T campList, U variableParameter);
}
