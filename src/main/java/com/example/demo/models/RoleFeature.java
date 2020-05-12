package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.models.RoleFeatureId;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "role_features")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.role",
        joinColumns = @JoinColumn(name = "role_id")),
    @AssociationOverride(name = "primaryKey.feature",
        joinColumns = @JoinColumn(name = "feature_id")) })
public class RoleFeature {
	
	@EmbeddedId
	@JsonIgnore
    private RoleFeatureId primaryKey = new RoleFeatureId();
	
	@ManyToOne
    @JoinColumn(name="accesslevelId")
	private Accesslevel accessLevel;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;


	public RoleFeatureId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(RoleFeatureId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
    @JsonIgnore
    public Role getRole() {
        return getPrimaryKey().getRole();
    }
 
    public void setRole(Role role) {
        getPrimaryKey().setRole(role);
    }
 
	@Transient
    public Feature getFeature() {
        return getPrimaryKey().getFeature();
    }
 
    public void setFeature(Feature feature) {
        getPrimaryKey().setFeature(feature);;
    }
    
	public Accesslevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(Accesslevel accessLevel) {
		this.accessLevel = accessLevel;
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