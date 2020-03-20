package com.example.demo.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@NotBlank
	@Column(name="task_key")
	private String key;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String fixVersion;
	
	@NotBlank
	private String originalEstimate;
	
	@NotBlank
	private String remainingEstimate;
	
	@NotBlank
	@Column(name="task_status")
	private String status;
	
	@NotBlank
	private String timeSpent;
	
	@NotBlank
	private String comment;
	
	@NotBlank
	private String taskType;
	
	private Date date;
	
	@ManyToOne
    @JoinColumn(name="fileTypeId")
	private Type fileType;
	
	@ManyToOne
    @JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	private Project project;
	
	public Task() {
		
	}

	public Task(String key, String title, String description, String fixVersion,
			String originalEstimate, String remainingEstimate, String status,
			String timeSpent, String comment, String taskType, Date date,
			Type fileType, User user, Project project) {
		this.key = key;
		this.title = title;
		this.description = description;
		this.fixVersion = fixVersion;
		this.originalEstimate = originalEstimate;
		this.remainingEstimate = remainingEstimate;
		this.status = status;
		this.timeSpent = timeSpent;
		this.comment = comment;
		this.taskType = taskType;
		this.date = date;
		this.fileType = fileType;
		this.user = user;
		this.project = project;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFixVersion() {
		return fixVersion;
	}

	public void setFixVersion(String fixVersion) {
		this.fixVersion = fixVersion;
	}

	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public String getRemainingEstimate() {
		return remainingEstimate;
	}

	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Type getFileType() {
		return fileType;
	}

	public void setFileType(Type fileType) {
		this.fileType = fileType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}