package com.example.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	@OneToMany(mappedBy = "primaryKey.role", cascade = CascadeType.ALL)
    private Set<RoleFeature> roleFeatures = new HashSet<RoleFeature>();
	
	
	public Role() {
	}
	
	public Role(ERole name, RoleFeature... roleFeatures) {
		this.name = name;
		for(RoleFeature roleFeature : roleFeatures) roleFeature.setRole(this);
        this.roleFeatures = Stream.of(roleFeatures).collect(Collectors.toSet());
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

	public Set<RoleFeature> getRoleFeatures() {
		return roleFeatures;
	}

	public void setRoleFeatures(Set<RoleFeature> roleFeatures) {
		this.roleFeatures = roleFeatures;
	}

}