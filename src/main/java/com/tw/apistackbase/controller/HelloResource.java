package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class HelloResource {
    List<Employee> employeeList = new ArrayList<>();
//    private final Logger log = Logger.getLogger(this.getClass().getName());
//
//    @GetMapping(path = "/{userName}", produces = {"application/json"})
//    public ResponseEntity<String> getAll(@PathVariable String userName) {
//
//        return ResponseEntity.ok("Hello:" + userName);
//    }
    @GetMapping
    public ResponseEntity getAll(){
        employeeList.add(new Employee(1001,"Zhangsan",20,"male",6000));
        return ResponseEntity.ok(employeeList);
    }
//    @PostMapping
//    public ResponseEntity createAEmployee(@RequestBody Employee employee){
//        employeeList.add(employee);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
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
