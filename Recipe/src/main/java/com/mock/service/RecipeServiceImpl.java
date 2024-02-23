package com.mock.service;

import com.mock.entities.CurrentUserSession;
import com.mock.entities.Rating;
import com.mock.entities.Recipe;
import com.mock.entities.User;
import com.mock.exception.RecipeException;
import com.mock.exception.UserException;
import com.mock.repository.CurrentSessionRepo;
import com.mock.repository.RatingRepo;
import com.mock.repository.RecipeRepo;
import com.mock.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Service class providing implementation for Recipe-related operations
@Service
public class RecipeServiceImpl implements RecipeService {
    // Autowired repositories for data access
    @Autowired
    public RecipeRepo recipeRepo;
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public CurrentSessionRepo currentSessionRepo;
    @Autowired
    public RatingRepo ratingRepo;

    // Implementation for creating a new recipe
    @Override
    public Recipe createRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException {
        // Retrieve the current user session based on authentication ID
        CurrentUserSession cus = currentSessionRepo.findByUserAuthenticationId(userAuthenticationId);

        // Check if the user is logged in
        if (cus == null) {
            throw new UserException("You must Login to create Recipe");
        }

        // Find the user associated with the current session
        User user = userRepo.findByUserId(cus.getUserId());

        // Check if a valid user is found
        if (user == null) {
            throw new UserException("No User Found");
        }

        // Set the user for the recipe and update the user's list of recipes
        recipe.setUser(user);
        user.getRecipes().add(recipe);
        userRepo.save(user);

        // Save the recipe to the repository
        recipeRepo.save(recipe);

        // Create a new Rating for the recipe and initialize with default values
        Rating rate = new Rating();
        rate.setRecipe(recipe);
        rate.setNoOfRaters(0);
        rate.setRating(0);

        // Save the rating to the repository
        ratingRepo.save(rate);

        // Return the saved recipe
        return recipeRepo.save(recipe);
    }


    // Implementation for retrieving a recipe by ID
    @Override
    public Recipe getRecipe(int recipeId) throws RecipeException {
        // Retrieve the recipe from the repository based on the given ID
        Optional<Recipe> orecipe = recipeRepo.findById(recipeId);

        // Check if the recipe is present in the repository
        if (!orecipe.isPresent()) {
            // Throw an exception if the recipe is not found
            throw new RecipeException("No recipe Found");
        }

        // Return the retrieved recipe
        return orecipe.get();
    }


    // Implementation for retrieving all recipes
    @Override
    public List<Recipe> getAllRecipe() throws RecipeException {
        // Retrieve all recipes from the repository
        List<Recipe> recipes = recipeRepo.findAll();

        // Check if the list of recipes is empty
        if (recipes.isEmpty()) {
            // Throw an exception if no recipes are found
            throw new RecipeException("No recipes found");
        }

        // Return the list of retrieved recipes
        return recipes;
    }


    // Implementation for updating a recipe
    @Override
    public Recipe updateRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException {
        // Retrieve the current user session based on authentication ID
        CurrentUserSession cus = currentSessionRepo.findByUserAuthenticationId(userAuthenticationId);

        // Check if the user is logged in
        if (cus == null) {
            throw new UserException("You must Login to update Recipe");
        }

        // Find the user associated with the current session
        User user = userRepo.findByUserId(cus.getUserId());

        // Check if a valid user is found
        if (user == null) {
            throw new UserException("No User Found");
        }

        // Find the existing recipe based on the provided ID
        Optional<Recipe> exRecipe = recipeRepo.findById(recipe.getId());

        // Check if the existing recipe is present
        if (!exRecipe.isPresent()) {
            throw new RecipeException("Recipe not found by this id");
        }

        // Verify that the user is the owner of the existing recipe
        int userId = exRecipe.get().getUser().getUserId();
        if (user.getUserId() != userId) {
            throw new UserException("Not a valid user to update the recipe");
        }

        // Replace the existing recipe in the user's list with the updated one
        Recipe existing = null;
        Set<Recipe> set = user.getRecipes();
        for (Recipe r : set) {
            if (r.getId() == exRecipe.get().getId()) {
                existing = r;
            }
        }
        set.remove(existing);
        set.add(recipe);
        user.setRecipes(set);

        // Save the updated user information
        userRepo.save(user);

        // Save the updated recipe
        return recipeRepo.save(recipe);
    }


