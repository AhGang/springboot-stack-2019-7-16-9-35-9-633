package com.tw.apistackbase;
import com.tw.apistackbase.controller.Company;
import com.tw.apistackbase.controller.Employee;
import org.json.JSONArray;

import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CompanyTest {
    @Autowired
    private MockMvc mockMvc;
    List<Company> companyList = new ArrayList<>(Arrays.asList());
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
    @Test
    public void should_get_companies_when_get_companies() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get("/companies")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("google", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(200, jsonArray.getJSONObject(0).getInt("employeesNumber"));
        Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
        JSONObject employeeAJsonObject = new JSONObject(employeeA);
        assertEquals(employeeAJsonObject.toString(), jsonArray.getJSONObject(0).getJSONArray("employees").get(0).toString());
    }
    @Test
    public void should_get_a_specific_company_when_get_an_company_id() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get("/companies/1001")).andExpect(status().isOk()).andReturn();
        JSONObject jsonObject1 = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("google", jsonObject1.getString("companyName"));
        assertEquals(200, jsonObject1.getInt("employeesNumber"));
        Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
        JSONObject employeeAJsonObject = new JSONObject(employeeA);
        assertEquals(employeeAJsonObject.toString(), jsonObject1.getJSONArray("employees").get(0).toString());

    }
    @Test
    public void should_get_a_specific_employees_when_get_an_company_id() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get("/companies/1001/employees")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
        JSONObject employeeAJsonObject = new JSONObject(employeeA);
        assertEquals(employeeAJsonObject.toString(), jsonArray.get(0).toString());
    }
    @Test
    public void should_add_an_company_when_post_a_company() throws Exception {
        Employee employeeA = new Employee(1003,"Zhangyi",20,"male",6000);
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA));
        Company companyA = new Company("tengxun",200,employeeList,1003);

        JSONObject companyAJsonObject = new JSONObject(companyA);
        final MvcResult mvcResult = this.mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(companyAJsonObject.toString())).andExpect(status().isCreated()).andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("tengxun", jsonObject.getString("companyName"));
        assertEquals(200, jsonObject.getInt("employeesNumber"));
        assertEquals(1003, jsonObject.getInt("id"));
    }

    @Test
    public void should_update_an_company_when_put_a_companyID() throws Exception {
        Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeA));
        Company companyA = new Company("alibaba",200,employeeList,1001);
        JSONObject companyAJsonObject = new JSONObject(companyA);
        final MvcResult mvcResult = this.mockMvc.perform(put("/companies/1001").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(companyAJsonObject.toString())).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(1001, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("google", jsonArray.getJSONObject(0).getString("companyName"));

    }

    @Test
    public void should_delete_an_company_when_delete_a_companyID() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(delete("/companies/1002")).andExpect(status().isOk()).andReturn();

    }
    @Test
    public void should_return_employees_when_give_page_and_pageSize() throws Exception {
        initCompanyList();
        final MvcResult mvcResult = this.mockMvc.perform(get("/companies?page=1&pageSize=5")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(companyList.get(0).getId(),jsonArray.getJSONObject(0).get("id"));
    }
}
