package com.example.demo.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String firstname;
	
	private String lastname;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private String phone;
		
	private String jiraUsername;
	
	private Long userCode;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long userId, String firstname, String lastname, String username, String email, String password, String phone, String jiraUsername, Long userCode,
			Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.jiraUsername = jiraUsername;
		this.userCode = userCode;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>() ;
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName().name()));

		return new UserDetailsImpl(
				user.getUserId(),
				user.getFirstname(),
				user.getLastname(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				user.getPhone(),
				user.getJiraUsername(), 
				user.getUserCode(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}
	

	public String getPhone() {
		return phone;
	}

	public String getJiraUsername() {
		return jiraUsername;
	}

	public Long getUserCode() {
		return userCode;
	}	

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(userId, user.userId);
	}
}
