package com.assessment.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.assessment.demo.controller.ParentTaskController;
import com.assessment.demo.model.ParentTask;
import com.assessment.demo.service.IProjectService;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskControllerTests {

	public ParentTaskControllerTests() {
	}

	@Mock
	private IProjectService service;

	private MockMvc mockMvc;

	@Spy
	@InjectMocks
	private ParentTaskController controller = new ParentTaskController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetParentTaskById() throws Exception {
		ParentTask parentTask = ApplicationTestData.getParentTaskDataToRead();

		Mockito.when(service.getParentTaskById(Mockito.anyLong())).thenReturn(parentTask);

		mockMvc.perform(MockMvcRequestBuilders.get("/parentTasks/9")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.parentId").value(9));
	}

	@Test
	public void testAddParentTask() throws Exception {
		ParentTask parentTask = ApplicationTestData.getParentTaskDataToWrite();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(parentTask);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/parentTasks").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testGetAllParentTasks() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/parentTasks")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testGetParentTask_WhenUnSuccess() throws Exception {
		ParentTask parentTask = null;

		Mockito.when(service.getParentTaskById(Mockito.anyLong())).thenReturn(parentTask);

		mockMvc.perform(MockMvcRequestBuilders.get("/parentTasks/0")).andExpect(MockMvcResultMatchers.status().is(404));

	}

}
