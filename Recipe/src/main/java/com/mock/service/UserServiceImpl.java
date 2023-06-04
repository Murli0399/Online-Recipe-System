package com.mock.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.dto.LogInDto;
import com.mock.entities.CurrentUserSession;
import com.mock.entities.User;
import com.mock.exception.CurrentUserSessionException;
import com.mock.exception.UserException;
import com.mock.repository.CurrentSessionRepo;
import com.mock.repository.UserRepo;



@Service
public class UserServiceImpl implements UserService{
	@Autowired
	public CurrentSessionRepo csr;
	@Autowired
	public UserRepo ur;
	
	@Override
	public User addUser(User user) throws UserException {
		// TODO Auto-generated method stub
		if(user==null) {
			throw new UserException("User is not valid to add");
		}
		return ur.save(user);
	}
	
	@Override
	public CurrentUserSession logIn(LogInDto lid) throws CurrentUserSessionException {
		User user=ur.findByUserName(lid.getUsername());
		if(user==null) {
			throw new CurrentUserSessionException("User not found");
		}
		CurrentUserSession ccus=csr.findByUserId(user.getUserId());
		if(ccus!=null) {
			throw new CurrentUserSessionException("User is Already logged In");
		}
		if(!user.getPassword().equals(lid.getPassword())) {
			throw new CurrentUserSessionException("Credentials is not valid");
		}
		//Net.buddy RandomString is stopping me to create the application
		String uuid=user.getUserName()+user.getUserId();
		CurrentUserSession cus=new CurrentUserSession();
		cus.setUserId(user.getUserId());
		cus.setUuid(uuid);
		cus.setLocalDateTime(LocalDateTime.now());
		
		
		
		return csr.save(cus);
	}

	@Override
	public boolean logOut(String uuid) throws CurrentUserSessionException {
		// TODO Auto-generated method stub
		CurrentUserSession cus= csr.findByUuid(uuid);
		if(cus!=null) {
			throw new CurrentUserSessionException("User is not log in currently");
		}
		csr.delete(cus);
		return true;
	}

	

}
