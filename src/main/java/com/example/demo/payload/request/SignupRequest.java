package com.example.demo.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
	@NotBlank
    private String firstname;
	
	@NotBlank
    private String lastname;
	
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private String role;
    
    @NotBlank
	private String phone;
	
	@NotBlank
	private String jiraUsername;
	
	@NotNull
	private Long userCode;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;   
  
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

	public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
      return this.role;
    }
    
    public void setRole(String role) {
      this.role = role;
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