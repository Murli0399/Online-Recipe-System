package com.mock.entities;

import java.util.Set;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotEmpty
	@Column(unique = true)
	@Size(min = 6,max=16)
	private String userName;
	@NotEmpty
	private String password;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "user")
	private Set<Recipe> recipes;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "bookedMarkedUser")
	private Set<Recipe> bookMarkedRecipes;
	public User(@Size(min = 6, max = 16) String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Set<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	public Set<Recipe> getBookMarkedRecipes() {
		return bookMarkedRecipes;
	}
	public void setBookMarkedRecipes(Set<Recipe> bookMarkedRecipes) {
		this.bookMarkedRecipes = bookMarkedRecipes;
	}
	
	
	
	
}
