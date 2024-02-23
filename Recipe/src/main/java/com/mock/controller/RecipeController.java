package com.mock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mock.entities.Rating;
import com.mock.entities.Recipe;
import com.mock.exception.RecipeException;
import com.mock.exception.UserException;
import com.mock.service.RecipeService;
import com.mock.service.UserService;

import jakarta.validation.Valid;

// Define a RESTful controller for handling Recipe-related operations
@RestController("/recipe")
public class RecipeController {

	// Autowire RecipeService for dependency injection
	@Autowired
	public RecipeService recipeService;

	// Define endpoint for adding a new recipe
	@PostMapping
	ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe, @PathVariable String uuid) throws UserException, RecipeException {
		// Return a response with the created recipe and status ACCEPTED
		return new ResponseEntity<>(recipeService.createRecipe(recipe, uuid), HttpStatus.ACCEPTED);
	}

	// Define endpoint for retrieving a recipe by its ID
	@GetMapping("/get/{id}")
	ResponseEntity<Recipe> getRecipe(@PathVariable int id) throws UserException, RecipeException {
		// Return a response with the retrieved recipe and status FOUND
		return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.FOUND);
	}

	// Define endpoint for retrieving all recipes
	@GetMapping("/getall")
	ResponseEntity<List<Recipe>> getAllRecipe() throws RecipeException {
		// Return a response with the list of all recipes and status FOUND
		return new ResponseEntity<>(recipeService.getAllRecipe(), HttpStatus.FOUND);
	}

	// Define endpoint for updating a recipe
	@PatchMapping("/{uuid}")
	ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable String uuid) throws UserException, RecipeException {
		// Return a response with the updated recipe and status OK
		return new ResponseEntity<>(recipeService.updateRecipe(recipe, uuid), HttpStatus.OK);
	}

	// Define endpoint for deleting a recipe
	@DeleteMapping("/{uuid}")
	ResponseEntity<Recipe> deleteRecipe(@Valid @RequestBody Recipe recipe, @PathVariable String uuid) throws UserException, RecipeException {
		// Return a response with the deleted recipe and status ACCEPTED
		return new ResponseEntity<>(recipeService.deleteRecipe(recipe, uuid), HttpStatus.ACCEPTED);
	}

	// Define endpoint for bookmarking a recipe
	@PostMapping("/{uuid}/{recid}")
	ResponseEntity<Recipe> bookRecipe(@PathVariable String uuid, @PathVariable int recid) throws UserException, RecipeException {
		// Return a response with the bookmarked recipe and status ACCEPTED
		return new ResponseEntity<>(recipeService.bookMarkRecipe(uuid, recid), HttpStatus.ACCEPTED);
	}

	// Define endpoint for rating a recipe
	@PutMapping("/{recid}/{rate}")
	ResponseEntity<Rating> rateRecipe(@PathVariable int recid, @PathVariable int rate) throws UserException, RecipeException {
		// Return a response with the rating and status ACCEPTED
		return new ResponseEntity<>(recipeService.rateRecipe(recid, rate), HttpStatus.ACCEPTED);
	}
}