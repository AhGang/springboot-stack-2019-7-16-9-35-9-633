package com.tw.apistackbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {


    Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
    Employee employeeB = new Employee(1002,"Zhanger",20,"female",7000);
    Employee employeeC = new Employee(1003,"Zhangsan",21,"male",7000);
    List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA,employeeB,employeeC));

    Company companyA = new Company("alibaba",200,employeeList);
    Company companyB = new Company("baidu",100,employeeList);
    List<Company> companyList = new ArrayList<>(Arrays.asList(companyA,companyB));
    @GetMapping
    public ResponseEntity getAllCompanies(){
        return ResponseEntity.ok(companyList);
    }
}
