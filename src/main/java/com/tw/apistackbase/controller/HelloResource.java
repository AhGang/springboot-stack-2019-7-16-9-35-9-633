package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class HelloResource {
    Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
    Employee employeeB = new Employee(1002,"Zhanger",20,"female",7000);
    List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA,employeeB));
//    private final Logger log = Logger.getLogger(this.getClass().getName());
//
//    @GetMapping(path = "/{userName}", produces = {"application/json"})
//    public ResponseEntity<String> getAll(@PathVariable String userName) {
//
//        return ResponseEntity.ok("Hello:" + userName);
//    }
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getASpecificEmployee(@PathVariable int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                return ResponseEntity.ok(employeeList.get(i));
            }
        }
        return ResponseEntity.ok().build();
    }
//    @PutMapping
//    public  ResponseEntity putAEmployee(@RequestBody Employee employee){
//        // Employee employee1 = employeeList.stream().filter(e -> e.getId() == employee.getId()).collect(Collectors.toList());
//        for(int i = 0 ; i < employeeList.size(); i ++){
//            if(employeeList.get(i).getId() == employee.getId()){
//                employeeList.get(i).setName(employee.getName());
//                employeeList.get(i).setAge(employee.getAge());
//                employeeList.get(i).setGender(employee.getGender());
//
//            }
//        }
//        return ResponseEntity.ok(employeeList);
//    }
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity deleteAEmployee(@PathVariable int id){
//        for(int i = 0 ; i < employeeList.size(); i ++){
//            if(employeeList.get(i).getId() == id){
//                employeeList.remove(employeeList.get(i));
//            }
//        }
//        return ResponseEntity.ok(employeeList);
//    }

}
