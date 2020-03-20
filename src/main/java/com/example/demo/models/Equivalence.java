package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "equivalences")
public class Equivalence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer equivalenceId;

	@NotBlank
	private String columnName;
	
	private String jiraEquivalence;
	
	private String fenixEquivalence;
	
	private Integer ColumnOrder;
	
	@ManyToOne
    @JoinColumn(name="fileTypeId")
	private Type fileType;

	public Equivalence() {
		
	}

	public Equivalence(String columnName, String jiraEquivalence, String fenixEquivalence, Integer ColumnOrder, Type fileType) {
		this.columnName = columnName;
		this.jiraEquivalence = jiraEquivalence;
		this.fenixEquivalence = fenixEquivalence;
		this.ColumnOrder = ColumnOrder;
		this.fileType = fileType;
	}

	public Integer getEquivalenceId() {
		return equivalenceId;
	}

	public void setEquivalenceId(Integer equivalenceId) {
		this.equivalenceId = equivalenceId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getJiraEquivalence() {
		return jiraEquivalence;
	}

	public void setJiraEquivalence(String jiraEquivalence) {
		this.jiraEquivalence = jiraEquivalence;
	}

	public String getFenixEquivalence() {
		return fenixEquivalence;
	}

	public void setFenixEquivalence(String fenixEquivalence) {
		this.fenixEquivalence = fenixEquivalence;
	}

	public Type getFileType() {
		return fileType;
	}

	public void setFileType(Type fileType) {
		this.fileType = fileType;
	}

	public Integer getColumnOrder() {
		return ColumnOrder;
	}

	public void setColumnOrder(Integer ColumnOrder) {
		this.ColumnOrder = ColumnOrder;
	}	
	
}