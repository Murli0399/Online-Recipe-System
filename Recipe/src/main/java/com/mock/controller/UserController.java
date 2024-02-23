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
// Define a RESTful controller for handling User-related operations
@RestController
public class UserController {

	// Autowire UserService for dependency injection
	@Autowired
	public UserService userService;

	// Define endpoint for adding a new user
	@PostMapping("/users")
	ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserException {
		// Return a response with the added user and status ACCEPTED
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.ACCEPTED);
	}

	// Define endpoint for user login
	@GetMapping("/users")
	ResponseEntity<CurrentUserSession> logInUser(@RequestBody LogInDto ltd) throws UserException, CurrentUserSessionException {
		// Return a response with the current user session and status ACCEPTED
		return new ResponseEntity<>(userService.logIn(ltd), HttpStatus.ACCEPTED);
	}

	// Define endpoint for user logout
	@PostMapping("/users/{uuid}")
	ResponseEntity<Boolean> logOutUser(@PathVariable String uuid) throws UserException, CurrentUserSessionException {
		// Return a response with the logout status and status ACCEPTED
		return new ResponseEntity<>(userService.logOut(uuid), HttpStatus.ACCEPTED);
	}
}