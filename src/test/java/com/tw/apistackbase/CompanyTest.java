package com.tw.apistackbase;
import com.tw.apistackbase.controller.Company;
import com.tw.apistackbase.controller.Employee;
import org.json.JSONArray;

import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Test;
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

    @Test
    public void should_get_companies_when_get_companies() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get("/companies")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("alibaba", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(200, jsonArray.getJSONObject(0).getInt("employeesNumber"));
        Employee employeeA = new Employee(1001,"Zhangyi",20,"male",6000);
        JSONObject employeeAJsonObject = new JSONObject(employeeA);
        assertEquals(employeeAJsonObject.toString(), jsonArray.getJSONObject(0).getJSONArray("employees").get(0).toString());
    }
    @Test
    public void should_get_a_specific_company_when_get_an_company_id() throws Exception {

    }
//    @Test
//    public void should_get_a_specific_employees_when_get_an_company_id() throws Exception {}
    //	@Test
//	public void should_get_specific_page_employees_when_get_page_number_and_pageSize_number() throws Exception{
//
//	}
//    @Test
//    public void should_add_an_employee_when_post_a_employee() throws Exception {
//
//    }
//
//    @Test
//    public void should_update_an_employee_when_put_a_employeeID() throws Exception {
//
//
//    }
//
//    @Test
//    public void should_delete_an_employee_when_delete_a_employeeID() throws Exception {
//
//    }
}
