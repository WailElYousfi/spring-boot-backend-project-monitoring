package com.example.demo.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long userId;
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String phone;
	private String jiraUsername;
	private Long userCode;
	private String role;

	public JwtResponse(String accessToken, Long userId, String firstname, String lastname, String username, String email, String jiraUsername, String phone, Long userCode, String role) {
		this.token = accessToken;
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.role = role;
		this.jiraUsername = jiraUsername;
		this.userCode = userCode;
		this.phone = phone;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJiraUsername() {
		return jiraUsername;
	}

	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}
	
	
}