package com.tw.apistackbase;

import com.tw.apistackbase.controller.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeesTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void should_get_employess_when_get_employees() throws Exception {
		// this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("[{\"id\":0,\"name\":\"Zhangsan\",\"age\":0,\"gender\":\"male\",\"salary\":6000}]")));
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees")).andExpect(status().isOk()).andReturn();
		JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
		assertEquals(1001,jsonArray.getJSONObject(0).getInt("id"));
		assertEquals(20,jsonArray.getJSONObject(0).getInt("age"));
		assertEquals("Zhangyi",jsonArray.getJSONObject(0).getString("name"));
		assertEquals("male",jsonArray.getJSONObject(0).getString("gender"));
		assertEquals(6000,jsonArray.getJSONObject(0).getInt("salary"));
	}
	@Test
	public void should_get_a_specific_employee_when_get_an_employee_id() throws Exception{
		final MvcResult mvcResult = this.mockMvc.perform(get("/employees/1002")).andExpect(status().isOk()).andReturn();
		JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
		assertEquals(1002,jsonObject.getInt("id"));
		assertEquals(20,jsonObject.getInt("age"));
		assertEquals("Zhanger",jsonObject.getString("name"));
		assertEquals("female",jsonObject.getString("gender"));
		assertEquals(7000,jsonObject.getInt("salary"));

	}
//	@Test
//	public void should_get_specific_page_employees_when_get_page_number_and_pageSize_number() throws Exception{
//
//	}
//	@Test
//	public void should_get_screen_all_male_employees_when_get_a_gender() throws Exception{
//
//	}
//	@Test
//	public void should_add_an_employee_when_post_a_employee() throws Exception{
//
//	}
//	@Test
//	public void should_update_an_employee_when_put_a_employeeID() throws Exception{
//
//	}
//	@Test
//	public void should_delete_an_employee_when_delete_a_employeeID() throws Exception{
//
//	}
}
