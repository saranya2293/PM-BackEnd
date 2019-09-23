package com.assessment.demo.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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

import com.assessment.demo.model.Project;
import com.assessment.demo.service.IProjectService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	Logger logger = LogManager.getLogger(ProjectController.class);

	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long projectId) {
		ResponseEntity<Project> response = null;
		
		logger.info("Search Project with Id: "+ projectId);

		Project project = projectService.getProjectById(projectId);

		if (null != project) {
			logger.info("Project found with Name: "+ project.getProjectName());
			response = new ResponseEntity<Project>(project, HttpStatus.OK);
		} else {
			logger.info("Project not found.");
			response = new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> projectList = projectService.listProjects();
		logger.info("Get all Projects with count: "+ projectList.size());
		return new ResponseEntity<List<Project>>(projectList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Project> addProject(@RequestBody Project project) {
		Project projectReturned = projectService.addProject(project);
		logger.info("Project added successfully with name: " + project.getProjectName());
		return new ResponseEntity<Project>(projectReturned, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Project> saveProject(@RequestBody Project project) {
		Project projectReturned = projectService.addProject(project);
		logger.info("Project updated successfully with name: " + project.getProjectName());
		return new ResponseEntity<Project>(projectReturned, HttpStatus.OK);

	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId) {

		ResponseEntity<Void> response = null;
		logger.info("Delete Project with Id: "+ projectId);
		Project project = projectService.getProjectById(projectId);

		if (null != project) {
			projectService.deleteProject(projectId);
			response = new ResponseEntity<>(HttpStatus.OK);
			logger.info("Project deleted successfully.");
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.info("Project not found with id: " + projectId);
		}

		return response;
	}

}
