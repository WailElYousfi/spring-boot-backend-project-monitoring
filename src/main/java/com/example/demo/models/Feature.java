package com.example.demo.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	private String url;
	
	@OneToMany(mappedBy = "primaryKey.feature", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<RoleFeature> roleFeatures = new HashSet<>();

	public Feature() {
	}

	public Feature(String featureName, String url) {
		this.featureName = featureName;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<RoleFeature> getRoleFeatures() {
		return roleFeatures;
	}

	public void setRoleFeatures(Set<RoleFeature> roleFeatures) {
		this.roleFeatures = roleFeatures;
	}
	
}