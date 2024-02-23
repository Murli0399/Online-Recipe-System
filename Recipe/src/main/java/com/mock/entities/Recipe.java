package com.mock.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;
import java.util.Set;

// Define an entity class representing a Recipe
@Entity
public class Recipe {

    // Primary key for the entity, auto-generated using identity strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Title of the recipe, marked with @NotBlank validation
    @jakarta.validation.constraints.NotBlank
    private String title;

    // Description of the recipe, marked with @NotBlank validation
    @NotBlank
    private String description;

    // Set of ingredients for the recipe, stored as an ElementCollection of Incredient objects
    @ElementCollection
    @Embedded
    private Set<Incredient> ingredients;

    // Instruction for preparing the recipe
    private String instruction;

    // Time required to prepare the recipe, validated using @Pattern
    @Pattern(regexp = "^([0-2][0-3]):([0-5][0-9]):([0-5][0-9])$")
    private String time;

    // Difficulty level of the recipe
    private String difficulty;

    // Reference to the User who created the recipe, marked with @JsonIgnore to avoid circular serialization
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // Set of Users who have bookmarked or marked the recipe, marked with @JsonIgnore
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> bookmarkedUsers;

    // Rating for the recipe, stored as a OneToOne relationship with the Rating entity
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Rating rating;

    // Default constructor required for JPA
    public Recipe() {
        // TODO Auto-generated constructor stub
    }

    // Override hashCode and equals methods based on the recipe's ID for proper comparison
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

    // Getter and setter methods for various properties of the recipe
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Incredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Incredient> ingredients) {
        this.ingredients = ingredients;
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

    public Set<User> getBookmarkedUsers() {
        return bookmarkedUsers;
    }

    public void setBookmarkedUsers(Set<User> bookmarkedUsers) {
        this.bookmarkedUsers = bookmarkedUsers;
    }
}
