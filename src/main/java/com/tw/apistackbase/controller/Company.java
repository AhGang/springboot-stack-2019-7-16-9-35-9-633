package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;
    private int id;

    public Company(String companyName, int employeesNumber, List<Employee> employees, int id) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
        this.id = id;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
