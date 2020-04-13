package com.example.demo.models;

import java.io.Serializable;
 
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.example.demo.models.Feature;
import com.example.demo.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Embeddable
public class RoleFeatureId implements Serializable { 
	
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Feature feature;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}
 
   
}