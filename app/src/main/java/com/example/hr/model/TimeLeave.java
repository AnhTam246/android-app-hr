package com.example.hr.model;

import java.util.Date;

public class TimeLeave {
    public String day_time_leave;
    public Integer type;
    public String time;
    public Integer is_approved;

    public TimeLeave(String day_time_leave, Integer type, String time, Integer is_approved) {
        this.day_time_leave = day_time_leave;
        this.type = type;
        this.time = time;
        this.is_approved = is_approved;
    }

    public String getDay_time_leave() {
        return day_time_leave;
    }

    public void setDay_time_leave(String day_time_leave) {
        this.day_time_leave = day_time_leave;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Integer is_approved) {
        this.is_approved = is_approved;
    }
}
