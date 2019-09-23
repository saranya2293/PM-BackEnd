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
import com.assessment.demo.controller.ProjectController;
import com.assessment.demo.model.Project;
import com.assessment.demo.service.IProjectService;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTests {

	public ProjectControllerTests() {
	}

	@Mock
	private IProjectService service;

	private MockMvc mockMvc;

	@Spy
	@InjectMocks
	private ProjectController controller = new ProjectController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetProjectById() throws Exception {
		Project project = ApplicationTestData.getProjectDataToRead();

		Mockito.when(service.getProjectById(Mockito.anyLong())).thenReturn(project);

		mockMvc.perform(MockMvcRequestBuilders.get("/projects/1")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(1));

	}

	@Test
	public void testAddProject() throws Exception {
		Project project = ApplicationTestData.getProjectDataToWrite();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(project);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/projects").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testUpdateProject() throws Exception {
		Project project = ApplicationTestData.getProjectDataToRead();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(project);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/projects").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void testDeleteProjectByid() throws Exception {
		Project project = ApplicationTestData.getProjectDataToRead();

		Mockito.when(service.getProjectById(Mockito.anyLong())).thenReturn(project);
		Mockito.doNothing().when(service).deleteProject(Mockito.anyLong());

		mockMvc.perform(MockMvcRequestBuilders.delete("/projects/1")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testDeleteProject_WhenUnSuccess() throws Exception {
		Project project = null;
		Mockito.when(service.getProjectById(Mockito.anyLong())).thenReturn(project);
		mockMvc.perform(MockMvcRequestBuilders.delete("/projects/0")).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void testGetProject_WhenUnSuccess() throws Exception {
		Project project = null;

		Mockito.when(service.getProjectById(Mockito.anyLong())).thenReturn(project);

		mockMvc.perform(MockMvcRequestBuilders.get("/projects/0")).andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	public void testGetAllProjects() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/projects")).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
