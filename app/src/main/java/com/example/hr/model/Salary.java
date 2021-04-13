package com.example.hr.model;

import java.util.Date;

public class Salary {
    private Date fromDate;
    private Date toDate;
    private int standardDays = 0;

    public Salary() {
    }

    public Salary(Date fromDate, Date toDate, int standardDays) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.standardDays = standardDays;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getStandardDays() {
        return standardDays;
    }

    public void setStandardDays(int standardDays) {
        this.standardDays = standardDays;
    }
}
