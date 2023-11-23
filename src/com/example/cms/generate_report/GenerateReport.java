package com.example.cms.generate_report;

import com.example.cms.Camp.Camp;

import com.example.cms.Format;

public interface GenerateReport {
    void generateAttendeeList(Camp camp, Format format);
    void generateCommitteeList(Camp camp, Format format);

}

