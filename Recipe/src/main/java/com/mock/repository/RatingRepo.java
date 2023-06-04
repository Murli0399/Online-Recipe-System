package com.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mock.entities.Rating;
@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
