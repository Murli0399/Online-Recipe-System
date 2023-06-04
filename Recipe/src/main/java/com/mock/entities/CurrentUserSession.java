package com.mock.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;
	private String uuid;
	private LocalDateTime localDateTime;
	
	public CurrentUserSession(Integer userId, String uuid, String role) {
		super();
		this.userId = userId;
		this.uuid = uuid;

		this.localDateTime = LocalDateTime.now();
	}
	public CurrentUserSession() {
		// TODO Auto-generated constructor stub
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}
