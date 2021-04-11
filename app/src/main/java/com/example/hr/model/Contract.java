package com.example.hr.model;

import java.util.Date;
import java.util.HashMap;

public class Contract {

    private Integer id;
    private int staffId;
    private Date startDate;
    private Date endDate;
    private Date stopDate;
    private double baseSalary;
    private Date createAt;

    public Contract() {
    }

    public Contract(Integer id, int staffId, Date startDate, Date endDate, Date stopDate, double baseSalary, Date createAt) {
        this.id = id;
        this.staffId = staffId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stopDate = stopDate;
        this.baseSalary = baseSalary;
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
