package com.example.demo.models;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "types")
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeId;

	@NotBlank
	private String typeName;
	
	@NotBlank
	private String description;
	
	@OneToMany(mappedBy = "fileType")
	@Transient
	@JsonIgnore
    private List<Task> tasks;

	@OneToMany(mappedBy = "fileType")
	@Transient
	@JsonIgnore
    private List<Incidence> incidences;
	
	@OneToMany(mappedBy = "fileType")
	@JsonIgnore
    private List<Equivalence> colonnes;
	
	public Type() {
		
	}

	public Type(String typeName, String description) {
		this.typeName = typeName;
		this.description = description;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(List<Incidence> incidences) {
		this.incidences = incidences;
	}

	public List<Equivalence> getColonnes() {
		return colonnes;
	}

	public void setColonnes(List<Equivalence> colonnes) {
		this.colonnes = colonnes;
	}	
	
}