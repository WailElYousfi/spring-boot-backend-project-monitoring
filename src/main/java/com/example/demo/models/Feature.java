package com.example.demo.models;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "features")
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer featureId;

	@NotBlank
	private String featureName;
	
	@NotBlank
	private String description;
	
	@ManyToOne
    @JoinColumn(name="parentFeatureId")
	private Feature parentFeature;
	
	@ManyToMany(mappedBy = "features")
	@Transient
	@JsonIgnore
    private List<Role> roles;

	public Feature(String featureName, String description, Feature parentFeature) {
		this.featureName = featureName;
		this.description = description;
		this.parentFeature = parentFeature;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Feature getParentFeature() {
		return parentFeature;
	}

	public void setParentFeature(Feature parentFeature) {
		this.parentFeature = parentFeature;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}