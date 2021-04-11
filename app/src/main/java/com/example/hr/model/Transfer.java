package com.example.hr.model;

import java.util.Date;

public class Transfer {
//    private Integer id;
    private Integer staff_id;
    private Integer old_department;
    private Integer new_department;
//    private boolean old_manager_approved;
//    private boolean newManagerApproved;
//    private boolean managerApproved;
//    private Integer hr_approved;
//    private Double new_salary;
//    private Date created_at;
//    private Integer created_by;
//    private String note;
//    private String note_manager;
//    private boolean del;

//    public Transfer(Integer id, Integer staff_id, Integer old_department, Integer new_department,
//                    boolean old_manager_approved, boolean newManagerApproved, boolean managerApproved,
//                    Integer hr_approved, Double new_salary, Date created_at, Integer created_by, String note,
//                    String note_manager, boolean del) {
//        this.id = id;
//        this.staff_id = staff_id;
//        this.old_department = old_department;
//        this.new_department = new_department;
//        this.old_manager_approved = old_manager_approved;
//        this.newManagerApproved = newManagerApproved;
//        this.managerApproved = managerApproved;
//        this.hr_approved = hr_approved;
//        this.new_salary = new_salary;
//        this.created_at = created_at;
//        this.created_by = created_by;
//        this.note = note;
//        this.note_manager = note_manager;
//        this.del = del;
//    }


    public Transfer(Integer staff_id, Integer old_department, Integer new_department) {
        this.staff_id = staff_id;
        this.old_department = old_department;
        this.new_department = new_department;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public Integer getOld_department() {
        return old_department;
    }

    public void setOld_department(Integer old_department) {
        this.old_department = old_department;
    }

    public Integer getNew_department() {
        return new_department;
    }

    public void setNew_department(Integer new_department) {
        this.new_department = new_department;
    }

//    public boolean isOld_manager_approved() {
//        return old_manager_approved;
//    }
//
//    public void setOld_manager_approved(boolean old_manager_approved) {
//        this.old_manager_approved = old_manager_approved;
//    }
//
//    public boolean isNewManagerApproved() {
//        return newManagerApproved;
//    }
//
//    public void setNewManagerApproved(boolean newManagerApproved) {
//        this.newManagerApproved = newManagerApproved;
//    }
//
//    public boolean isManagerApproved() {
//        return managerApproved;
//    }
//
//    public void setManagerApproved(boolean managerApproved) {
//        this.managerApproved = managerApproved;
//    }
//
//    public Integer getHr_approved() {
//        return hr_approved;
//    }
//
//    public void setHr_approved(Integer hr_approved) {
//        this.hr_approved = hr_approved;
//    }
//
//    public Double getNew_salary() {
//        return new_salary;
//    }
//
//    public void setNew_salary(Double new_salary) {
//        this.new_salary = new_salary;
//    }
//
//    public Date getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(Date created_at) {
//        this.created_at = created_at;
//    }
//
//    public Integer getCreated_by() {
//        return created_by;
//    }
//
//    public void setCreated_by(Integer created_by) {
//        this.created_by = created_by;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public String getNote_manager() {
//        return note_manager;
//    }
//
//    public void setNote_manager(String note_manager) {
//        this.note_manager = note_manager;
//    }
//
//    public boolean isDel() {
//        return del;
//    }
//
//    public void setDel(boolean del) {
//        this.del = del;
//    }
}