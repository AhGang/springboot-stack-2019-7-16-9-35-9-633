package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class EmployController {

    Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
    Employee employeeB = new Employee(1002,"Zhanger",20,"female",7000);
    Employee employeeC = new Employee(1003,"Zhangsan",21,"male",7000);
    List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA,employeeB,employeeC));
    private void initEmployeeList() {
        for(int i = 0 ; i < 50; i++){
            Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
            employeeList.add(employeeA);
        }
    }
    @GetMapping()
    public ResponseEntity getEmployees(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) String gender){
        initEmployeeList();
        if(page!=null&&pageSize!=null){
            List<Employee> returnList=new ArrayList<>();
            for(int i=page-1;i<pageSize+page-1;i++){
                returnList.add(employeeList.get(i));
            }
            return ResponseEntity.ok().body(returnList);

        }
        if(gender!=null){
            return ResponseEntity.ok().body(employeeList.stream().filter(ee->ee.getGender().equals("male")).collect(Collectors.toList()));
        }
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getASpecificEmployee(@PathVariable int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                return ResponseEntity.ok(employeeList.get(i));
            }
        }
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping
    public ResponseEntity createAEmployee(@RequestBody Employee employee){
        employeeList.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
    @PutMapping(path = "/{id}")
    public  ResponseEntity putASpecificEmployee(@RequestBody Employee employee){
        // Employee employee1 = employeeList.stream().filter(e -> e.getId() == employee.getId()).collect(Collectors.toList());
        for(int i = 0 ; i < employeeList.size(); i ++){
            if(employeeList.get(i).getId() == employee.getId()){
                employeeList.get(i).setAge(25);
            }
        }
        return ResponseEntity.ok(employeeList);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteASpecificEmployee(@PathVariable int id){
        for(int i = 0 ; i < employeeList.size(); i ++){
            if(employeeList.get(i).getId() == id){
                employeeList.remove(employeeList.get(i));
            }
        }
        return ResponseEntity.ok(employeeList);
    }

}
