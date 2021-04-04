package com.example.hr.model;

import java.sql.Time;
import java.util.Date;

public class CheckInOut {
    public Date check_in_day;
    public Date check_in;
    public Date check_out;

    public CheckInOut(Date check_in_day, Date check_in, Date check_out) {
        this.check_in_day = check_in_day;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public Date getCheck_in_day() {
        return check_in_day;
    }

    public void setCheck_in_day(Date check_in_day) {
        this.check_in_day = check_in_day;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }
}
