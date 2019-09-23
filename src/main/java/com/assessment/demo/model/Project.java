/**

 *

 */

package com.assessment.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ravi
 *
 */

@Entity
@Table(name = "Project_DB_TBL")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId", updatable = false, nullable = false)
	private long projectId;

	private String projectName;

//	@JsonFormat(pattern = "MM/dd/yyyy")
//	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;

//	@JsonFormat(pattern = "MM/dd/yyyy")
//	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;

	private int priority;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private Set<Task> tasks = new HashSet<>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private Set<ParentTask> parentTasks = new HashSet<>();

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<ParentTask> getParentTasks() {
		return parentTasks;
	}

	public void setParentTasks(Set<ParentTask> parentTasks) {
		this.parentTasks = parentTasks;
	}

	public Project(long projectId, String projectName, Date startDate, Date endDate, int priority, User user) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priority=" + priority + "]";
	}

	public Project() {
		super();
	}

}
