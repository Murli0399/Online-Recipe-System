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

@RestController("/recipe")
public class RecipeController {
	@Autowired
	public UserService us;
	@Autowired
	public RecipeService rs;
	
	@PostMapping
	ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe,@PathVariable String uuid) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.createRecipe(recipe, uuid),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}")
	ResponseEntity<Recipe> getRecipe(@PathVariable int id) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.getRecipe(id),HttpStatus.FOUND);
	}
	@GetMapping("/getall")
	ResponseEntity<List<Recipe>> getAllRecipe() throws  RecipeException{
		return new ResponseEntity<>(rs.getAllRecipe(),HttpStatus.FOUND);
	}
	@PatchMapping("/{uuid}")
	ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe ,@PathVariable String uuid) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.updateRecipe(recipe,uuid),HttpStatus.OK);
	}
	@DeleteMapping("/{uuid}")
	ResponseEntity<Recipe> deleteRecipe(@Valid @RequestBody Recipe recipe ,@PathVariable String uuid) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.deleteRecipe(recipe,uuid),HttpStatus.ACCEPTED);
	}
	@PostMapping("/{uuid}/{recid}")
	ResponseEntity<Recipe> bookRecipe(@PathVariable String uuid,@PathVariable int recid) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.bookMarkRecipe(uuid,recid),HttpStatus.ACCEPTED);
	}
	@PutMapping("/{recid}/{rate}")
	ResponseEntity<Rating> rateRecipe(@PathVariable int recid,@PathVariable int rate) throws UserException, RecipeException{
		return new ResponseEntity<>(rs.rateRecipe(recid,rate),HttpStatus.ACCEPTED);
	}
	
	
	
	
	
}
