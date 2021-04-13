package com.example.hr.model;

public class Department {
    private String name;
    private String nameVn;

    public Department(String name, String nameVn) {
        this.name = name;
        this.nameVn = nameVn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }
}
