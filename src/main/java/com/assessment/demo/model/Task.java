/**

 *

 */

package com.assessment.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author Ravi
 *
 */

@Entity
@Table(name = "Task_DB_TBL")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId", updatable = false, nullable = false)
	private long taskId;

	private String taskName;

	private Date startDate;

	private Date endDate;

	private int priority;

	private String status;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private ParentTask parentTask;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "project_id")
	private Project project;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	@JsonIgnore
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task(String taskName, Date startDate, Date endDate, int priority, String status, ParentTask parentTask,
			Project project) {
		super();
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
		this.parentTask = parentTask;
		this.project = project;
	}

	public Task() {
		super();
	}

}
