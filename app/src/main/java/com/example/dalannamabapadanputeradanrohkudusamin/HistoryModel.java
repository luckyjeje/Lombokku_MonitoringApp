package com.example.dalannamabapadanputeradanrohkudusamin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class HistoryModel {
    public String time, value;

    public HistoryModel() {

    }

    public HistoryModel(String time, String value) {
        this.time = time;
        this.value = value;
    }
}
