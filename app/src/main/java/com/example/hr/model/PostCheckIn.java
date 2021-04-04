package com.example.hr.model;

import java.util.Date;

public class PostCheckIn {
    private int staff_id;
    private String y_m;

    public PostCheckIn(int staff_id, String y_m) {
        this.staff_id = staff_id;
        this.y_m = y_m;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getY_m() {
        return y_m;
    }

    public void setY_m(String y_m) {
        this.y_m = y_m;
    }
}
