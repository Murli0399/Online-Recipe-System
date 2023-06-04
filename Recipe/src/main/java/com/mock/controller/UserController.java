package com.mock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mock.dto.LogInDto;
import com.mock.entities.CurrentUserSession;
import com.mock.entities.User;
import com.mock.exception.CurrentUserSessionException;
import com.mock.exception.UserException;
import com.mock.service.UserService;

import jakarta.validation.Valid;
@RestController
public class UserController {
	@Autowired
	public UserService us;
	
	@PostMapping("/users")
	ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserException{
		return new ResponseEntity<>(us.addUser(user),HttpStatus.ACCEPTED);
	}
	@GetMapping("/users")
	ResponseEntity<CurrentUserSession> logInUser(@RequestBody LogInDto ltd) throws UserException, CurrentUserSessionException{
		return new ResponseEntity<>(us.logIn(ltd),HttpStatus.ACCEPTED);
	}
	@PostMapping("/users/{uuid}")
	ResponseEntity<Boolean> LogOutUser(@PathVariable String uuid) throws UserException, CurrentUserSessionException{
		return new ResponseEntity<>(us.logOut(uuid),HttpStatus.ACCEPTED);
	}
	
}
