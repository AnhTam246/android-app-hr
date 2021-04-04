package com.example.hr.model;

public class StaffLogin {
    private Integer id;
    private String code;
    private String firstname;
    private String lastname;
    private Integer department;
    private boolean isManager;
    private boolean status;

    public StaffLogin(Integer id, String code, String firstname, String lastname, Integer department, boolean isManager, boolean status) {
        this.id = id;
        this.code = code;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.isManager = isManager;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
