package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {


    Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
    Employee employeeB = new Employee(1002,"Zhanger",20,"female",7000);
    Employee employeeC = new Employee(1003,"Zhangsan",21,"male",7000);
    List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA,employeeB,employeeC));

    Company companyA = new Company("alibaba",200,employeeList,1001);
    Company companyB = new Company("baidu",100,employeeList,1002);
    List<Company> companyList = new ArrayList<>(Arrays.asList(companyA,companyB));
    private void initCompanyList() {
        for(int i = 0 ; i < 50; i++){
            Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
            Employee employeeB = new Employee(1002,"Zhanger",20,"female",7000);
            Employee employeeC = new Employee(1003,"Zhangsan",21,"male",7000);
            List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA,employeeB,employeeC));

            Company companyA = new Company("alibaba",200,employeeList,1001);
            Company companyB = new Company("baidu",100,employeeList,1002);
            companyList.add(companyA);
            companyList.add(companyB);
        }
    }

//    @GetMapping
//    public ResponseEntity getAllCompanies(){
//        return ResponseEntity.ok(companyList);
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getASpecificCompanies(@PathVariable int id) {
        for (int i = 0; i < companyList.size(); i++) {
            if (companyList.get(i).getId() == id) {
                return ResponseEntity.ok(companyList.get(i));
            }
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping(path = "/{id}/employees")
    public ResponseEntity getASpecificEmployes(@PathVariable int id) {
        for (int i = 0; i < companyList.size(); i++) {
            if (companyList.get(i).getId() == id) {
                return ResponseEntity.ok(companyList.get(i).getEmployees());
            }
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createACompany(@RequestBody Company company){
        companyList.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }
    @PutMapping(path = "/{id}")
    public  ResponseEntity putACompany(@RequestBody Company company){
        for(int i = 0 ; i < companyList.size(); i ++){
            if(companyList.get(i).getId() == company.getId()){
                companyList.get(i).setCompanyName("google");
            }
        }
        return ResponseEntity.ok(companyList);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteACompany(@PathVariable int id){
        for(int i = 0 ; i < companyList.size(); i ++){
            if(companyList.get(i).getId() == id){
                companyList.get(i).setEmployees( new ArrayList<Employee>());
            }
        }
        return ResponseEntity.ok(companyList);
    }

//    public ResponseEntity getSpecificPageEmployees(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize) {
//        if(page!=null&&pageSize!=null){
//            initCompanyList();
//            List<Employee> returnList=new ArrayList<>();
//            for(int i=page-1;i<pageSize+page-1;i++){
//                returnList.add(employeeList.get(i));
//            }
//            return ResponseEntity.ok().body(returnList);
//
//        }
//        return ResponseEntity.ok().body(employeeList);
//    }
    @GetMapping()
    public ResponseEntity getEmployees(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize) {
        initCompanyList();
        if (page != null && pageSize != null) {
            List<Company> returnList = new ArrayList<>();
            for (int i = page - 1; i < pageSize + page - 1; i++) {
                returnList.add(companyList.get(i));
            }
            return ResponseEntity.ok().body(returnList);

        }
        return ResponseEntity.ok().body(companyList);

    }
}
