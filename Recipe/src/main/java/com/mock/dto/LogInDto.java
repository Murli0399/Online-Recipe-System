package com.mock.dto;

// Define a Data Transfer Object (DTO) class for login information
public class LogInDto {

	// Private fields to store username and password
	private String username;
	private String password;

	// Constructor to initialize the LogInDto with username and password
	public LogInDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// Getter method for retrieving the username
	public String getUsername() {
		return username;
	}

	// Setter method for setting the username
	public void setUsername(String username) {
		this.username = username;
	}

	// Getter method for retrieving the password
	public String getPassword() {
		return password;
	}

	// Setter method for setting the password
	public void setPassword(String password) {
		this.password = password;
	}

}

