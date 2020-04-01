package com.example.demo.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "incidences")
public class Incidence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incidenceId;

	@NotBlank
	@Column(name="incidence_key")
	private String key;
	
	@NotBlank
	private String summary;
	
	private String linkedIssues;
	
	private String originalEstimate;
	
	@Lob //long text
	private String description;
	
	private String comment;
	
	private Date created;
	
	private Date updated;
	
	private Date resolved;
	
	private Date plannedEnd;
	
	private String jiraSas;
	
	@NotBlank
	@Column(name="task_status")
	private String status;
	
	private String timeSpent;
	
	@NotBlank
	private String incidenceType;
	
	private Date date;
		
	@ManyToOne
    @JoinColumn(name="fileTypeId")
	private Type fileType;
	
	@ManyToOne
    @JoinColumn(name="assignedUserId")
	private User assignedUser;

	@ManyToOne
    @JoinColumn(name="causedUserId")
	private User causedUser;
	
	@ManyToOne
    @JoinColumn(name="projectId")
//	@JsonIgnore
	private Project project;
	
	public Incidence() {
		
	}

	public Incidence(String key, String summary, String linkedIssues, String originalEstimate, Date created, Date updated, Date resolved,
			Date plannedEnd, String jiraSas, String status, String timeSpent, String description, String comment,
			String incidenceType, Date date, Type fileType, User assignedUser, User causedUser,
			Project project) {
		this.key = key;
		this.summary = summary;
		this.linkedIssues = linkedIssues;
		this.originalEstimate = originalEstimate;
		this.created = created;
		this.updated = updated;
		this.resolved = resolved;
		this.plannedEnd = plannedEnd;
		this.jiraSas = jiraSas;
		this.status = status;
		this.timeSpent = timeSpent;
		this.description = description;
		this.comment = comment;
		this.incidenceType = incidenceType;
		this.date = date;
		this.fileType = fileType;
		this.assignedUser = assignedUser;
		this.causedUser = causedUser;
		this.project = project;
	}


	public Integer getIncidenceId() {
		return incidenceId;
	}

	public void setIncidenceId(Integer incidenceId) {
		this.incidenceId = incidenceId;
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

	public String getLinkedIssues() {
		return linkedIssues;
	}

	public void setLinkedIssues(String linkedIssues) {
		this.linkedIssues = linkedIssues;
	}

	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
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

	public Date getPlannedEnd() {
		return plannedEnd;
	}

	public void setPlannedEnd(Date plannedEnd) {
		this.plannedEnd = plannedEnd;
	}

	public String getJiraSas() {
		return jiraSas;
	}

	public void setJiraSas(String jiraSas) {
		this.jiraSas = jiraSas;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIncidenceType() {
		return incidenceType;
	}

	public void setIncidenceType(String incidenceType) {
		this.incidenceType = incidenceType;
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

	public User getCausedUser() {
		return causedUser;
	}

	public void setCausedUser(User causedUser) {
		this.causedUser = causedUser;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
	
}