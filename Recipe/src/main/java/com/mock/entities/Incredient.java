package com.mock.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;

// Define an embeddable class representing an ingredient
@Embeddable
public class Incredient {

	// Fields for the name and quantity of the ingredient
	private String name;
	private String quantity;

	// Constructor to initialize the Incredient with name and quantity
	public Incredient(String name, String quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	// Getter method for retrieving the name of the ingredient
	public String getName() {
		return name;
	}

	// Setter method for setting the name of the ingredient
	public void setName(String name) {
		this.name = name;
	}

	// Getter method for retrieving the quantity of the ingredient
	public String getQuantity() {
		return quantity;
	}

	// Setter method for setting the quantity of the ingredient
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	// Generate a hash code based on the name and quantity
	@Override
	public int hashCode() {
		return Objects.hash(name, quantity);
	}

	// Check if two Incredient objects are equal based on name and quantity
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incredient other = (Incredient) obj;
		return Objects.equals(name, other.name) && Objects.equals(quantity, other.quantity);
	}

}
