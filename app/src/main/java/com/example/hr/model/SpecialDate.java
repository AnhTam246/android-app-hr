package com.example.hr.model;

public class SpecialDate {
    String day_special_from;
    String day_special_to;
    String note;

    public SpecialDate(String day_special_from, String day_special_to, String note) {
        this.day_special_from = day_special_from;
        this.day_special_to = day_special_to;
        this.note = note;
    }

    public String getDay_special_from() {
        return day_special_from;
    }

    public void setDay_special_from(String day_special_from) {
        this.day_special_from = day_special_from;
    }

    public String getDay_special_to() {
        return day_special_to;
    }

    public void setDay_special_to(String day_special_to) {
        this.day_special_to = day_special_to;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