    // Implementation for deleting a recipe
    @Override
    public Recipe deleteRecipe(Recipe recipe, String userAuthenticationId) throws UserException, RecipeException {
        // Retrieve the current user session based on authentication ID
        CurrentUserSession cus = currentSessionRepo.findByUserAuthenticationId(userAuthenticationId);

        // Check if the user is logged in
        if (cus == null) {
            throw new UserException("You must Login to update Recipe");
        }

        // Find the user associated with the current session
        User user = userRepo.findByUserId(cus.getUserId());

        // Check if a valid user is found
        if (user == null) {
            throw new UserException("No User Found");
        }

        // Find the existing recipe based on the provided ID
        Optional<Recipe> exRecipe = recipeRepo.findById(recipe.getId());

        // Check if the existing recipe is present
        if (!exRecipe.isPresent()) {
            throw new RecipeException("Recipe not found by this id");
        }

        // Verify that the user is the owner of the existing recipe
        int userId = exRecipe.get().getUser().getUserId();
        if (user.getUserId() != userId) {
            throw new UserException("Not valid user to update");
        }

        // Remove the existing recipe from the user's list
        Recipe existing = null;
        Set<Recipe> set = user.getRecipes();
        for (Recipe r : set) {
            if (r.getId() == exRecipe.get().getId()) {
                existing = r;
            }
        }
        set.remove(existing);

        // Save the updated user information
        user.setRecipes(set);
        userRepo.save(user);

        // Delete the recipe from the repository
        recipeRepo.delete(recipe);

        // Return the deleted recipe
        return recipe;
    }


    // Implementation for rating a recipe
    @Override
    public Rating rateRecipe(int recipeid, int rate) throws RecipeException {
        // Check if the rating is greater than 5
        if (rate > 5) {
            throw new RecipeException("Rating should not be greater than 5");
        }

        // Find the existing recipe based on the provided ID
        Optional<Recipe> exRecipe = recipeRepo.findById(recipeid);

        // Check if the existing recipe is present
        if (!exRecipe.isPresent()) {
            throw new RecipeException("Recipe not found by this id");
        }

        // Retrieve the recipe and its associated rating
        Recipe recipe = exRecipe.get();
        Rating rating = recipe.getRating();

        // Calculate the new rating based on the provided rate
        rating.setRating(((rating.getNoOfRaters() * rating.getRating()) + rate) / (rating.getNoOfRaters() + 1));
        rating.setNoOfRaters(rating.getNoOfRaters() + 1);

        // Save the updated rating to the repository
        ratingRepo.save(rating);

        // Return the updated rating
        return rating;
    }


    // Implementation for bookmarking a recipe
    @Override
    public Recipe bookMarkRecipe(String userAuthenticationId, int recipeId) throws UserException, RecipeException {
        // Retrieve the current user session based on authentication ID
        CurrentUserSession cus = currentSessionRepo.findByUserAuthenticationId(userAuthenticationId);

        // Find the existing recipe based on the provided ID
        Optional<Recipe> exRecipe = recipeRepo.findById(recipeId);

        // Check if the existing recipe is present
        if (!exRecipe.isPresent()) {
            throw new RecipeException("Recipe not found by this id");
        }

        // Retrieve the recipe
        Recipe recipe = exRecipe.get();

        // Check if the user is logged in
        if (cus == null) {
            throw new UserException("You must Login to bookmark this Recipe");
        }

        // Find the user associated with the current session
        User user = userRepo.findByUserId(cus.getUserId());

        // Check if a valid user is found
        if (user == null) {
            throw new UserException("No User Found");
        }

        // Add the recipe to the user's bookmarked recipes
        Set<Recipe> set = user.getBookmarkedRecipes();
        set.add(recipe);
        user.setBookmarkedRecipes(set);

        // Save the updated user information
        userRepo.save(user);

        // Add the user to the recipe's list of bookmarked users
        Set<User> users = recipe.getBookmarkedUsers();
        users.add(user);
        recipe.setBookmarkedUsers(users);

        // Save the updated recipe
        return recipeRepo.save(recipe);
    }

}
