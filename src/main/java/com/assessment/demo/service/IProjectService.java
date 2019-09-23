package com.assessment.demo.service;

import java.util.List;

import com.assessment.demo.model.ParentTask;
import com.assessment.demo.model.Project;
import com.assessment.demo.model.Task;
import com.assessment.demo.model.User;

public interface IProjectService {
	
	public User getUserById(Long userId);
	
	public List<User> listUsers(); 
	
	public User addUser(User user);
	
	public void deleteUser(Long userId);
	
	public Project getProjectById(Long projectId);
	
	public List<Project> listProjects(); 
	
	public Project addProject(Project project);
	
	public void deleteProject(Long projectId);
	
	public Task getTaskById(Long taskId);
	
	public List<Task> listTasks(); 
	
	public Task addTask(Task task);
	
	public Task updateTaskForStatus(Task task);
	
	public void deleteTask(Long taskId);
	
	public ParentTask getParentTaskById(Long parentTaskId);
	
	public List<ParentTask> listParentTasks(); 
	
	public ParentTask addParentTask(ParentTask parentTask);
	
}
