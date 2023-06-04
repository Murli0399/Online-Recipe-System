package com.mock.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Incredient {
	private String name;
	private String quantity;
	public Incredient(String name, String quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, quantity);
	}
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
