package com.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mock.entities.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Integer> {

}
