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

import com.assessment.demo.model.Task;
import com.assessment.demo.service.IProjectService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private IProjectService projectService;
	
	Logger logger = LogManager.getLogger(TaskController.class);

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) {
		ResponseEntity<Task> response = null;
		logger.info("Search Task with Id: "+ taskId);
		Task task = projectService.getTaskById(taskId);

		if (null != task) {
			logger.info("Task found with name: "+ task.getTaskName());
			response = new ResponseEntity<Task>(task, HttpStatus.OK);
		} else {
			logger.info("Task not found.");
			response = new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> taskList = projectService.listTasks();
		logger.info("Get all Tasks with count: "+ taskList.size());
		return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		Task taskReturned = projectService.addTask(task);
		logger.info("Task added successfully with name: " + task.getTaskName());
		return new ResponseEntity<Task>(taskReturned, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Task> saveTask(@RequestBody Task task) {
		Task taskReturned = projectService.updateTaskForStatus(task);
		logger.info("Task updated successfully with name: " + task.getTaskName());
		return new ResponseEntity<Task>(taskReturned, HttpStatus.OK);

	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {

		ResponseEntity<Void> response = null;
		logger.info("Delete Task with Id: "+ taskId);
		Task task = projectService.getTaskById(taskId);

		if (null != task) {
			projectService.deleteTask(taskId);
			response = new ResponseEntity<>(HttpStatus.OK);
			logger.info("Task deleted successfully.");
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.info("Task not found with id: " + taskId);
		}

		return response;
	}

}
