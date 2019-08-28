package com.tw.apistackbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
//��ȡ�б�	
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
	   .string("[{\"id\":1,\"baseInformation\":\"�ٶ�\",\"employee\":[{\"id\":1},{\"id\":2}]}]"));
	}	
	
	//��ȡĳһ���ض��Ĺ�˾
	@Test
	public void should_return_ok_and_content_when_query_a_company() throws Exception {
	   //Given
	   MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/companies/1");
	   //When
	   ResultActions performResult = mockMvc.perform(requestBuilder);
	   //Then
	   performResult
	   .andDo(MockMvcResultHandlers.print())
	   .andExpect(status().isOk())
	   .andExpect(MockMvcResultMatchers.content()
	   .string("{\"id\":1,\"baseInformation\":\"�ٶ�\",\"employee\":[{\"id\":1},{\"id\":2}]}"));
	}	
	
	//��ȡĳһ����˾�µ�����Ա��
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
		
	//��ҳ��ѯ
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
			.string("[{\"id\":1,\"baseInformation\":\"�ٶ�\",\"employee\":[{\"id\":1},{\"id\":2}]}]"));
		}	
    //���һ����˾
		@Test
		public void should_return_201_status_when_create_a_company() throws Exception {
			//Given
			MockHttpServletRequestBuilder input = post("/companies")
		.content("{\"id\":2,\"baseInformation\":\"����\",\"employee\":[{\"id\":1},{\"id\":2}]}")
		.contentType(MediaType.APPLICATION_JSON);
			//When
			ResultActions result = mockMvc.perform(input);
			//Then
			result
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isCreated());
		}
		
		
	//����һ����˾����Ϣ
		@Test
		public void should_return_201_status_when_update_a_company() throws Exception {
			//Given
			MockHttpServletRequestBuilder input = post("/companies")
		.content("{\"id\":1,\"baseInformation\":\"����\",\"employee\":[{\"id\":1},{\"id\":2}]}")
		.contentType(MediaType.APPLICATION_JSON);
			//When
			ResultActions result = mockMvc.perform(input);
			//Then
			result
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isCreated());
		}
		
		//ɾ��һ����˾����Ϣ
		@Test
		public void should_return_ok_and_content_when_delete_a_company() throws Exception {
		   //Given
		   MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/companies/1");
		   //When
		   ResultActions performResult = mockMvc.perform(requestBuilder);
		   //Then
		   performResult
		   .andDo(MockMvcResultHandlers.print())
		   .andExpect(status().isOk())
		   .andExpect(MockMvcResultMatchers.content()
		   .string(""));
		}	
		
		
}
