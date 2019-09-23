package com.assessment.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.demo.model.ParentTask;
import com.assessment.demo.service.IProjectService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/parentTasks")
public class ParentTaskController {

	@Autowired
	private IProjectService projectService;
	
	Logger logger = LogManager.getLogger(ParentTaskController.class);

	@GetMapping("/{parentTaskId}")
	public ResponseEntity<ParentTask> getParentTaskById(@PathVariable("parentTaskId") Long parentTaskId) {
		ResponseEntity<ParentTask> response = null;
		logger.info("Search Parent Task with Id: "+ parentTaskId);
		ParentTask parentTask = projectService.getParentTaskById(parentTaskId);

		if (null != parentTask) {
			logger.info("Parent Task found with name: "+ parentTask.getParentTask());
			response = new ResponseEntity<ParentTask>(parentTask, HttpStatus.OK);
		} else {
			logger.info("Parent Task not found.");
			response = new ResponseEntity<ParentTask>(parentTask, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@GetMapping
	public ResponseEntity<List<ParentTask>> getAllParentTasks() {
		List<ParentTask> parentTaskList = projectService.listParentTasks();
		logger.info("Get all Parent Tasks with count: "+ parentTaskList.size());
		return new ResponseEntity<List<ParentTask>>(parentTaskList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ParentTask> addParentTask(@RequestBody ParentTask parentTask) {
		ParentTask parentTaskReturned = projectService.addParentTask(parentTask);
		logger.info("Parent Task added successfully with name: " + parentTask.getParentTask());
		return new ResponseEntity<ParentTask>(parentTaskReturned, HttpStatus.OK);
	}
	

}
