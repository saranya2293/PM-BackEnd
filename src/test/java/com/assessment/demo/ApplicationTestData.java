package com.assessment.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.assessment.demo.model.ParentTask;
import com.assessment.demo.model.Project;
import com.assessment.demo.model.Task;
import com.assessment.demo.model.User;

public class ApplicationTestData {
	
	public static Project getProjectDataToRead() {
		Project project = new Project(1, "FSD assessment for Project Manager in all screens.", dateFromString("09/31/2019")
				, dateFromString("11/26/2019"), 9, getUserDataToRead());
		return project;
	}
	
	public static Project getProjectDataToWrite() {
		Project project = new Project();
		project.setPriority(12);
		project.setProjectName("Update created Task.");
		project.setStartDate(dateFromString("09/19/2019"));
		project.setEndDate(dateFromString("10/12/2019"));
		project.setUser(getUserDataToRead());
		
		return project;
	}
	
	public static User getUserDataToRead() {
		User user = new User();
		user.setEmployeeId("SU549176");
		user.setFirstName("Saranya");
		user.setLastName("Unnikumar");
		user.setUserId(1);
		
		return user;
	}
	
	public static User getUserDataToWrite() {
		User user = new User("Pooja", "Kumari", "PK23678");
		return user;
	}
	
	public static Task getTaskDataToRead() {
		Task task = new Task();
		
		task.setTaskId(12);
		task.setPriority(8);
		task.setTaskName("Child task for checkbox validation after task creation on load ");
		task.setStartDate(dateFromString("09/19/2019"));
		task.setEndDate(dateFromString("11/26/2019"));
		task.setStatus("New");
		task.setUser(getUserDataToRead());
		
		return task;
	}
	
	public static Task getTaskDataToWrite() {
		Task task = new Task("Testing task for Junit Test.", dateFromString("09/22/2019"), dateFromString("12/11/2019"), 
				24, "New", getParentTaskDataToRead(), getProjectDataToRead());
		
		return task;
	}
	
	public static ParentTask getParentTaskDataToRead() {
		ParentTask parentTask = new ParentTask(9, "Parent task for checkbox validation on load after task creation");
		return parentTask;
	}
	
	public static ParentTask getParentTaskDataToWrite() {
		ParentTask parentTask = new ParentTask();
		
		parentTask.setParentTask("Testing parent task for Junit validation.");
		parentTask.setProject(getProjectDataToRead());
		
		return parentTask;
	}
	
	public static Date dateFromString(final String sDate) {
		String dateFormat = "MM/dd/yyyy";
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
		try {
			return sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
