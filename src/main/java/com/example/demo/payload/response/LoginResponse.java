package com.example.demo.payload.response;

import com.example.demo.models.User;

public class LoginResponse {
	private User user;
	private String token;
	private String type = "Bearer";
	private long time;
	
	public LoginResponse() {
	}

	public LoginResponse(User user, String token, long time) {
		this.user = user;
		this.token = token;
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}