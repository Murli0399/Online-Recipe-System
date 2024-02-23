package com.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mock.entities.CurrentUserSession;

@Repository
public interface CurrentSessionRepo extends JpaRepository<CurrentUserSession, Integer>{
	public CurrentUserSession findByUserAuthenticationId(String uuid);
	public CurrentUserSession  findByUserId(Integer userId);
	
}
