package com.example.demo.models;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "features")
public class Feature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer featureId;

	@NotBlank
	private String featureName;
	
	private String title;
	
	private String parentTitle;
	
	private String url;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@OneToMany(mappedBy = "primaryKey.feature", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<RoleFeature> roleFeatures = new HashSet<>();

	public Feature() {
	}

	public Feature(String featureName, String url, String title, String parentTitle) {
		this.featureName = featureName;
		this.url = url;
		this.title = title;
		this.parentTitle = parentTitle;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	public Set<RoleFeature> getRoleFeatures() {
		return roleFeatures;
	}

	public void setRoleFeatures(Set<RoleFeature> roleFeatures) {
		this.roleFeatures = roleFeatures;
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