package com.example.demo.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email"),
			@UniqueConstraint(columnNames = "jiraUsername"),
			@UniqueConstraint(columnNames = "userCode")
			
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank
	private String firstname;
	
	@NotBlank
	private String lastname;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 120)
	@JsonIgnore
	private String password;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String jiraUsername;
	
	@NotNull
	private Long userCode;
	
	private boolean isArchived;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@ManyToOne
    @JoinColumn(name="roleId")
	private Role role;
	
	@OneToMany(mappedBy = "assignedUser",fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Task> tasks;

	@OneToMany(mappedBy = "assignedUser", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Incidence> assignedIncidences;

	@OneToMany(mappedBy = "causedUser", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Incidence> causedIncidences;
	
	@OneToMany(mappedBy = "justificator", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Justification> justifications;
	
	@OneToMany(mappedBy = "validator", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Justification> validations;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_projects", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects = new ArrayList<>();

	public User() {
	}

	public User(String firstname, String lastname, String username, String email, String password, String phone, String jiraUsername, Long userCode, boolean isArchived) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.jiraUsername = jiraUsername;
		this.userCode = userCode;
		this.isArchived = isArchived;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJiraUsername() {
		return jiraUsername;
	}

	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Justification> getJustifications() {
		return justifications;
	}

	public void setJustifications(List<Justification> justifications) {
		this.justifications = justifications;
	}

	public List<Justification> getValidations() {
		return validations;
	}

	public void setValidations(List<Justification> validations) {
		this.validations = validations;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Incidence> getAssignedIncidences() {
		return assignedIncidences;
	}

	public void setAssignedIncidences(List<Incidence> assignedIncidences) {
		this.assignedIncidences = assignedIncidences;
	}

	public List<Incidence> getCausedIncidences() {
		return causedIncidences;
	}

	public void setCausedIncidences(List<Incidence> causedIncidences) {
		this.causedIncidences = causedIncidences;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}