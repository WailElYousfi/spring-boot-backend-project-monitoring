package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<User> users;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "role_features", 
				joinColumns = @JoinColumn(name = "role_id"), 
				inverseJoinColumns = @JoinColumn(name = "feature_id"))
	private List<Feature> features = new ArrayList<>();
	
	
	public Role() {
	}
	
	public Role(ERole name) {
		this.name = name;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

}