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
import com.assessment.demo.controller.TaskController;
import com.assessment.demo.model.Task;
import com.assessment.demo.service.IProjectService;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTests {
	
	public TaskControllerTests() {
	}
	
	@Mock
	private IProjectService service;

	private MockMvc mockMvc;

	@Spy
	@InjectMocks
	private TaskController controller = new TaskController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetTaskById() throws Exception {
		Task task = ApplicationTestData.getTaskDataToRead();
		
		Mockito.when(service.getTaskById(Mockito.anyLong())).thenReturn(task);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tasks/12"))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.taskId").value(12));
	}
	
	@Test
	public void testAddTask() throws Exception {
		Task task = ApplicationTestData.getTaskDataToWrite();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(task);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/tasks").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testUpdateTask() throws Exception {
		Task task = ApplicationTestData.getTaskDataToRead();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(task);
		
		mockMvc.perform(
				MockMvcRequestBuilders.put("/tasks").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testDeleteTaskByid() throws Exception {
		Task task = ApplicationTestData.getTaskDataToRead();
		
		Mockito.when(service.getTaskById(Mockito.anyLong())).thenReturn(task);
		Mockito.doNothing().when(service).deleteTask(Mockito.anyLong());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/12")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testDeleteTask_WhenUnSuccess() throws Exception {
		Task task = null;
		Mockito.when(service.getTaskById(Mockito.anyLong())).thenReturn(task);
		mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/0")).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void testGetTask_WhenUnSuccess() throws Exception {
		Task task = null;

		Mockito.when(service.getTaskById(Mockito.anyLong())).thenReturn(task);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tasks/0"))
			.andExpect(MockMvcResultMatchers.status().is(404));

	}
	
	@Test
	public void testGetAllTasks() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
