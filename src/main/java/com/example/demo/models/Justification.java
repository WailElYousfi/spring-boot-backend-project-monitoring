package com.example.demo.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	private Boolean isValidated;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	private Date validationDate;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@NotBlank
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@ManyToOne
    @JoinColumn(name="justificatorId")
	private User justificator;
	
	@ManyToOne
    @JoinColumn(name="validatorId")
	private User validator;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	@JsonIgnore
	private Project project;
	
	public Justification() {
		
	}

	public Justification(String subject, String content, Boolean isValidated,
			Date validationDate, Date startDate, Date endDate, User justificator,
			User validator, Project project) {
		this.subject = subject;
		this.content = content;
		this.isValidated = isValidated;
		this.validationDate = validationDate;
		this.startDate = startDate;
		this.endDate = endDate;
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
	
}