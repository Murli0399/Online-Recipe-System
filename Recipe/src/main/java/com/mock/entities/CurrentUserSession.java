package com.mock.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Define an entity class representing the current user session
@Entity
public class CurrentUserSession {

	// Primary key for the entity
	@Id
	@Column(unique = true)
	private Integer userId;

	// Unique identifier for user authentication
	private String userAuthenticationId;

	// Timestamp indicating the time of the current session
	private LocalDateTime localDateTime;

	// Constructor to initialize the CurrentUserSession with user ID and authentication ID
	public CurrentUserSession(Integer userId, String userAuthenticationId) {
		super();
		this.userId = userId;
		this.userAuthenticationId = userAuthenticationId;

		// Set the timestamp to the current date and time
		this.localDateTime = LocalDateTime.now();
	}

	// Default constructor required for JPA
	public CurrentUserSession() {
		// TODO Auto-generated constructor stub
	}

	// Getter method for retrieving the user ID
	public Integer getUserId() {
		return userId;
	}

	// Setter method for setting the user ID
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// Getter method for retrieving the user authentication ID (UUID)
	public String getUserAuthenticationId() {
		return userAuthenticationId;
	}

	// Setter method for setting the user authentication ID (UUID)
	public void setUserAuthenticationId(String userAuthenticationId) {
		this.userAuthenticationId = userAuthenticationId;
	}

	// Getter method for retrieving the timestamp of the current session
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	// Setter method for setting the timestamp of the current session
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}
