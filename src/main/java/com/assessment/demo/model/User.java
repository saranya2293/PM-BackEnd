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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ravi
 *
 */

@Entity
@Table(name = "User_DB_TBL")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", updatable = false, nullable = false)
	private long userId;

	private String firstName;

	private String lastName;

	private String employeeId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Project> projects = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Task> tasks = new HashSet<>();

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public User(String firstName, String lastName, String employeeId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}

	public User() {
		super();
	}

}
