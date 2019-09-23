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
import com.assessment.demo.controller.UserController;
import com.assessment.demo.model.User;
import com.assessment.demo.service.IProjectService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

	public UserControllerTests() {
	}

	@Mock
	private IProjectService service;

	private MockMvc mockMvc;

	@Spy
	@InjectMocks
	private UserController controller = new UserController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetUserById() throws Exception {
		User user = ApplicationTestData.getUserDataToRead();

		Mockito.when(service.getUserById(Mockito.anyLong())).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/1")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1));
	}

	@Test
	public void testAddUser() throws Exception {
		User user = ApplicationTestData.getUserDataToWrite();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/users").content(jsonString)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = ApplicationTestData.getUserDataToRead();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/users").content(jsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void testDeleteUserByid() throws Exception {
		User user = ApplicationTestData.getUserDataToRead();

		Mockito.when(service.getUserById(Mockito.anyLong())).thenReturn(user);
		Mockito.doNothing().when(service).deleteUser(Mockito.anyLong());

		mockMvc.perform(MockMvcRequestBuilders.delete("/users/1")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testDeleteUser_WhenUnSuccess() throws Exception {
		User user = null;
		Mockito.when(service.getUserById(Mockito.anyLong())).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/0")).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void testGetUser_WhenUnSuccess() throws Exception {
		User user = null;

		Mockito.when(service.getUserById(Mockito.anyLong())).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/0")).andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	public void testGetAllUsers() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
