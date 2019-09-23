/**

 *

 */

package com.assessment.demo.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author Ravi
 *
 */

@Entity
@Table(name = "Parent_Task_DB_TBL")
public class ParentTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parentId", updatable = false, nullable = false)
	private long parentId;

	private String parentTask;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
	private Set<Task> tasks = new HashSet<>();

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public ParentTask(long parentId, String parentTask) {
		super();
		this.parentId = parentId;
		this.parentTask = parentTask;
	}

	@JsonIgnore
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ParentTask() {
		super();
	}

}
