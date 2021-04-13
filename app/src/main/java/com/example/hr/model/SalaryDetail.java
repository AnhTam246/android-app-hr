package com.example.hr.model;

public class SalaryDetail {
    private double salary;
    private double baseSalaryContract = 0;
    private double totalDayWork = 0;
    private double totalSpecialDay = 0;
    private double salaryOt;
    private double totalAllowance = 0;
    private double totalInsurance = 0;
    private double totalTax = 0;
    private double incomeTax = 0;
    private double taxableIncome = 0;
    private double personalTax = 0;
    private double salaryActuallyReceived = 0;
    private Salary salaryDetail;

    public SalaryDetail() {
    }

    public SalaryDetail(double salary, double baseSalaryContract, double totalDayWork, double totalSpecialDay, double salaryOt, double totalAllowance, double totalInsurance, double totalTax, double incomeTax, double taxableIncome, double personalTax, double salaryActuallyReceived, Salary salaryDetail) {
        this.salary = salary;
        this.baseSalaryContract = baseSalaryContract;
        this.totalDayWork = totalDayWork;
        this.totalSpecialDay = totalSpecialDay;
        this.salaryOt = salaryOt;
        this.totalAllowance = totalAllowance;
        this.totalInsurance = totalInsurance;
        this.totalTax = totalTax;
        this.incomeTax = incomeTax;
        this.taxableIncome = taxableIncome;
        this.personalTax = personalTax;
        this.salaryActuallyReceived = salaryActuallyReceived;
        this.salaryDetail = salaryDetail;
    }

    public Salary getSalaryDetail() {
        return salaryDetail;
    }

    public void setSalaryDetail(Salary salaryDetail) {
        this.salaryDetail = salaryDetail;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBaseSalaryContract() {
        return baseSalaryContract;
    }

    public void setBaseSalaryContract(double baseSalaryContract) {
        this.baseSalaryContract = baseSalaryContract;
    }

    public double getTotalDayWork() {
        return totalDayWork;
    }

    public void setTotalDayWork(float totalDayWork) {
        this.totalDayWork = totalDayWork;
    }

    public double getTotalSpecialDay() {
        return totalSpecialDay;
    }

    public void setTotalSpecialDay(float totalSpecialDay) {
        this.totalSpecialDay = totalSpecialDay;
    }

    public double getSalaryOt() {
        return salaryOt;
    }

    public void setSalaryOt(double salaryOt) {
        this.salaryOt = salaryOt;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public double getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(double totalInsurance) {
        this.totalInsurance = totalInsurance;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getPersonalTax() {
        return personalTax;
    }

    public void setPersonalTax(double personalTax) {
        this.personalTax = personalTax;
    }

    public double getSalaryActuallyReceived() {
        return salaryActuallyReceived;
    }

    public void setSalaryActuallyReceived(double salaryActuallyReceived) {
        this.salaryActuallyReceived = salaryActuallyReceived;
    }
}
