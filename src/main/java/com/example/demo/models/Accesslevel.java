package com.example.demo.models;


import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accesslevels")
public class Accesslevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accesslevelId;

	private Integer levelNumber;
	
	private String description;
	
	@OneToMany(mappedBy = "accessLevel", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<RoleFeature> rolefeatures;
	
	public Accesslevel() {
	}

	public Accesslevel(Integer levelNumber, String description) {
		this.levelNumber = levelNumber;
		this.description = description;
	}

	public Integer getAccesslevelId() {
		return accesslevelId;
	}

	public void setAccesslevelId(Integer accesslevelId) {
		this.accesslevelId = accesslevelId;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleFeature> getRolefeatures() {
		return rolefeatures;
	}

	public void setRolefeatures(List<RoleFeature> rolefeatures) {
		this.rolefeatures = rolefeatures;
	}

	
}