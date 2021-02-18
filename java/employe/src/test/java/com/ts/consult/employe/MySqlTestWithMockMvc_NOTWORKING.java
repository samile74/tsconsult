package com.ts.consult.employe;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.consult.employe.controler.EmployeeRestController;
import com.ts.consult.employe.dao.EmployeDao;
import com.ts.consult.employe.dto.ReceiveDto;
import com.ts.consult.employe.entities.EmployeEntity;
import com.ts.consult.employe.service.EmployeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class MySqlTestWithMockMvc_NOTWORKING {
	
	@Autowired
	private MockMvc mvc;

	
	@MockBean
	private EmployeService service;
	
	@MockBean
	private EmployeDao dao;
	
	@MockBean
	private ReceiveDto dto;
	
	@MockBean
	private EmployeEntity ee;
	
	@Test
	@Rollback
    public void testMe()  throws Exception {
		EmployeEntity alex = new EmployeEntity();
		alex.setName("Alex");
		
		
		EmployeEntity alex2 = new EmployeEntity();
		alex2.setName("Alex");
		
		EmployeEntity paul = new EmployeEntity();
		paul.setName("Paul");
		
		List<EmployeEntity> allEmployees = Arrays.asList(alex);
		
				
		/*mvc.perform(get("/api/public/list")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
		*/
		ReceiveDto rdto=new ReceiveDto();
		rdto.setEmploye(alex);
		String postBody=asJsonString(rdto);
		
		System.out.println(postBody);
		
		mvc.perform(post("/api/public/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(postBody)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.employe.id", is(notNullValue(Long.class))));
		
		mvc.perform(get("/api/public/list")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(1)))
			      .andExpect(jsonPath("$[0].name", is(alex.getName())));
		
    }
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
}
