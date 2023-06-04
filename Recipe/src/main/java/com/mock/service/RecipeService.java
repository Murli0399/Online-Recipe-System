package com.mock.service;

import java.util.List;

import com.mock.entities.Rating;
import com.mock.entities.Recipe;
import com.mock.exception.RecipeException;
import com.mock.exception.UserException;

public interface RecipeService {
	public Recipe createRecipe(Recipe recipe ,String uuid)throws UserException,RecipeException;
	public Recipe getRecipe(int recipeId) throws RecipeException;
	public List<Recipe> getAllRecipe()throws RecipeException;
	public Recipe updateRecipe (Recipe recipe,String uuid) throws UserException,RecipeException;
	public Recipe deleteRecipe(Recipe recipe,String uuid) throws UserException,RecipeException;
	public Rating rateRecipe(int recipeid,int rate) throws RecipeException;
	public Recipe bookMarkRecipe(String uuid,int recipeId) throws UserException,RecipeException;
}
