package com.example.demo.models;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

	@NotBlank
	private String projectName;
	
	@NotBlank
	private String description;
	
	private Boolean isClosed;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	//
	@JsonIgnore
    private List<Task> tasks;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	//
	@JsonIgnore
    private List<Incidence> incidences;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Justification> justifications;
	
	@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<User> users;
	
	public Project() {
	}

	public Project(String projectName, String description, Boolean isClosed) {
		this.projectName = projectName;
		this.description = description;
		this.isClosed = isClosed;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(List<Incidence> incidences) {
		this.incidences = incidences;
	}	
	
	
	
}