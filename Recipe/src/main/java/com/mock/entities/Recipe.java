package com.mock.entities;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@jakarta.validation.constraints.NotBlank
	private String title;
	@NotBlank
	private String discription;
	@ElementCollection
	@Embedded
	private Set<Incredient> incredients;
	private String instruction;
	@Pattern(regexp = "^([0-2][0-3]):([0-5][0-9]):([0-5][0-9])$)")
	private String time;
	private String difficulty;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> bookedMarkedUser;
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Rating rating;
	public Recipe() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return id == other.id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Set<Incredient> getIncredients() {
		return incredients;
	}
	public void setIncredients(Set<Incredient> incredients) {
		this.incredients = incredients;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Set<User> getBookedMarkedUser() {
		return bookedMarkedUser;
	}

	public void setBookedMarkedUser(Set<User> bookedMarkedUser) {
		this.bookedMarkedUser = bookedMarkedUser;
	}

	
	
	
}
