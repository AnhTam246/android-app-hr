package com.example.hr.model;

import java.util.Date;

public class Transfer {
    private Integer staff_id;
    private String staff_transfer;
    private String new_department_name3;
    private String new_department_name;
    private boolean old_manager_approved;
    private boolean new_manager_approved;
    private boolean manager_approved;

    public Transfer(Integer staff_id, String staff_transfer, String new_department_name3, String new_department_name,  boolean old_manager_approved, boolean new_manager_approved, boolean manager_approved) {
        this.staff_id = staff_id;
        this.new_department_name3 = new_department_name3;
        this.new_department_name = new_department_name;
        this.staff_transfer= staff_transfer;
        this.old_manager_approved = old_manager_approved;
        this.new_manager_approved = new_manager_approved;
        this.manager_approved = manager_approved;


    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public String getNew_department_name3() {
        return new_department_name3;
    }

    public void setNew_department_name3(String new_department_name3) {
        this.new_department_name3 = new_department_name3;
    }

    public String getNew_department_name() {
        return new_department_name;
    }

    public void setNew_department_name(String new_department_name) {
        this.new_department_name = new_department_name;
    }

    public String getStaff_transfer() {
        return staff_transfer;
    }

    public void setStaff_transfer(String staff_transfer) {
        this.staff_transfer = staff_transfer;
    }

    public boolean isOld_manager_approved() {
        return old_manager_approved;
    }

    public void setOld_manager_approved(boolean old_manager_approved) {
        this.old_manager_approved = old_manager_approved;
    }

    public boolean isNew_manager_approved() {
        return new_manager_approved;
    }

    public void setNew_manager_approved(boolean new_manager_approved) {
        this.new_manager_approved = new_manager_approved;
    }

    public boolean isManager_approved() {
        return manager_approved;
    }

    public void setManager_approved(boolean manager_approved) {
        this.manager_approved = manager_approved;
    }


}