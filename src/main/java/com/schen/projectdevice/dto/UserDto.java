package com.schen.projectdevice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {
	
	@NotNull(message="user name should not be empty.")
	@Size(min=1, message="user name should not be empty.")
	private String username;
	@NotNull(message="user name should not be empty.")
	@Size(min=4, message="password length should not be smaller than 4.")
	private String password;
	private String confirmPassword;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
