package com.mock.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

// Define an entity class representing a rating for a Recipe
@Entity
public class Rating {

    // Primary key for the entity, auto-generated using identity strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Number of users who have rated the recipe
    private int noOfRaters;

    // The overall rating for the recipe
    private int rating;

    // Reference to the associated Recipe, marked with @JsonIgnore to avoid infinite recursion
    @JsonIgnore
    @OneToOne
    private Recipe recipe;

    // Default constructor required for JPA
    public Rating() {
        // TODO Auto-generated constructor stub
    }

    // Getter method for retrieving the ID of the rating
    public int getId() {
        return id;
    }

    // Setter method for setting the ID of the rating
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for retrieving the number of raters
    public int getNoOfRaters() {
        return noOfRaters;
    }

    // Setter method for setting the number of raters
    public void setNoOfRaters(int noOfRaters) {
        this.noOfRaters = noOfRaters;
    }

    // Getter method for retrieving the overall rating
    public int getRating() {
        return rating;
    }

    // Setter method for setting the overall rating
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Getter method for retrieving the associated Recipe
    public Recipe getRecipe() {
        return recipe;
    }

    // Setter method for setting the associated Recipe
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
