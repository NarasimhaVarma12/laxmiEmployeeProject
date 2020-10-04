package com.example.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.example.employee.EmployeeApplication;
import com.example.employee.request.EmployeeDto;
import com.example.employee.service.EmployeeResource;
import com.example.employee.utils.EmployeeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = { EmployeeApplication.class })
@TestPropertySource(locations = "classpath:application.properties")
class EmployeeControllerTest {
	@MockBean
	private EmployeeResource empResource;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objMapper;

	private EmployeeDto employeeDto;

	@BeforeEach
	void setUp() throws Exception {
		employeeDto = EmployeeUtils.employeDtoMock();
	}

	private String baseUri = "http://localhost:8080/v1/bfs/";
	private String empUri = baseUri + "employees/{id}";
	private String empSaveUri = baseUri + "employee";

	@Test
	void testFetchEmployeeById() throws Exception {
		int id = 2;
		when(empResource.fetchEmployeeById(2)).thenReturn(employeeDto);
		mvc.perform(get(empUri, id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(employeeDto.getId())));
	}

	@Test
	void testSave() throws Exception {
		when(empResource.save(employeeDto)).thenReturn(employeeDto);
		mvc.perform(post(empSaveUri).contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(employeeDto))).andExpect(status().isCreated());
				
	}

}
