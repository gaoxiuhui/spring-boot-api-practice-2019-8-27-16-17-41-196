package com.tw.apistackbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompaniesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void should_return_ok_and_content_when_query_all_content() throws Exception {
	   //Given
	   MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies");
	   //When
	   ResultActions performResult = mockMvc.perform(requestBuilder);
	   //Then
	   performResult
	   .andDo(MockMvcResultHandlers.print())
	   .andExpect(status().isOk())
	   .andExpect(MockMvcResultMatchers.content()
	   .string("[{\"id\":1,\"baseInformation\":\"百度\",\"employee\":[{\"id\":1},{\"id\":2}]}]"));
	}	
//	//获取某一个特定的公司
//	@Test
//	public void should_return_ok_and_content_when_query_a_company() throws Exception {
//	   //Given
//	   MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/1");
//	   //When
//	   ResultActions performResult = mockMvc.perform(requestBuilder);
//	   //Then
//	   performResult
//	   .andDo(MockMvcResultHandlers.print())
//	   .andExpect(status().isOk())
//	   .andExpect(MockMvcResultMatchers.content()
//	   .string("{\"id\":1,\"baseInformation\":\"百度\",\"employee\":[{\"id\":1},{\"id\":2}]}"));
//	}	
	//获取某一个公司下的所有员工
		@Test
		public void should_return_ok_and_content_when_query_all_employees_of_a_company() throws Exception {
		   //Given
		   MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/1");
		   //When
		   ResultActions performResult = mockMvc.perform(requestBuilder);
		   //Then
		   performResult
		   .andDo(MockMvcResultHandlers.print())
		   .andExpect(status().isOk())
		   .andExpect(MockMvcResultMatchers.content()
		   .string("[{\"id\":1},{\"id\":2}]"));
		}	
	//分页查询
	
		@Test
	    public void should_return_ok_and_content_when_query_companies_by_page() throws Exception {
			//Given
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/pages?page=1&pageSize=1");
			//When
			ResultActions performResult = mockMvc.perform(requestBuilder);
			 //Then
			performResult
			.andDo(MockMvcResultHandlers.print())
		    .andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content()
			.string("[{\"id\":1,\"baseInformation\":\"百度\",\"employee\":[{\"id\":1},{\"id\":2}]}]"));
		}	
}
