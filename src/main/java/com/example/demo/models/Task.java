package com.example.demo.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@NotBlank
	@Column(name="task_key")
	private String key;
	
	private String summary;
	
	@Lob //long text
	private String description;
	
	private String fixVersion;
	
	private String originalEstimate;
	
	private String remainingEstimate;
	
	@Column(name="task_status")
	private String status;
	
	private String timeSpent;
	
	@Lob //long text
	private String comment;
	
	private String taskType;
	
	private Date date;
	
	private Date created;
	
	private Date updated;
	
	private Date resolved;
	
	private Integer idOt;
	
	@ManyToOne
    @JoinColumn(name="fileTypeId")
	private Type fileType;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
	private User assignedUser;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	private Project project;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	public Task() {
		
	}

	public Task(String key, String summary, String originalEstimate,String remainingEstimate, String status, String timeSpent,
			String comment, String taskType, Date date, Date created, Date updated, Date resolved,
			Integer idOt, Type fileType, User assignedUser, Project project, String description, String fixVersion) {
		this.key = key;
		this.summary = summary;
		this.originalEstimate = originalEstimate;
		this.remainingEstimate = remainingEstimate;
		this.status = status;
		this.timeSpent = timeSpent;
		this.comment = comment;
		this.taskType = taskType;
		this.date = date;
		this.created = created;
		this.updated = updated;
		this.resolved = resolved;
		this.idOt = idOt;
		this.fileType = fileType;
		this.assignedUser = assignedUser;
		this.project = project;
		this.description = description;
		this.fixVersion = fixVersion;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public Integer getIdOt() {
		return idOt;
	}

	public void setIdOt(Integer idOt) {
		this.idOt = idOt;
	}

	public Type getFileType() {
		return fileType;
	}

	public void setFileType(Type fileType) {
		this.fileType = fileType;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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