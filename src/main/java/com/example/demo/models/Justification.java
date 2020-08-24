package com.example.demo.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "justifications")
public class Justification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer justificationId;

	@NotBlank
	private String subject;
	
	@NotBlank
	private String content;
	
	private Boolean isValidated;
	
	private Boolean isArchived;
	
	private Date validationDate;
	
	@Temporal(TemporalType.DATE)
	private Date concernedMonth;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="justificatorId")
	private User justificator;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="validatorId")
	private User validator;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	@JsonIgnore
	private Project project;
	
	public Justification() {
		
	}

	public Justification(String subject, String content, Boolean isArchived, Boolean isValidated,
			Date validationDate, Date concernedMonth, User justificator,
			User validator, Project project) {
		this.subject = subject;
		this.content = content;
		this.isValidated = isValidated;
		this.isArchived = isArchived;
		this.validationDate = validationDate;
		this.concernedMonth = concernedMonth;
		this.justificator = justificator;
		this.validator = validator;
		this.project = project;
	}

	public Integer getJustificationId() {
		return justificationId;
	}

	public void setJustificationId(Integer justificationId) {
		this.justificationId = justificationId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(Boolean isValidated) {
		this.isValidated = isValidated;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public Date getConcernedMonth() {
		return concernedMonth;
	}

	public void setConcernedMonth(Date concernedMonth) {
		this.concernedMonth = concernedMonth;
	}

	public User getJustificator() {
		return justificator;
	}

	public void setJustificator(User justificator) {
		this.justificator = justificator;
	}

	public User getValidator() {
		return validator;
	}

	public void setValidator(User validator) {
		this.validator = validator;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}	
	
	
	
}