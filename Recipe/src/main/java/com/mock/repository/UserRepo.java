package com.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mock.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUserName(String userName);
	public User findByUserId(int userId);
}
