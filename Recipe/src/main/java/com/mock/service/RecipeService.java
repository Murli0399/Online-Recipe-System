package com.mock.service;

import java.util.List;

import com.mock.entities.Rating;
import com.mock.entities.Recipe;
import com.mock.exception.RecipeException;
import com.mock.exception.UserException;

// Interface defining the contract for Recipe-related operations
public interface RecipeService {

	// Method for creating a new recipe
	public Recipe createRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException;

	// Method for retrieving a specific recipe by its ID
	public Recipe getRecipe(int recipeId) throws RecipeException;

	// Method for retrieving all recipes
	public List<Recipe> getAllRecipe() throws RecipeException;

	// Method for updating an existing recipe
	public Recipe updateRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException;

	// Method for deleting an existing recipe
	public Recipe deleteRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException;

	// Method for rating a recipe
	public Rating rateRecipe(int recipeid, int rate) throws RecipeException;

	// Method for bookmarking a recipe
	public Recipe bookMarkRecipe(String userAuthenticationId, int recipeId) throws UserException, RecipeException;
}

