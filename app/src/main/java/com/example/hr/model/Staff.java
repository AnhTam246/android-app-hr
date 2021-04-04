package com.example.hr.model;

import java.util.Date;

public class Staff {
    private Double id;
    private String code;
    private String firstname;
    private String lastname;
    private Double department;
    private boolean isManager;
    private Date joinedAt;
    private Date offDate;
    private String dob;
    private Double gender;
    private Double regional;
    private String phoneNumber;
    private String email;
    private String password;
    private String idNumber;
    private Date identity_issue_date;
    private String photo;
    private String idPhoto;
    private String idPhotoBack;
    private float dayOfLeave;
    private String note;
    private Date createdAt;
    private Double createdBy;
    private Date updatedAt;
    private Double updatedBy;
    private boolean status;

    public Staff(Double id, String code, String firstname, String lastname, Double department, boolean isManager, Date joinedAt, Date offDate, String dob, Double gender, Double regional, String phoneNumber, String email, String password, String idNumber, Date identity_issue_date, String photo, String idPhoto, String idPhotoBack, float dayOfLeave, String note, Date createdAt, Double createdBy, Date updatedAt, Double updatedBy, boolean status) {
        this.id = id;
        this.code = code;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.isManager = isManager;
        this.joinedAt = joinedAt;
        this.offDate = offDate;
        this.dob = dob;
        this.gender = gender;
        this.regional = regional;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
        this.identity_issue_date = identity_issue_date;
        this.photo = photo;
        this.idPhoto = idPhoto;
        this.idPhotoBack = idPhotoBack;
        this.dayOfLeave = dayOfLeave;
        this.note = note;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
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

    public Double getDepartment() {
        return department;
    }

    public void setDepartment(Double department) {
        this.department = department;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Double getGender() {
        return gender;
    }

    public void setGender(Double gender) {
        this.gender = gender;
    }

    public Double getRegional() {
        return regional;
    }

    public void setRegional(Double regional) {
        this.regional = regional;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getIdentity_issue_date() {
        return identity_issue_date;
    }

    public void setIdentity_issue_date(Date identity_issue_date) {
        this.identity_issue_date = identity_issue_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getIdPhotoBack() {
        return idPhotoBack;
    }

    public void setIdPhotoBack(String idPhotoBack) {
        this.idPhotoBack = idPhotoBack;
    }

    public float getDayOfLeave() {
        return dayOfLeave;
    }

    public void setDayOfLeave(float dayOfLeave) {
        this.dayOfLeave = dayOfLeave;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Double createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Double updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
