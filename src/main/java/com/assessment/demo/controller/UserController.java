package com.assessment.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.demo.model.User;
import com.assessment.demo.service.IProjectService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IProjectService projectService;
	
	Logger logger = LogManager.getLogger(UserController.class);

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
		ResponseEntity<User> response = null;
		logger.info("Search User with Id: "+ userId);
		User user = projectService.getUserById(userId);

		if (null != user) {
			logger.info("User found with name: "+ user.getFirstName() + " " + user.getLastName());
			response = new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			logger.info("User not found.");
			response = new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = projectService.listUsers();
		logger.info("Get all Users with count: "+ userList.size());
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User userReturned = projectService.addUser(user);
		logger.info("User added successfully with name: " + user.getFirstName() + " " + user.getLastName());
		return new ResponseEntity<User>(userReturned, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User userReturned = projectService.addUser(user);
		logger.info("User updated successfully with name: " + user.getFirstName() + " " + user.getLastName());
		return new ResponseEntity<User>(userReturned, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {

		ResponseEntity<Void> response = null;
		logger.info("Delete User with Id: "+ userId);
		User user = projectService.getUserById(userId);

		if (null != user) {
			projectService.deleteUser(userId);
			logger.info("User deleted successfully.");
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			logger.info("User not found with id: " + userId);
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}

}
