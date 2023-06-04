package com.mock.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	public RecipeRepo rr;
	@Autowired
	public UserRepo ur;
	@Autowired
	public CurrentSessionRepo csr;
	@Autowired
	public RatingRepo rtr;
	@Override
	public Recipe createRecipe(Recipe recipe, String uuid) throws UserException, RecipeException {
		// TODO Auto-generated method stub
		CurrentUserSession cus=csr.findByUuid(uuid);
		if(cus==null) {
			throw new UserException("You must Login to create Recipe");
		}
		User user =ur.findByUserId(cus.getUserId());
		if(user==null) {
			throw new UserException("No User Found");
			
		}
		recipe.setUser(user);
		user.getRecipes().add(recipe);
		ur.save(user);
		rr.save(recipe);
		Rating rate= new Rating();
		rate.setRecipe(recipe);
		rate.setNoOfRaters(0);
		rate.setRating(0);
		rtr.save(rate);
		return rr.save(recipe);
	}

	@Override
	public Recipe getRecipe(int recipeId) throws RecipeException {
		// TODO Auto-generated method stub
		Optional<Recipe> orecipe=rr.findById(recipeId);
		if(!orecipe.isPresent()){
			throw new RecipeException("No recipe Found");
		}
		return orecipe.get();
	}

	@Override
	public List<Recipe> getAllRecipe() throws RecipeException {
		// TODO Auto-generated method stub
		List<Recipe> recipes=rr.findAll();
		if(recipes.isEmpty()) {
			throw new RecipeException("No recipes found");
		}
		return recipes;
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, String uuid) throws UserException, RecipeException {
		// TODO Auto-generated method stub
		CurrentUserSession cus=csr.findByUuid(uuid);
		if(cus==null) {
			throw new UserException("You must Login to update Recipe");
		}
		User user =ur.findByUserId(cus.getUserId());
		if(user==null) {
			throw new UserException("No User Found");
			
		}
		Optional<Recipe> exRecipe=rr.findById(recipe.getId());
		if(!exRecipe.isPresent()) {
			throw new RecipeException("Recipe not found by this id");
		}
		int userId=exRecipe.get().getUser().getUserId();
		if(user.getUserId()!=userId) {
			throw new UserException("Not valid user to update ");
		}
		Recipe existing=null;
		Set<Recipe> set=user.getRecipes();
		for(Recipe r:set) {
			if(r.getId()==exRecipe.get().getId()) {
				existing=r;
			}
		}
		set.remove(existing);
		set.add(recipe);
		user.setRecipes(set);
		ur.save(user);
		
		return rr.save(recipe);
	}

	@Override
	public Recipe deleteRecipe(Recipe recipe, String uuid) throws UserException, RecipeException {
		// TODO Auto-generated method stub
		CurrentUserSession cus=csr.findByUuid(uuid);
		if(cus==null) {
			throw new UserException("You must Login to update Recipe");
		}
		User user =ur.findByUserId(cus.getUserId());
		if(user==null) {
			throw new UserException("No User Found");
			
		}
		Optional<Recipe> exRecipe=rr.findById(recipe.getId());
		if(!exRecipe.isPresent()) {
			throw new RecipeException("Recipe not found by this id");
		}
		int userId=exRecipe.get().getUser().getUserId();
		if(user.getUserId()!=userId) {
			throw new UserException("Not valid user to update ");
		}
		Recipe existing=null;
		Set<Recipe> set=user.getRecipes();
		for(Recipe r:set) {
			if(r.getId()==exRecipe.get().getId()) {
				existing=r;
			}
		}
		set.remove(existing);
		
		user.setRecipes(set);
		ur.save(user);
		rr.delete(recipe);
		return recipe;
		
	}

	@Override
	public Rating rateRecipe(int recipeid,int rate) throws RecipeException {
		// TODO Auto-generated method stub
		if(rate>5) {
			throw new RecipeException("Rating should not be grater than 5");
		}
		Optional<Recipe> exRecipe=rr.findById(recipeid);
		if(!exRecipe.isPresent()) {
			throw new RecipeException("Recipe not found by this id");
		}
		Recipe recipe=exRecipe.get();
		Rating rating=recipe.getRating();
		rating.setRating(((rating.getNoOfRaters()*rating.getRating())+rate)/(rating.getNoOfRaters()+1));
		rating.setNoOfRaters(rating.getNoOfRaters()+1);
		rtr.save(rating);
		return rating;
	}

	@Override
	public Recipe bookMarkRecipe(String uuid, int recipeId) throws UserException, RecipeException {
		// TODO Auto-generated method stub
		CurrentUserSession cus=csr.findByUuid(uuid);
		Optional<Recipe> exRecipe=rr.findById(recipeId);
		if(!exRecipe.isPresent()) {
			throw new RecipeException("Recipe not found by this id");
		}
		Recipe recipe=exRecipe.get();
		if(cus==null) {
			throw new UserException("You must Login to bookmark this Recipe");
		}
		User user =ur.findByUserId(cus.getUserId());
		if(user==null) {
			throw new UserException("No User Found");
			
		}
		Set<Recipe> set=user.getBookMarkedRecipes();
		set.add(recipe);
		user.setBookMarkedRecipes(set);
		ur.save(user);
		Set <User> users=recipe.getBookedMarkedUser();
		users.add(user);
		recipe.setBookedMarkedUser(users);
	
		return rr.save(recipe);
	}

}
